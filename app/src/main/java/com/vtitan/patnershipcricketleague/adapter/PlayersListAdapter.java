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
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.SessionManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayersListAdapter extends RecyclerView.Adapter<PlayersListAdapter.MyViewHolder> {

    private Context context;
    private List<Players> playersList;
    private SessionManager sessionManager;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtState;
        private TextView txtPlayerName;
        private CircleImageView img_team;
        public MyViewHolder(View view) {
            super(view);
            txtState = view.findViewById(R.id.txtState);
            txtPlayerName=view.findViewById(R.id.txtPlayerName);
            img_team=view.findViewById(R.id.img_team);
        }
    }

    public PlayersListAdapter(List<Players> playersList, Context context) {
        this.playersList = playersList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlayersListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list_item, parent, false);
        sessionManager=new SessionManager(context);
        return new PlayersListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersListAdapter.MyViewHolder holder, int position) {
        final Players model = playersList.get(position);

        holder.txtPlayerName.setText(model.getPlayer_name());
        if(model.getPlayer_state()==0){
        holder.txtState.setText("C");
        }else{
            holder.txtState.setText("VC");
        }
        if(position%2==0) {
            holder.img_team.setImageDrawable(context.getDrawable(R.drawable.team_2));
        }else {
            holder.img_team.setImageDrawable(context.getDrawable(R.drawable.team_1));
        }

    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

}
