package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.util.SessionManager;

import java.util.List;

public class BowlingTeamPlayerAdapter extends RecyclerView.Adapter<BowlingTeamPlayerAdapter.MyViewHolder> {

    private Context context;
    private List<Players> playersList;
    private SessionManager sessionManager;
    private PlayersListAdapter.editClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtPlayerName;
        private TextView txtO;
        private TextView txtM;
        private TextView txt_R;
        private TextView txtW;
        private TextView txtER;
        public MyViewHolder(View view) {
            super(view);
            txtPlayerName = view.findViewById(R.id.txtPlayerName);
            txtO=view.findViewById(R.id.txtO);
            txtM=view.findViewById(R.id.txtM);
            txt_R=view.findViewById(R.id.txt_R);
            txtW=view.findViewById(R.id.txtW);
            txtER=view.findViewById(R.id.txtER);


        }
    }

    public BowlingTeamPlayerAdapter(List<Players> playersList, Context context) {
        this.playersList = playersList;
        this.context = context;
    }

    @NonNull
    @Override
    public BowlingTeamPlayerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bowling_team_list_item, parent, false);
        sessionManager=new SessionManager(context);
        return new BowlingTeamPlayerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BowlingTeamPlayerAdapter.MyViewHolder holder, int position) {
        final Players model = playersList.get(position);
        holder.txtPlayerName.setText(model.getPlayer_name());
        holder.txtO.setText("0");
        holder.txtM.setText("0");
        holder.txt_R.setText("0");
        holder.txtW.setText("0");
        holder.txtER.setText("0");
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

}