package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.activity.MatchDetailActivity;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.MyViewHolder> {

    private Context context;
    private List<Matches> matchesList;
    private SessionManager sessionManager;
    private SimpleDateFormat formatter;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtGroupName;
        private TextView txtMatchType;
        private TextView txtDate;
        private TextView txtMatchStatus;
        private TextView txtTeamNameA;
        private TextView txtScoreA;
        private TextView txtTeamNameB;
        private TextView txtScoreB;
        private TextView txtMatchLocation;
        private TextView txtMatchResult;
        private CircleImageView img_teamA;
        private CircleImageView img_teamB;
        private LinearLayout llMatch;
        public MyViewHolder(View view) {
            super(view);
            txtGroupName = view.findViewById(R.id.txtGroupName);
            txtMatchType=view.findViewById(R.id.txtMatchType);
            txtDate=view.findViewById(R.id.txtDate);
            txtMatchStatus=view.findViewById(R.id.txtMatchStatus);
            txtTeamNameA=view.findViewById(R.id.txtTeamNameA);
            txtScoreA=view.findViewById(R.id.txtScoreA);
            txtTeamNameB=view.findViewById(R.id.txtTeamNameB);
            txtScoreB=view.findViewById(R.id.txtScoreB);
            txtMatchLocation=view.findViewById(R.id.txtMatchLocation);
            txtMatchResult=view.findViewById(R.id.txtMatchResult);
            img_teamA=view.findViewById(R.id.img_teamA);
            img_teamB=view.findViewById(R.id.img_teamB);
            llMatch=view.findViewById(R.id.llMatch);
        }
    }

    public MatchListAdapter(List<Matches> matchesList, Context context) {
        this.matchesList = matchesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MatchListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_list_items, parent, false);
        sessionManager=new SessionManager(context);
        formatter = new SimpleDateFormat("dd MMM yyyy");
        return new MatchListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchListAdapter.MyViewHolder holder, int position) {
        final Matches model = matchesList.get(position);

        String teamName[]=model.getMatch_details().split(":");

        holder.txtTeamNameA.setText(teamName[0]);
        holder.txtTeamNameB.setText(teamName[1]);
        holder.img_teamA.setImageDrawable(context.getDrawable(R.drawable.team_1));;
        holder.img_teamB.setImageDrawable(context.getDrawable(R.drawable.team_2));
        holder.txtDate.setText(formatter.format(model.getMatch_date()));
        holder.txtMatchLocation.setText(model.getMatch_venue());
        if(model.getMatch_status()==0){
        holder.txtMatchStatus.setText("");
        holder.txtScoreB.setText("");
        holder.txtScoreA.setText("");
        holder.txtMatchResult.setText("");
        }else if(model.getMatch_status()==1){
            holder.txtMatchStatus.setText("Live");
        }else
        {
            holder.txtMatchStatus.setText("Completed");
            holder.txtScoreA.setText("165-5");
            holder.txtScoreB.setText("166-5");
            holder.txtMatchResult.setText(teamName[1]+"Won by 5 Wickets");
        }

        holder.llMatch.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                Intent intent=new Intent(context, MatchDetailActivity.class);
                intent.putExtra("TA",teamName[0]);
                intent.putExtra("TB",teamName[1]);
                intent.putExtra("MNO",model.getMatch_no());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }

}
