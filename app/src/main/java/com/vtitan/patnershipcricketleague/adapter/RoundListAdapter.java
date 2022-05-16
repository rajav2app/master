package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.SessionManager;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class RoundListAdapter extends RecyclerView.Adapter<RoundListAdapter.MyViewHolder> {
    private Context context;
    private List<Matches> MatchesList;
    private List<String >roundList;
    private List<Teams>teamsList;
    private SessionManager sessionManager;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtRound;
        private RecyclerView rv_points;
        public MyViewHolder(View view) {
            super(view);
            txtRound = view.findViewById(R.id.txtRound);
            rv_points=view.findViewById(R.id.rv_points);
        }
    }

    public RoundListAdapter(List<String>roundList, List<Matches> matchesList, List<Teams>teamsList,Context context) {
        this.MatchesList = matchesList;
        this.roundList=roundList;
        this.teamsList=teamsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RoundListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.round_item, parent, false);
        sessionManager=new SessionManager(context);
        return new RoundListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoundListAdapter.MyViewHolder holder, int position) {
        holder.txtRound.setText(roundList.get(position));
        PointsAdapter pointsAdapter = new PointsAdapter(teamsList,context );
        holder.rv_points.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_points.setAdapter(pointsAdapter);
        holder.rv_points.setHasFixedSize(true);
    }

    @Override
    public int getItemCount() {
        return roundList.size();
    }

}
