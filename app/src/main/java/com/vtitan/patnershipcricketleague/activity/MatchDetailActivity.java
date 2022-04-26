package com.vtitan.patnershipcricketleague.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;
import com.vtitan.patnershipcricketleague.viewmodel.TournamentViewModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchDetailActivity extends AppCompatActivity {
    private String teamA;
    private String teamB;
    private String matchNo;
    private int toss=0;
    private int bat=0;
    private int bowl=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

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
        final TeamViewModel mViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        Intent intent=getIntent();
        if(intent!=null){
            teamA=intent.getStringExtra("TA");
            teamB=intent.getStringExtra("TB");
            matchNo=intent.getStringExtra("MNO");
        }
        final CircleImageView img_teamA=findViewById(R.id.img_teamA);
        final CircleImageView img_teamB=findViewById(R.id.img_teamB);
        final TextView txtTeamName=findViewById(R.id.txtTeamName);
        final TextView txtTeamNameB=findViewById(R.id.txtTeamNameB);
        final EditText etVenue=findViewById(R.id.etVenue);
        final TextInputLayout til_sch_time=findViewById(R.id.til_sch_time);
        final TextInputEditText edit_sch_time=findViewById(R.id.edit_sch_time);
        final EditText etOver=findViewById(R.id.etOver);
        final EditText etWickets=findViewById(R.id.etWickets);
        final RadioGroup rg_won_toss=findViewById(R.id.rg_won_toss);
        final RadioButton rb_teamA=findViewById(R.id.rb_teamA);
        final RadioButton rb_teamB=findViewById(R.id.rb_teamB);
        final RadioGroup rg_decided_to_bat=findViewById(R.id.rg_decided_to_bat);
        final RadioButton rb_Bat=findViewById(R.id.rb_Bat);
        final RadioButton rb_bowl=findViewById(R.id.rb_bowl);
        final Button btn_save=findViewById(R.id.btn_save);
        final Button btn_start_scoring=findViewById(R.id.btn_start_scoring);

        txtTeamName.setText(teamA);
        txtTeamNameB.setText(teamB);

        btn_save.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

            }
        });

        btn_start_scoring.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

            }
        });

        til_sch_time.setEndIconOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {

            }
        });

        rb_teamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toss =mViewModel.getTeamId(teamA);
            }
        });
        rb_teamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toss =mViewModel.getTeamId(teamB);
            }
        });

        rb_Bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bat =1;
            }
        });
        rb_bowl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bowl=1;
            }
        });
    }
}