package com.vtitan.patnershipcricketleague.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.adapter.MatchListAdapter;
import com.vtitan.patnershipcricketleague.adapter.PointsAdapter;
import com.vtitan.patnershipcricketleague.adapter.RoundListAdapter;
import com.vtitan.patnershipcricketleague.adapter.TeamFragmentAdapter;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.MatchViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

public class PointsFragment extends Fragment {
    public PointsFragment() {
        // Required empty public constructor
    }

    private List<String>roundList=new ArrayList<>();
    private List<Teams>teamList=new ArrayList<>();
    public static PointsFragment newInstance() {
        PointsFragment fragment = new PointsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_points, container, false);
        final RecyclerView rv_points=rootView.findViewById(R.id.rv_points);
        final RecyclerView rv_rounds=rootView.findViewById(R.id.rv_rounds);
        final TeamViewModel mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        SessionManager sessionManager=new SessionManager(getContext());
        final MatchViewModel matchViewModel = new ViewModelProvider(this).get(MatchViewModel.class);
        addRoundList();
        mViewModel.getAllTeams(sessionManager.getTournamentId()).observe(getActivity(), new Observer<List<Teams>>() {
            @Override
            public void onChanged(List<Teams> teams) {
                if(teams.size()>0){
                    teamList=teams;
                }

            }
        });

        matchViewModel.getMatches().observe(getActivity(), new Observer<List<Matches>>() {
            @Override
            public void onChanged(List<Matches> matches) {
                if(matches.size()>0){
                    RoundListAdapter roundListAdapter = new RoundListAdapter(roundList,matches,teamList,getContext() );
                    rv_rounds.setLayoutManager(new LinearLayoutManager(getContext()));
                    rv_rounds.setAdapter(roundListAdapter);
                    rv_rounds.setHasFixedSize(true);
                }
            }
        });

        return rootView;
    }

    private void addRoundList(){
        roundList.clear();
        roundList.add("Round 1");
        roundList.add("Round 2");
        roundList.add("Round 3");
        roundList.add("Round 4");
        roundList.add("Round 5");
    }
}