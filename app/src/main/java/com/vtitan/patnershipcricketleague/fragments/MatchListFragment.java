package com.vtitan.patnershipcricketleague.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.adapter.MatchListAdapter;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.MatchViewModel;

import java.util.ArrayList;
import java.util.List;


public class MatchListFragment extends Fragment {


    public static MatchListFragment newInstance() {
        MatchListFragment fragment = new MatchListFragment();
        return fragment;
    }
    private List<Matches> matchesList=new ArrayList<>();
    private MatchListAdapter matchListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_match_list, container, false);
       final SessionManager sessionManager=new SessionManager(getContext());
        final MatchViewModel matchViewModel = new ViewModelProvider(this).get(MatchViewModel.class);
        final RecyclerView rv_matches=rootView.findViewById(R.id.rv_matches);

        matchViewModel.getTeamMatches(sessionManager.getTeamId()).observe(getActivity(), new Observer<List<Matches>>() {
            @Override
            public void onChanged(List<Matches> matches) {
                if(matches.size()>0){
                    matchListAdapter = new MatchListAdapter(matches,getContext() );
                    rv_matches.setLayoutManager(new LinearLayoutManager(getContext()));
                    rv_matches.setAdapter(matchListAdapter);
                    rv_matches.setHasFixedSize(true);

                }

            }
        });
        return rootView;
    }
}