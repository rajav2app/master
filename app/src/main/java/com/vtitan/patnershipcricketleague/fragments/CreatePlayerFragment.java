package com.vtitan.patnershipcricketleague.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;

public class CreatePlayerFragment extends DialogFragment {

    public CreatePlayerFragment() {
        // Required empty public constructor
    }

    private int playerState=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_create_player, container, false);

        final EditText etPlayerName=rootView.findViewById(R.id.etPlayerName);
        final Button btn_CreatePlayers=rootView.findViewById(R.id.btn_CreatePlayers);
        final ImageButton ibtn_close=rootView.findViewById(R.id.ibtn_close);
        final TeamViewModel mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        final RadioButton rb_c = rootView.findViewById(R.id.rb_c);
        final RadioButton rb_vc = rootView.findViewById(R.id.rb_vc);
        final SessionManager sessionManager=new SessionManager(getContext());
        ibtn_close.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                dismiss();
            }
        });

        btn_CreatePlayers.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

                if(etPlayerName.getText().toString().trim().isEmpty()){
                    etPlayerName.setError("Player name can't be empty.");
                }else {
                              Players players = new Players();
                                players.setTeam_id(sessionManager.getTeamId());
                                players.setPlayer_name(etPlayerName.getText().toString().trim());
                                players.setPlayer_state(playerState);
                                long result = mViewModel.insertPlayers(players);
                                if (result == -1) {
                                    Toast.makeText(getContext(), "Player addition failed.", Toast.LENGTH_SHORT).show();
                                    dismiss();
                                } else {
                                    Toast.makeText(getContext(), "Player added successfully.", Toast.LENGTH_SHORT).show();
                                    dismiss();
                                }
                        }

            }
        });

        rb_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerState = 0;
            }
        });
        rb_vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerState = 1;
            }
        });


        return rootView;
    }
}