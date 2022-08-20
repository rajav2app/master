package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.vtitan.patnershipcricketleague.activity.ScoreActivity;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.BasicFunction;
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

        private TextView txtMatchNo;
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
        private TextView txtTime;
        private TextView txtRound;
        public MyViewHolder(View view) {
            super(view);
            txtMatchNo = view.findViewById(R.id.txtMatchNo);
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
            txtTime=view.findViewById(R.id.txtTime);
            txtRound=view.findViewById(R.id.txtRound);
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

        holder.txtTeamNameA.setText(model.getTeam_name_home());
        holder.txtTeamNameB.setText(model.getTeam_name_away());
        holder.img_teamA.setImageDrawable(context.getDrawable(R.drawable.team_1));;
        holder.img_teamB.setImageDrawable(context.getDrawable(R.drawable.team_2));
        holder.txtDate.setText(formatter.format(model.getMatch_date()));
        holder.txtMatchLocation.setText(model.getMatch_venue());
        holder.txtMatchNo.setText("Match.No : "+model.getMatch_no());
        holder.txtTime.setText(BasicFunction.longTohms(model.getMatch_date()));

       if(model.getMatch_status()==1){
            holder.txtMatchStatus.setText("Live");
           holder.txtMatchStatus.setTextColor(Color.GREEN);
          // holder.txtScoreA.setText();
        }else if(model.getMatch_status()==2)
        {
            holder.txtMatchStatus.setText("Completed");
            holder.txtMatchStatus.setTextColor(Color.RED);
            holder.txtScoreA.setText("165-5");
            holder.txtScoreB.setText("166-5");
            holder.txtMatchResult.setText(teamName[1]+"Won by 5 Wickets");
        }else {
           holder.txtMatchStatus.setText("");
           holder.txtScoreB.setText("");
           holder.txtScoreA.setText("");
           holder.txtMatchResult.setText("");
       }

        holder.txtRound.setVisibility(View.VISIBLE);
        holder.setIsRecyclable(false);
        switch (model.getMatch_no()){
            case 1:
                holder.txtRound.setText("Round 1");
                break;
            case 2:
                holder.txtRound.setVisibility(View.GONE);
                break;
            case 3:
                holder.txtRound.setVisibility(View.GONE);
                break;
            case 4:
                holder.txtRound.setText("Round 2");
                break;
            case 5:
                holder.txtRound.setVisibility(View.GONE);
                break;
            case 6:
                holder.txtRound.setVisibility(View.GONE);
                break;
            case 7:
                holder.txtRound.setText("Round 3");
                break;
            case 8:
                holder.txtRound.setVisibility(View.GONE);
                break;
            case 9:
                holder.txtRound.setVisibility(View.GONE);
                break;
            case 10:
                holder.txtRound.setText("Round 4");
                break;
            case 11:
                holder.txtRound.setVisibility(View.GONE);
                break;
            case 12:
                holder.txtRound.setVisibility(View.GONE);
                break;
            case 13:
                holder.txtRound.setText("Round 5");
                break;
            case 14:
                holder.txtRound.setVisibility(View.GONE);
                break;
            case 15:
                holder.txtRound.setVisibility(View.GONE);
                break;
        }

        holder.llMatch.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                if(model.getMatch_status()==1){
                    Intent intent=new Intent(context, ScoreActivity.class);
                    intent.putExtra("TA",model.getTeam_name_home());
                    intent.putExtra("TB",model.getTeam_name_away());
                    intent.putExtra("MNO",model.getMatch_no());
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, MatchDetailActivity.class);
                    intent.putExtra("TA", model.getTeam_name_home());
                    intent.putExtra("TB", model.getTeam_name_away());
                    intent.putExtra("MNO", model.getMatch_no());
                    context.startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }

}
