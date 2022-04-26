package com.vtitan.patnershipcricketleague.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.activity.TeamDetailsActivity;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>
{
        private static final String TAG = "RecyclerViewAdapter";
        private Context mContext;
        private List<Teams> teamsArrayList;
        private SessionManager sessionManager;

        public TeamAdapter(Context context, List<Teams> teamsArrayList) {
        this.teamsArrayList = teamsArrayList;
        this.mContext = context;
    }
        @Override
        public TeamAdapter.ViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_items, parent, false);
            sessionManager=new SessionManager(mContext);
        return new TeamAdapter.ViewHolder(view);
    }
        @Override
        public void onBindViewHolder (final TeamAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position){
            final Teams model = teamsArrayList.get(position);

        holder.txtTeamName.setText(teamsArrayList.get(position).getTeam_name());
        if(position%2==0){
            holder.img_team.setImageDrawable(mContext.getDrawable(R.drawable.team_1));;
        }else{
            holder.img_team.setImageDrawable(mContext.getDrawable(R.drawable.team_2));
        }

        holder.llTeam.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

                int Id = model.getTeamID();
                Log.i("TEAM_DETAILS",new Gson().toJson(model));
                sessionManager.saveTeamID(Id);
                Intent intent=new Intent(mContext, TeamDetailsActivity.class);
                mContext.startActivity(intent);
            }
        });
        //Picasso.with(mContext).load(teamsArrayList.get(position).getImageurl()).placeholder(R.drawable.ic_panorama_black).into(holder.profile_icon);
    }
        @Override
        public int getItemCount () {
        return teamsArrayList.size();
    }
        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView txtTeamName;
            private ImageView img_team;
            private View view;
            private LinearLayout llTeam;


            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                txtTeamName=view.findViewById(R.id.txtTeamName);
                img_team=view.findViewById(R.id.img_team);
                llTeam=view.findViewById(R.id.llTeam);
            }


        }
    }



