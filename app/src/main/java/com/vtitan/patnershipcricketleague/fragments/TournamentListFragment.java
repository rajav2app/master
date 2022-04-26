package com.vtitan.patnershipcricketleague.fragments;

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

import com.google.gson.Gson;
import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.adapter.PlayersListAdapter;
import com.vtitan.patnershipcricketleague.adapter.TournamentListAdapter;
import com.vtitan.patnershipcricketleague.adapter.TournamentSliderAdapter;
import com.vtitan.patnershipcricketleague.model.Tournament;
import com.vtitan.patnershipcricketleague.viewmodel.TournamentViewModel;

import java.util.List;


public class TournamentListFragment extends Fragment {


    public TournamentListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TournamentListFragment newInstance() {
        TournamentListFragment fragment = new TournamentListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView=inflater.inflate(R.layout.fragment_tournament_list, container, false);
        final TournamentViewModel mViewModel = new ViewModelProvider(this).get(TournamentViewModel.class);
        final LinearLayout llNewtournament=rootView.findViewById(R.id.llNewtournament);
        final LinearLayout llTournamentList=rootView.findViewById(R.id.llTournamentList);
        final RecyclerView rv_tournament=rootView.findViewById(R.id.rv_tournament);
        final Button btn_createTournament=rootView.findViewById(R.id.btn_createTournament);
        mViewModel.getAllTournament().observe(getActivity(), new Observer<List<Tournament>>() {
            @Override
            public void onChanged(List<Tournament> tournaments) {
                if(tournaments.size()>0){
                    TournamentListAdapter tournamentSliderAdapter = new TournamentListAdapter(tournaments,getContext());
                    rv_tournament.setLayoutManager(new LinearLayoutManager(getContext()));
                    rv_tournament.setAdapter(tournamentSliderAdapter);
                    rv_tournament.setHasFixedSize(true);
                    llTournamentList.setVisibility(View.VISIBLE);
                    llNewtournament.setVisibility(View.GONE);
                }else
                {
                    llTournamentList.setVisibility(View.GONE);
                    llNewtournament.setVisibility(View.VISIBLE);

                }
            }
        });

        return rootView;
    }
}