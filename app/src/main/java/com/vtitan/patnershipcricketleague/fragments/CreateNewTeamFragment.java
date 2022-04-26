package com.vtitan.patnershipcricketleague.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;

public class CreateNewTeamFragment extends DialogFragment {

    public CreateNewTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView= inflater.inflate(R.layout.fragment_create_new_team, container, false);

       final ImageButton ibtn_close=rootView.findViewById(R.id.ibtn_close);
       final EditText etTeamName=rootView.findViewById(R.id.etTeamName);
       final EditText etLocation=rootView.findViewById(R.id.etLocation);
       final TeamViewModel mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        SessionManager sessionManager=new SessionManager(getContext());
       final Button btn_CreateTeam=rootView.findViewById(R.id.btn_CreateTeam);

        btn_CreateTeam.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

                if(etTeamName.getText().toString().trim().isEmpty()){
                    etTeamName.setError("Team name can't be empty.");
                }else if(etLocation.getText().toString().trim().isEmpty()){
                    etLocation.setError("Location can't be empty.");
                }else{
                    Teams teamModel=new Teams();
                    teamModel.setTeam_name(etTeamName.getText().toString().trim());
                    teamModel.setTeam_location(etLocation.getText().toString().trim());
                    teamModel.setT_ID(sessionManager.getTournamentId());
                    int teamCount=mViewModel.getTeamCount(sessionManager.getTournamentId());
                    if(teamCount<6){
                       long res= mViewModel.insertTeam(teamModel);
                       if(res==-1){
                           Toast.makeText(getContext(), "Team creation failed", Toast.LENGTH_SHORT).show();
                           dismiss();
                       }else
                       {
                           Toast.makeText(getContext(), "Team created successfully.", Toast.LENGTH_SHORT).show();
                           dismiss();
                       }
                    }else{
                        Toast.makeText(getContext(), "Team Exist for this tournament.", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }

                }
            }
        });

        ibtn_close.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
             dismiss();
            }
        });
        return rootView;
    }
}