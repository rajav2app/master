package com.vtitan.patnershipcricketleague.fragments;

import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.adapter.MatchListAdapter;
import com.vtitan.patnershipcricketleague.adapter.PointsAdapter;
import com.vtitan.patnershipcricketleague.adapter.TeamAdapter;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.model.Tournament;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.MatchViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TournamentViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MatchesFragment extends Fragment {
    public MatchesFragment() {
        // Required empty public constructor
    }
    private SessionManager sessionManager;
    private List<Matches>matchesList=new ArrayList<>();
    private long tStartdate;
    private long tenddate;
    private MatchViewModel matchViewModel;
    private List<Teams>teamList=new ArrayList<>();
    private MatchListAdapter matchListAdapter;

    public static MatchesFragment newInstance() {
        MatchesFragment fragment = new MatchesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView= inflater.inflate(R.layout.fragment_matches, container, false);
        final Button btn_start_match=rootView.findViewById(R.id.btn_start_match);
        final RecyclerView rv_matches=rootView.findViewById(R.id.rv_matches);
        final LinearLayout llNewMatch=rootView.findViewById(R.id.llNewMatch);
        final LinearLayout llMatchList=rootView.findViewById(R.id.llMatchList);
        final TeamViewModel mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        matchViewModel = new ViewModelProvider(this).get(MatchViewModel.class);
        final TournamentViewModel tournamentViewModel = new ViewModelProvider(this).get(TournamentViewModel.class);
        sessionManager=new SessionManager(getContext());
        tournamentViewModel.getTournamentData(sessionManager.getTournamentId()).observe(getViewLifecycleOwner(), new Observer<Tournament>() {
            @Override
            public void onChanged(Tournament tournament) {
                if(tournament!=null){
                    tStartdate=tournament.getTournament_start_time();
                    tenddate=tournament.getTournament_end_time();
                }
            }
        });

        matchViewModel.getMatches().observe(getActivity(), new Observer<List<Matches>>() {
            @Override
            public void onChanged(List<Matches> matches) {
                if(matches.size()>0){
                    matchListAdapter = new MatchListAdapter(matches,getContext() );
                    rv_matches.setLayoutManager(new LinearLayoutManager(getContext()));
                    rv_matches.setAdapter(matchListAdapter);
                    rv_matches.setHasFixedSize(true);
                    llMatchList.setVisibility(View.VISIBLE);
                    llNewMatch.setVisibility(View.GONE);
                }else
                {
                    llMatchList.setVisibility(View.GONE);
                    llNewMatch.setVisibility(View.VISIBLE);
                }

            }
        });

        mViewModel.getAllTeams(sessionManager.getTournamentId()).observe(getViewLifecycleOwner(), new Observer<List<Teams>>() {
            @Override
            public void onChanged(List<Teams> teams) {
                Log.i("TEAMSIZE",""+teams.size());
                if(teams.size()>0){
                    teamList.clear();
                    teamList.addAll(teams);
                }
            }
        });

       btn_start_match.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
           @Override
           public void onDebouncedClick(View v) {
               if(teamList.size()==6){
                   List<Matches> matches= scheduleTeam(teamList);
                   Long[] result= matchViewModel.insertMatches(matches);
                   if(result[0]==-1){
                      // btn_start_match.setEnabled(true);
                       Toast.makeText(getContext(), "Match schedule failed, Please try again.", Toast.LENGTH_SHORT).show();
                   }else
                   {
                       Toast.makeText(getContext(), "Match Scheduled successfully.", Toast.LENGTH_SHORT).show();
                       tournamentViewModel.updateMatchScheduleState(1,Integer.parseInt(sessionManager.getTournamentId()));
                   }
               } else{
                   Toast.makeText(getContext(), "Please create 6 team and start schedule.", Toast.LENGTH_SHORT).show();
               }
           }
       });

        tournamentViewModel.getMatchScheduleState().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer==1){
                    btn_start_match.setEnabled(false);
                }else{
                    btn_start_match.setEnabled(true);
                }
            }
        });

        return rootView;
    }

    public List<Matches> scheduleTeam(List<Teams> teams){
        //obtain the number of teams from user input
        //Scanner input = new Scanner(System.in);
        //System.out.print("How many teams should the fixture table have?");
        int team = teams.size();
        // Generate the schedule using round robin algorithm.
        int totalRounds = team-1 ;
        int matchesPerRound = team / 2;
        int count=0;
        String[][] rounds = new String[totalRounds][matchesPerRound];
        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (team - 1);
                int away = (team - 1 - match + round) % (team - 1);
                // Last team stays in the same place
                // while the others rotate around it.
                if (match == 0) {
                    away = team - 1;
                }
                // Add one so teams are number 1 to teams
                // not 0 to teams - 1 upon display.
                rounds[round][match] = ( (teams.get(home ).getTeam_name())
                        + ":" + (teams.get(away )).getTeam_name());
                Matches matches=new Matches();
                Calendar matchDate = Calendar.getInstance();
                matchDate.setTimeInMillis(tStartdate);
                matchDate.add(Calendar.DATE, count);
                matchDate.set(Calendar.MILLISECONDS_IN_DAY,0);
                matches.setMatch_no(count+1);
                matches.setT_ID(sessionManager.getTournamentId());
                matches.setMatch_date(matchDate.getTimeInMillis());
                matches.setMatch_venue(teams.get(home).getTeam_location());
                matches.setRound("Round " + round);
                matches.setMatch_status(0);
                matches.setMatch_details((teams.get(home ).getTeam_name()+":"+(teams.get(away )).getTeam_name()));
                matchesList.add(matches);
                count++;
            }
        }
        // Display the rounds
        for (int i = 0; i < rounds.length; i++) {
            System.out.println("Round " + (i + 1));
            System.out.println(Arrays.asList(rounds[i]));
            System.out.println();
        }
        return matchesList;
    }
}