package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.MyViewHolder> {

    private Context context;
    private List<Teams> teamsList;
    private SessionManager sessionManager;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTeamName;
        private TextView txtR1;
        private TextView txtR2;
        private TextView txtR3;
        private TextView txtR4;
        private TextView txtPoints;
        private TextView txtR5;
        private CircleImageView img_team;
        public MyViewHolder(View view) {
            super(view);
            txtTeamName = view.findViewById(R.id.txtTeamName);
            txtR1=view.findViewById(R.id.txtR1);
            txtR2=view.findViewById(R.id.txtR2);
            txtR3=view.findViewById(R.id.txtR3);
            txtR4=view.findViewById(R.id.txtR4);
            txtR5=view.findViewById(R.id.txtR5);
            txtPoints=view.findViewById(R.id.txtPoints);
            img_team=view.findViewById(R.id.img_team);
        }
    }

    public PointsAdapter(List<Teams> teamsList, Context context) {
        this.teamsList = teamsList;
        this.context = context;
    }

    @NonNull
    @Override
    public PointsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.point_list_items, parent, false);
        sessionManager=new SessionManager(context);
        return new PointsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PointsAdapter.MyViewHolder holder, int position) {
        final Teams model = teamsList.get(position);

        holder.txtTeamName.setText(model.getTeam_name());
        //holder.txtMatches.setText(model.getTeam_location());
        if(position%2==0){
            holder.img_team.setImageDrawable(context.getDrawable(R.drawable.team_1));;
        }else{
            holder.img_team.setImageDrawable(context.getDrawable(R.drawable.team_2));
        }

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
