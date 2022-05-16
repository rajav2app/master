package com.vtitan.patnershipcricketleague.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.adapter.MatchListAdapter;
import com.vtitan.patnershipcricketleague.adapter.PlayersListAdapter;
import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;

import java.util.List;

public class PlayersFragment extends Fragment {

    public static PlayersFragment newInstance() {
        PlayersFragment fragment = new PlayersFragment();
        return fragment;
    }
    private Button btn_players;
    private Button btn_create_players;
    private SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView=inflater.inflate(R.layout.fragment_players, container, false);
        sessionManager=new SessionManager(getContext());
        final TeamViewModel mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        final LinearLayout llNewPlayers=rootView.findViewById(R.id.llNewPlayers);
        final LinearLayout llPlayersList=rootView.findViewById(R.id.llPlayersList);
        btn_create_players=rootView.findViewById(R.id.btn_create_players);
        final RecyclerView rv_players=rootView.findViewById(R.id.rv_players);
        btn_players=rootView.findViewById(R.id.btn_players);

        btn_create_players.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                CreatePlayerFragment createPlayerFragment=new CreatePlayerFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("ConfirmDialog");
                if (prev == null) {
                    ft.addToBackStack(null);
                    createPlayerFragment.show(ft, "CreatePlayerDialog");
                }
            }
        });

        final PlayersListAdapter.editClickListener listener=new PlayersListAdapter.editClickListener() {
            @Override
            public void onItemClick(View v, Players player) {
                Bundle bundle=new Bundle();
                bundle.putString(getString(R.string.key_player_name),player.getPlayer_name());
                bundle.putInt(getString(R.string.key_player_state),player.getPlayer_state());
                bundle.putInt(getString(R.string.key_player_id),player.getPlayerID());
                CreatePlayerFragment createPlayerFragment=new CreatePlayerFragment();
                createPlayerFragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("ConfirmDialog");
                if (prev == null) {
                    ft.addToBackStack(null);
                    createPlayerFragment.show(ft, "CreatePlayerDialog");
                }
            }
        };
       mViewModel.getAllPlayers(sessionManager.getTeamId()).observe(getActivity(), new Observer<List<Players>>() {
           @Override
           public void onChanged(List<Players> players) {
               if(players.size()>0){
                   PlayersListAdapter playersListAdapter = new PlayersListAdapter(players,getContext());
                   rv_players.setLayoutManager(new LinearLayoutManager(getContext()));
                   rv_players.setAdapter(playersListAdapter);
                   playersListAdapter.setListener(listener);
                   rv_players.setHasFixedSize(true);
                   llPlayersList.setVisibility(View.VISIBLE);
                   llNewPlayers.setVisibility(View.GONE);
               }else
               {
                   llPlayersList.setVisibility(View.GONE);
                   llNewPlayers.setVisibility(View.VISIBLE);
                   btn_create_players.setVisibility(View.GONE);

               }
           }
       });

        btn_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatePlayerFragment createPlayerFragment=new CreatePlayerFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("ConfirmDialog");
                if (prev == null) {
                    ft.addToBackStack(null);
                    createPlayerFragment.show(ft, "CreatePlayerDialog");
                }
            }
        });

        mViewModel.getPlayerCount(sessionManager.getTeamId()).observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer playerCount) {
                if(playerCount<2){
                    btn_players.setVisibility(View.VISIBLE);
                }else{
                    btn_players.setVisibility(View.GONE);
                }
            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}