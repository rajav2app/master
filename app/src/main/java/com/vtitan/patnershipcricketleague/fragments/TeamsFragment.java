package com.vtitan.patnershipcricketleague.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.adapter.TeamAdapter;
import com.vtitan.patnershipcricketleague.adapter.TeamFragmentAdapter;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;

import java.util.List;

public class TeamsFragment extends Fragment {

    public TeamsFragment() {
        // Required empty public constructor
    }
    public static TeamsFragment newInstance() {
        TeamsFragment fragment = new TeamsFragment();
        return fragment;
    }
    TeamFragmentAdapter teamAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_teams, container, false);
        SessionManager sessionManager=new SessionManager(getContext());
        final LinearLayout llNewTeam=rootView.findViewById(R.id.llNewTeam);
        final Button btn_CreateTeam=rootView.findViewById(R.id.btn_CreateTeam);
        final LinearLayout llTeams=rootView.findViewById(R.id.llTeams);
        //final EditText etSearch=rootView.findViewById(R.id.etSearch);
        final RecyclerView team_grid_view=rootView.findViewById(R.id.team_grid_view);
        final TeamViewModel mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
       final Button btn_create_team=rootView.findViewById(R.id.btn_create_team);

        mViewModel.getAllTeams(sessionManager.getTournamentId()).observe(getActivity(), new Observer<List<Teams>>() {
            @Override
            public void onChanged(List<Teams> teams) {
                if(teams.size()>0){
                    //String tid=teams.get(0).getT_ID();
                    llNewTeam.setVisibility(View.GONE);
                    llTeams.setVisibility(View.VISIBLE);
                    teamAdapter = new TeamFragmentAdapter(teams,getContext() );
                    team_grid_view.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    team_grid_view.setAdapter(teamAdapter);
                    team_grid_view.setHasFixedSize(true);
                    if(teams.size()<6){
                        btn_create_team.setVisibility(View.VISIBLE);
                    }else{
                        btn_create_team.setVisibility(View.GONE);
                    }

                }else{
                    llNewTeam.setVisibility(View.VISIBLE);
                    llTeams.setVisibility(View.GONE);
                    btn_create_team.setVisibility(View.GONE);
                }

            }
        });

        btn_CreateTeam.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
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

        mViewModel.getTeamCount(sessionManager.getTournamentId()).observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer teamCount) {
                if(teamCount<6){
                    btn_create_team.setVisibility(View.VISIBLE);
                }else
                {
                    btn_create_team.setVisibility(View.GONE);;
                }

            }
        });


        btn_create_team.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
              //  final int teamCount = mViewModel.getTeamCount(sessionManager.getTournamentId());
              //  if (teamCount < 6) {
                    CreateNewTeamFragment createNewTeamFragment = new CreateNewTeamFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("ConfirmDialog");
                    if (prev == null) {
                        ft.addToBackStack(null);
                        createNewTeamFragment.show(ft, "ConfirmDialog");
                    }
                }/*else{
                    Toast.makeText(getContext(), getActivity().getString(R.string.txt_unable_to_create_more_team), Toast.LENGTH_SHORT).show();
                }*/
         //   }
        });
      /* final int teamCount=mViewModel.getTeamCount(sessionManager.getTournamentId());
       if(teamCount<6){
           // btn_CreateTeam.setVisibility(View.VISIBLE);
            btn_create_team.setVisibility(View.VISIBLE);
        }else{
           // btn_CreateTeam.setVisibility(View.GONE);
            btn_create_team.setVisibility(View.GONE);
        }*/

        return rootView;
    }
}