package com.vtitan.patnershipcricketleague.fragments;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.activity.NewTournamentActivity;
import com.vtitan.patnershipcricketleague.adapter.TeamAdapter;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.model.Tournament;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.MatchViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TournamentViewModel;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    private SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
    private TeamAdapter teamAdapter;
    private List<Teams>teamList=new ArrayList<>();
    private long tStartdate;
    private long tenddate;
    private MatchViewModel matchViewModel;
    private SessionManager sessionManager;
    private List<Matches>matchesList=new ArrayList<>();
    private TeamViewModel mteamViewModel;
    private Button btn_start_match;
    private Button btn_create_team;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView=inflater.inflate(R.layout.fragment_home, container, false);

        final LinearLayout llNewMatch=rootView.findViewById(R.id.llNewMatch);
        final LinearLayout llTournament=rootView.findViewById(R.id.llTournament);
        final LinearLayout llNoTeams=rootView.findViewById(R.id.llNoTeams);
        sessionManager=new SessionManager(getActivity());
        String tID=sessionManager.getTournamentId();

        final TournamentViewModel mViewModel = new ViewModelProvider(this).get(TournamentViewModel.class);
        mteamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        matchViewModel=new ViewModelProvider(this).get(MatchViewModel.class);
        final TextView tv_location=rootView.findViewById(R.id.tv_location);
        final TextView tv_tournament_name=rootView.findViewById(R.id.tv_tournament_name);
        final TextView tv_name=rootView.findViewById(R.id.tv_name);
        final TextView tv_tournament_date=rootView.findViewById(R.id.tv_tournament_date);
        btn_start_match=rootView.findViewById(R.id.btn_start_match);
        final RecyclerView team_grid_view=rootView.findViewById(R.id.team_grid_view);
        mViewModel.getTournamentData(tID).observe(getViewLifecycleOwner(), new Observer<Tournament>() {
            @Override
            public void onChanged(Tournament tournament) {
                if(tournament!=null){
                    tv_tournament_name.setText(tournament.getTournament_name());
                    tv_location.setText(tournament.getTournament_location());
                    tv_tournament_date.setText(getDateTime(tournament.getTournament_start_time())+" - "+getDateTime(tournament.getTournament_end_time()));
                    llTournament.setVisibility(View.VISIBLE);
                    llNewMatch.setVisibility(View.GONE);
                    tStartdate=tournament.getTournament_start_time();
                    tenddate=tournament.getTournament_end_time();
                }else{
                    llNewMatch.setVisibility(View.VISIBLE);
                    llTournament.setVisibility(View.GONE);
                }
            }
        });

        final Button btn_CreateTournament=rootView.findViewById(R.id.btn_CreateTournament);
        btn_CreateTournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewTournamentActivity.class));
            }
        });

        btn_create_team=rootView.findViewById(R.id.btn_create_team);

        btn_create_team.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                CreateNewTeamFragment createNewTeamFragment=new CreateNewTeamFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("ConfirmDialog");
                if (prev == null) {
                    ft.addToBackStack(null);
                    createNewTeamFragment.show(ft, "ConfirmDialog");
                }
            }
        });

        int teamCount=mteamViewModel.getTeamCount(sessionManager.getTournamentId());
        if(teamCount<6){
            btn_create_team.setEnabled(true);
        }else
        {
            btn_create_team.setEnabled(false);
        }

        mViewModel.getMatchScheduleState().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer==1){
                    btn_start_match.setEnabled(false);
                }else{
                    btn_start_match.setEnabled(true);
                }
            }
        });

        mteamViewModel.getAllTeams(sessionManager.getTournamentId()).observe(getActivity(), new Observer<List<Teams>>() {
            @Override
            public void onChanged(List<Teams> teams) {
                Log.i("TEAMSIZE",""+teams.size());
                if(teams.size()>0){
                    teamList.clear();
                    teamList.addAll(teams);
                    team_grid_view.setVisibility(View.VISIBLE);
                    llNoTeams.setVisibility(View.GONE);
                    teamAdapter = new TeamAdapter(getContext(), teams);
                    team_grid_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    team_grid_view.setAdapter(teamAdapter);
                    team_grid_view.setHasFixedSize(true);
                    //ScheduleMatch(teams);

                }else
                {
                    team_grid_view.setVisibility(View.GONE);
                    llNoTeams.setVisibility(View.VISIBLE);
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
                       mViewModel.updateMatchScheduleState(1,Integer.parseInt(sessionManager.getTournamentId()));
                       //btn_start_match.setEnabled(false);
                   }
                } else{
                    Toast.makeText(getContext(), "Please create 6 team and start schedule.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*final int speedScroll = 1200;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            boolean flag = true;

            @Override
            public void run() {
                if (count <teamAdapter.getItemCount()) {
                    if (count == teamAdapter.getItemCount() - 1) {
                        flag = false;
                    } else if (count == 0) {
                        flag = true;
                    }
                    if (flag) count++;
                    else count--;
                    team_grid_view.smoothScrollToPosition(count);
                    handler.postDelayed(this, speedScroll);
                }
            }
        };
        handler.postDelayed(runnable, speedScroll);*/

        return rootView;
    }
    private String getDateTime(long i){
        final Date date = new Date(i);
        return formatter.format(date);
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

    @Override
    public void onResume() {
        super.onResume();
        int teamCount=mteamViewModel.getTeamCount(sessionManager.getTournamentId());
        if(teamCount<6){
            btn_create_team.setEnabled(true);
        }else
        { btn_create_team.setEnabled(false);
        }

    }
}