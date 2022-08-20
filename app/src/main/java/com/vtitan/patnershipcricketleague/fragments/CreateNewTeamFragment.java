package com.vtitan.patnershipcricketleague.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
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
import com.vtitan.patnershipcricketleague.viewmodel.MatchViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;

public class CreateNewTeamFragment extends DialogFragment {

    public CreateNewTeamFragment() {
        // Required empty public constructor
    }
    private int teamCount=0;
    private int team_id;
    private String teamName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView= inflater.inflate(R.layout.fragment_create_new_team, container, false);
        Bundle bundle=getArguments();
       final ImageButton ibtn_close=rootView.findViewById(R.id.ibtn_close);
       final EditText etTeamName=rootView.findViewById(R.id.etTeamName);
       final EditText etLocation=rootView.findViewById(R.id.etLocation);
       final TeamViewModel mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        final MatchViewModel matchViewModel = new ViewModelProvider(this).get(MatchViewModel.class);
        SessionManager sessionManager=new SessionManager(getContext());
       final Button btn_CreateTeam=rootView.findViewById(R.id.btn_CreateTeam);
       if(bundle!=null){
            teamName=bundle.getString(getString(R.string.key_team_name));
            String teamLocation=bundle.getString(getString(R.string.key_team_location));
            team_id=bundle.getInt(getString(R.string.key_team_id));
            etTeamName.setText(teamName);
            etLocation.setText(teamLocation);
            btn_CreateTeam.setText(R.string.txt_save);
        }

        mViewModel.getTeamCount(sessionManager.getTournamentId()).observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer teamCount) {
                teamCount=teamCount;
            }
        });
        btn_CreateTeam.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

                if(etTeamName.getText().toString().trim().isEmpty()){
                    etTeamName.setError("Team name can't be empty.");
                }else if(etLocation.getText().toString().trim().isEmpty()){
                    etLocation.setError("Location can't be empty.");
                }else {
                    if (bundle == null) {
                        Teams teamModel = new Teams();
                        teamModel.setTeam_name(etTeamName.getText().toString().trim());
                        teamModel.setTeam_location(etLocation.getText().toString().trim());
                        teamModel.setT_ID(sessionManager.getTournamentId());
                        // int teamCount=mViewModel.getTeamCount(sessionManager.getTournamentId());

                        long res = mViewModel.insertTeam(teamModel);
                        if (res == -1) {
                            Toast.makeText(getContext(), "Team creation failed", Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Toast.makeText(getContext(), "Team created successfully.", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }
                    }
                    else
                    {
                        int res = mViewModel.updateTeamDetails(etTeamName.getText().toString().trim(),etLocation.getText().toString().trim(),team_id);
                        if(teamName!=null){
                            matchViewModel.updateawayTeamDetails(etTeamName.getText().toString().trim(),teamName);
                            matchViewModel.updateMatchDetails(etTeamName.getText().toString().trim(),teamName);

                        }


                        if (res <0) {
                            Toast.makeText(getContext(), "Team details updation failed", Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Toast.makeText(getContext(), "Team details updated successfully.", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }
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