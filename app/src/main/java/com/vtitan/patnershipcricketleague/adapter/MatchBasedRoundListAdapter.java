package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.SessionManager;

import java.util.List;

public class MatchBasedRoundListAdapter extends RecyclerView.Adapter<MatchBasedRoundListAdapter.MyViewHolder> {
    private Context context;
    private List<Matches> MatchesList;
    private List<Teams> teamsList;
    private List<String >roundList;
    private SessionManager sessionManager;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtRound;
        private RecyclerView rv_match_list;
        public MyViewHolder(View view) {
            super(view);
            txtRound = view.findViewById(R.id.txtRound);
            rv_match_list=view.findViewById(R.id.rv_match_list);
        }
    }

    public MatchBasedRoundListAdapter(List<String>roundList, List<Matches> matchesList,List<Teams>teamsList,Context context) {
        this.MatchesList = matchesList;
        this.roundList=roundList;
        this.teamsList=teamsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MatchBasedRoundListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_based_round_item, parent, false);
        sessionManager=new SessionManager(context);
        return new MatchBasedRoundListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchBasedRoundListAdapter.MyViewHolder holder, int position) {
        holder.txtRound.setText(roundList.get(position));
        if(position==1) {
           MatchListAdapter matchListAdapter = new MatchListAdapter(MatchesList, context);
            holder.rv_match_list.setLayoutManager(new LinearLayoutManager(context));
            holder.rv_match_list.setAdapter(matchListAdapter);
            holder.rv_match_list.setHasFixedSize(true);
       }
      /*PointsAdapter pointsAdapter = new PointsAdapter(teamsList,context );
        holder.rv_match_list.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_match_list.setAdapter(pointsAdapter);
        holder.rv_match_list.setHasFixedSize(true);*/
    }

    @Override
    public int getItemCount() {
        return roundList.size();
    }

}
