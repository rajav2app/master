package com.vtitan.patnershipcricketleague.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.activity.MainActivity;
import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.model.Tournament;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TournamentListAdapter extends RecyclerView.Adapter<TournamentListAdapter.MyViewHolder> {

    private Context context;
    private List<Tournament> tournamentList;
    private SessionManager sessionManager;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTournamentName;
        private ImageView bannerimg;
        private LinearLayout llLoadTournament;
        public MyViewHolder(View view) {
            super(view);
            tvTournamentName = view.findViewById(R.id.tvTournamentName);
            bannerimg=view.findViewById(R.id.bannerimg);
            llLoadTournament=view.findViewById(R.id.llLoadTournament);
        }
    }

    public TournamentListAdapter(List<Tournament> tournaments, Context context) {
        this.tournamentList = tournaments;
        this.context = context;
    }

    @NonNull
    @Override
    public TournamentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout, parent, false);
        sessionManager=new SessionManager(context);
        return new TournamentListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TournamentListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Tournament model = tournamentList.get(position);

        holder.tvTournamentName.setText(model.getTournament_name());
        if(position%2==0) {
            holder.bannerimg.setImageDrawable(context.getDrawable(R.drawable.banner1));
        }else {
            holder.bannerimg.setImageDrawable(context.getDrawable(R.drawable.banner2));
        }

        holder.llLoadTournament.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                sessionManager.saveTournamentID(String.valueOf(tournamentList.get(position).getTournamentID()));
                Intent intent=new Intent(context, MainActivity.class);
                //intent.putExtra(context.getString(R.string.key_tid) ,tournamentList.get(position).getTournamentID());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tournamentList.size();
    }

}

