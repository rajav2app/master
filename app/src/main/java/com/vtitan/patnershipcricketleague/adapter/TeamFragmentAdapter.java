package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.activity.TeamDetailsActivity;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.SessionManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeamFragmentAdapter extends RecyclerView.Adapter<TeamFragmentAdapter.MyViewHolder> {

    private Context context;
    private List<Teams> teamsList;
    private SessionManager sessionManager;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTeamName;
        private TextView txtTeamLocation;
        private CircleImageView img_team;
        private LinearLayout llTeamList;


        public MyViewHolder(View view) {
            super(view);
            txtTeamName = view.findViewById(R.id.txtTeamName);
            txtTeamLocation=view.findViewById(R.id.txtTeamLocation);
            img_team=view.findViewById(R.id.img_team);
            llTeamList = (LinearLayout) view.findViewById(R.id.llTeamList);
        }
    }

    public TeamFragmentAdapter(List<Teams> teamsList, Context context) {
        this.teamsList = teamsList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeamFragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_list_items, parent, false);
        sessionManager=new SessionManager(context);
        return new TeamFragmentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamFragmentAdapter.MyViewHolder holder, int position) {
        final Teams model = teamsList.get(position);

        holder.txtTeamName.setText(model.getTeam_name());
        holder.txtTeamLocation.setText(model.getTeam_location());

        if(position%2==0){
            holder.img_team.setImageDrawable(context.getDrawable(R.drawable.team_1));;
        }else{
            holder.img_team.setImageDrawable(context.getDrawable(R.drawable.team_2));
        }
        holder.llTeamList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Id = model.getTeamID();
                sessionManager.saveTeamID(Id);
                Log.d("ID", ""+Id);
                Intent intent = new Intent(context, TeamDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    public void filterList(List<Teams> filteredList) {
        teamsList = filteredList;
        notifyDataSetChanged();
    }

}

