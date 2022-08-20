package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BattingTeamPlayerAdapter extends RecyclerView.Adapter<BattingTeamPlayerAdapter.MyViewHolder> {

    private Context context;
    private List<Players> playersList;
    private SessionManager sessionManager;
    private PlayersListAdapter.editClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtPlayerName;
        private TextView txtR;
        private TextView txtB;
        private TextView txt4s;
        private TextView txt6s;
        private TextView txtSR;
        public MyViewHolder(View view) {
            super(view);
            txtPlayerName = view.findViewById(R.id.txtPlayerName);
            txtR=view.findViewById(R.id.txtR);
            txtB=view.findViewById(R.id.txtB);
            txt4s=view.findViewById(R.id.txt4s);
            txt6s=view.findViewById(R.id.txt6s);
            txtSR=view.findViewById(R.id.txtSR);


        }
    }

    public BattingTeamPlayerAdapter(List<Players> playersList, Context context) {
        this.playersList = playersList;
        this.context = context;
    }

    @NonNull
    @Override
    public BattingTeamPlayerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.batting_team_list_item, parent, false);
        sessionManager=new SessionManager(context);
        return new BattingTeamPlayerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BattingTeamPlayerAdapter.MyViewHolder holder, int position) {
        final Players model = playersList.get(position);
        holder.txtPlayerName.setText(model.getPlayer_name());
        holder.txtR.setText(""+model.getRuns());
        //holder.txtB.setText(""+model.getOvers());
        holder.txt4s.setText(""+model.getFours());
        holder.txt6s.setText(""+model.getSixes());
        //holder.txtSR.setText(""+model.getRuns());
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

}

