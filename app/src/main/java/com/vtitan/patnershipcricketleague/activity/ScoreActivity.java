package com.vtitan.patnershipcricketleague.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.adapter.BattingTeamPlayerAdapter;
import com.vtitan.patnershipcricketleague.adapter.BowlingTeamPlayerAdapter;
import com.vtitan.patnershipcricketleague.adapter.MatchListAdapter;
import com.vtitan.patnershipcricketleague.fragments.CreateNewTeamFragment;
import com.vtitan.patnershipcricketleague.fragments.StickerSelectionFragment;
import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.MatchViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {
    private String teamA;
    private String teamB;
    private int matchNo;
    private int run=0;
    private int wicket=0;
    private int battingTeam=-1;
    private int bowlingTeam=-1;
    private int totalRun=0;
    private int bat=-1;
    private int torunamentId;
    private List<String>currentOverScore=new ArrayList<>();
    private List<Players>battingPlayerList;
    private List<Players>bowlingPlayerList;
    private boolean isbattingFirstFlag=true;
    private boolean isbowlingFirstFlag=true;
    private int battingPlayerId;
    private int bowlingPlayerId;
    private TeamViewModel mViewModel;
    private RecyclerView rv_bowlers;
    private RecyclerView rv_batsman;
    private RecyclerView rv_current_score;
    private MatchViewModel matchViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_start_match));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.enter,R.anim.exit);
            }
        });
        mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        matchViewModel = new ViewModelProvider(this).get(MatchViewModel.class);
        final SessionManager sessionManager=new SessionManager(this);
        Intent intent=getIntent();
        if(intent!=null){
            teamA=intent.getStringExtra("TA");
            teamB=intent.getStringExtra("TB");
            matchNo=intent.getIntExtra("MNO",0);
        }
        torunamentId=Integer.parseInt(sessionManager.getTournamentId());
        if(battingPlayerList!=null){
           if(isbattingFirstFlag){
               battingPlayerId=battingPlayerList.get(0).getPlayerID();
               isbattingFirstFlag=false;
           }
        }
        if(bowlingPlayerList!=null){
            if(isbowlingFirstFlag){
                bowlingPlayerId=bowlingPlayerList.get(0).getPlayerID();
                isbowlingFirstFlag=false;
            }
        }
        final TextView txtTeamName=findViewById(R.id.txtTeamName);
        final TextView txtInnings=findViewById(R.id.txtInnings);
        final TextView txtScore=findViewById(R.id.txtScore);
        final TextView txtWickets=findViewById(R.id.txtWickets);
        final TextView txtCurrentOver=findViewById(R.id.txtCurrentOver);
        final TextView txtTotalOver=findViewById(R.id.txtTotalOver);
        final TextView txtcomment=findViewById(R.id.txtcomment);
        final TextView txtCurrentRR=findViewById(R.id.txtCurrentRR);
        final TextView txtRR=findViewById(R.id.txtRR);
        final TextView txtTR=findViewById(R.id.txtTR);
        final TextView txtR=findViewById(R.id.txtR);
        final TextView txtB=findViewById(R.id.txtB);
        rv_batsman=findViewById(R.id.rv_batsman);
        rv_bowlers=findViewById(R.id.rv_bowlers);
        rv_current_score=findViewById(R.id.rv_current_score);

        final RadioGroup rg_extras=findViewById(R.id.rg_extras);
        final RadioButton rb_wide=findViewById(R.id.rb_wide);
        final RadioButton rb_no_ball=findViewById(R.id.rb_no_ball);
        final RadioButton rb_byes=findViewById(R.id.rb_byes);
        final RadioButton rb_leg_byes=findViewById(R.id.rb_leg_byes);
        final RadioButton rb_wicket=findViewById(R.id.rb_wicket);

        final RadioGroup rg_runs=findViewById(R.id.rg_runs);
        final RadioButton rb_0=findViewById(R.id.rb_0);
        final RadioButton rb_1=findViewById(R.id.rb_1);
        final RadioButton rb_2=findViewById(R.id.rb_2);
        final RadioButton rb_3=findViewById(R.id.rb_3);
        final RadioButton rb_4=findViewById(R.id.rb_4);
        final RadioButton rb_5=findViewById(R.id.rb_5);
        final RadioButton rb_6=findViewById(R.id.rb_6);

        matchViewModel.getTotalrun(matchNo,Integer.parseInt(sessionManager.getTournamentId())).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer totalrun) {
                if(totalrun!=null) {
                totalRun = totalrun;
                }
            }
        });

        rb_wide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              run=1;
              matchViewModel.updatTotalrun(totalRun+1,matchNo,torunamentId);
              matchViewModel.updatextraScore(run,matchNo,torunamentId,1);

            }
        });
        rb_no_ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchViewModel.updatTotalrun(totalRun+1,matchNo,torunamentId);
                matchViewModel.updatextraScore(run,matchNo,torunamentId,1);
              run=1;
            }
        });

        rb_byes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchViewModel.updatTotalrun(totalRun+1,matchNo,torunamentId);
                matchViewModel.updatextraScore(run,matchNo,torunamentId,1);
            run=1;

            }
        });
        rb_leg_byes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchViewModel.updatTotalrun(totalRun+1,matchNo,torunamentId);
                matchViewModel.updatextraScore(run,matchNo,torunamentId,1);
            run=1;
            }
        });
        rb_wicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // matchViewModel.updatTotalrun(totalRun+0,matchNo,torunamentId);
               // matchViewModel.updatextraScore(run,matchNo,torunamentId,1);
                wicket=1;
            }
        });
        rb_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                run=0;
                matchViewModel.updatTotalrun(totalRun+0,matchNo,torunamentId);
                updateScore(0);
                //matchViewModel.updateb1Score(run,matchNo,torunamentId,1);

            }
        });
        rb_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            run=1;
                updateScore(1);
                matchViewModel.updatTotalrun(totalRun+1,matchNo,torunamentId);
               // matchViewModel.updateb2Score(run,matchNo,torunamentId,1);
            }
        });
        rb_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore(2);
                matchViewModel.updatTotalrun(totalRun+2,matchNo,torunamentId);
            run=2;
            }
        });
        rb_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore(3);
                matchViewModel.updatTotalrun(totalRun+3,matchNo,torunamentId);
            run=3;
            }
        });
        rb_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore(4);
                matchViewModel.updatTotalrun(totalRun+4,matchNo,torunamentId);
            run=4;
            }
        });
        rb_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore(5);
            run=5;
                matchViewModel.updatTotalrun(totalRun+5,matchNo,torunamentId);
            }
        });
        rb_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore(6);
            run=6;
                matchViewModel.updatTotalrun(totalRun+6,matchNo,torunamentId);
            }
        });

        matchViewModel.getMatchDetails(matchNo,Integer.parseInt(sessionManager.getTournamentId())).observe(this, new Observer<Matches>() {
            @Override
            public void onChanged(Matches matches) {
                if(matches!=null){
                    if (teamA.equals(mViewModel.getTeamName(matches.getMatch_won_toss()))){
                        if(matches.getDecided_to()==1){
                            int batting=mViewModel.getTeamId(teamA);
                            int bowlling=mViewModel.getTeamId(teamB);
                            txtTeamName.setText(teamA);
                            battingTeam=batting;
                            bowlingTeam=bowlling;
                            intitbattingTeam(batting);
                            intibowlingTeam(bowlling);
                            stickerNonStrickerSelection(battingTeam,bowlingTeam,matchNo);
                        }else{
                            int batting=mViewModel.getTeamId(teamB);
                            int bowlling=mViewModel.getTeamId(teamA);
                            battingTeam=batting;
                            bowlingTeam=bowlling;
                            intitbattingTeam(batting);
                            intibowlingTeam(bowlling);
                            stickerNonStrickerSelection(battingTeam,bowlingTeam,matchNo);
                        }

                    }else{
                        if(matches.getDecided_to()==1){
                            txtTeamName.setText(teamB);
                            battingTeam=mViewModel.getTeamId(teamB);
                            bowlingTeam=mViewModel.getTeamId(teamA);
                            intitbattingTeam(battingTeam);
                            intibowlingTeam(bowlingTeam);
                            stickerNonStrickerSelection(battingTeam,bowlingTeam,matchNo);
                        }else{
                            battingTeam=mViewModel.getTeamId(teamA);
                            bowlingTeam=mViewModel.getTeamId(teamB);
                            intitbattingTeam(battingTeam);
                            intibowlingTeam(bowlingTeam);
                            stickerNonStrickerSelection(battingTeam,bowlingTeam,matchNo);
                        }
                    }
                }
                if(matches.getTotalrun()>0){
                    txtScore.setText(""+matches.getTotalrun());
                }

            }
        });


    }

    public void intitbattingTeam(int teaamId){
        mViewModel.getBattingTeamPlayers(teaamId).observe(this, new Observer<List<Players>>() {
            @Override
            public void onChanged(List<Players> players) {
                battingPlayerList=players;
                BattingTeamPlayerAdapter battingTeamPlayerAdapter = new BattingTeamPlayerAdapter(players,ScoreActivity.this );
                rv_batsman.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
                rv_batsman.setAdapter(battingTeamPlayerAdapter);
                rv_batsman.setHasFixedSize(true);
            }
        });
    }

    public void intibowlingTeam(int teamId){
        mViewModel.getBowlingTeamPlayers(teamId).observe(this, new Observer<List<Players>>() {
            @Override
            public void onChanged(List<Players> players) {
                bowlingPlayerList=players;
                BowlingTeamPlayerAdapter bowlingTeamPlayerAdapter = new BowlingTeamPlayerAdapter(players,ScoreActivity.this );
                rv_bowlers.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
                rv_bowlers.setAdapter(bowlingTeamPlayerAdapter);
                rv_bowlers.setHasFixedSize(true);
            }
        });
    }

    public void updateScore(int run){
        if(matchViewModel.getB1Run(matchNo,torunamentId)!=null && matchViewModel.getB2Run(matchNo,torunamentId)!=null
         && matchViewModel.getB4Run(matchNo,torunamentId)!=null && matchViewModel.getB3Run(matchNo,torunamentId)!=null
         && matchViewModel.getB5Run(matchNo,torunamentId)!=null && matchViewModel.getB6Run(matchNo,torunamentId)!=null){

        }else {
            if (matchViewModel.getB1Run(matchNo, torunamentId) == null) {
                matchViewModel.updateb1Score(run, matchNo, torunamentId, 1);
            } else if (matchViewModel.getB2Run(matchNo, torunamentId) == null) {
                matchViewModel.updateb2Score(run, matchNo, torunamentId, 1);
            } else if (matchViewModel.getB3Run(matchNo, torunamentId) == null) {
                matchViewModel.updateb3Score(run, matchNo, torunamentId, 1);
            } else if (matchViewModel.getB4Run(matchNo, torunamentId) == null) {
                matchViewModel.updateb4Score(run, matchNo, torunamentId, 1);
            } else if (matchViewModel.getB5Run(matchNo, torunamentId) == null) {
                matchViewModel.updateb5Score(run, matchNo, torunamentId, 1);
            } else if (matchViewModel.getB6Run(matchNo, torunamentId) == null) {
                matchViewModel.updateb6Score(run, matchNo, torunamentId, 1);
            }
        }
    }
public void stickerNonStrickerSelection(int teamA,int teamB,int matchNo){
    StickerSelectionFragment stickerSelectionFragment=new StickerSelectionFragment();
    Bundle bundle=new Bundle();
    bundle.putInt("TA",teamA);
    bundle.putInt("TB",teamB);
    bundle.putInt("matchNo",matchNo);
    stickerSelectionFragment.setArguments(bundle);
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    Fragment prev = getSupportFragmentManager().findFragmentByTag("StrickerSelection");
    if (prev == null) {
        ft.addToBackStack(null);
        stickerSelectionFragment.show(ft, "StrickerSelection");
    }
}


}