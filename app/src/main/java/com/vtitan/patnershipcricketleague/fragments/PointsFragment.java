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
import com.vtitan.patnershipcricketleague.adapter.PointsAdapter;
import com.vtitan.patnershipcricketleague.adapter.TeamFragmentAdapter;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;

import java.util.List;

public class PointsFragment extends Fragment {
    public PointsFragment() {
        // Required empty public constructor
    }

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
        final TeamViewModel mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        SessionManager sessionManager=new SessionManager(getContext());
        mViewModel.getAllTeams(sessionManager.getTournamentId()).observe(getActivity(), new Observer<List<Teams>>() {
            @Override
            public void onChanged(List<Teams> teams) {
                if(teams.size()>0){
                    String tid=teams.get(0).getT_ID();
                    PointsAdapter pointsAdapter = new PointsAdapter(teams,getContext() );
                    rv_points.setLayoutManager(new LinearLayoutManager(getContext()));
                    rv_points.setAdapter(pointsAdapter);
                    rv_points.setHasFixedSize(true);
                }

            }
        });
        return rootView;
    }
}