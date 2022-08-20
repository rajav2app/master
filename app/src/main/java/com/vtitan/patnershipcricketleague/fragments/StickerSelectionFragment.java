package com.vtitan.patnershipcricketleague.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.activity.ScoreActivity;
import com.vtitan.patnershipcricketleague.adapter.BattingTeamPlayerAdapter;
import com.vtitan.patnershipcricketleague.adapter.BowlingTeamPlayerAdapter;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.MatchViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

public class StickerSelectionFragment extends DialogFragment {
    public StickerSelectionFragment() {
        // Required empty public constructor
    }
    private int teamA;
    private int teamB;
    private int matchNo;
    private MatchViewModel matchViewModel;
    private TeamViewModel mViewModel;
    private List<String>battingPlayerList=new ArrayList<>();
    private List<String >bowlingPlayerList=new ArrayList<>();
    private SessionManager sessionManager;
    private int strickerId=-1;
    private int nonStrickerId=-1;
    private int bowlerId=-1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_sticker_selection, container, false);
        Bundle bundle=getArguments();
        if(bundle!=null){
          teamA=bundle.getInt("TA");
          teamB=bundle.getInt("TB");
          matchNo=bundle.getInt("matchNo");
        }

        sessionManager=new SessionManager(getContext());
        mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        matchViewModel = new ViewModelProvider(this).get(MatchViewModel.class);

        List<Players>btplayers=mViewModel.getBTPlayers(teamA);
        List<Players>bowtplayers=mViewModel.getBTPlayers(teamB);
        for(Players playerNames : btplayers)
        {
          battingPlayerList.add(playerNames.getPlayer_name());
        }
        for(Players bowlingPlayers : bowtplayers){
            bowlingPlayerList.add(bowlingPlayers.getPlayer_name());
        }
        final AutoCompleteTextView stricker=rootView.findViewById(R.id.stickerName);
        final AutoCompleteTextView nonStricker=rootView.findViewById(R.id.nonstickerName);
        final AutoCompleteTextView bowlerName=rootView.findViewById(R.id.bowlerName);

        final ImageButton ibtn_close=rootView.findViewById(R.id.ibtn_close);
        ibtn_close.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                dismiss();
            }
        });


           final ArrayAdapter<String> strickerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, battingPlayerList);
        strickerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stricker.setAdapter(strickerAdapter);
        stricker.setThreshold(1);

        stricker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strickerId=btplayers.get(i).getPlayerID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        final ArrayAdapter<String> nonStrickerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, battingPlayerList);
        nonStrickerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nonStricker.setAdapter(nonStrickerAdapter);
        nonStricker.setThreshold(1);

        nonStricker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              nonStrickerId=btplayers.get(i).getPlayerID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final ArrayAdapter<String> bowlingAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,bowlingPlayerList);
        bowlingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bowlerName.setAdapter(bowlingAdapter);
        bowlerName.setThreshold(1);
        bowlerName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bowlerId=btplayers.get(i).getPlayerID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Button btn_save=rootView.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                String str=stricker.getText().toString().trim();


            }
        });
        return rootView;
    }


}