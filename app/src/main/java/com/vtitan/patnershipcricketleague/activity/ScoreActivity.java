package com.vtitan.patnershipcricketleague.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.vtitan.patnershipcricketleague.R;

public class ScoreActivity extends AppCompatActivity {
    private String teamA;
    private String teamB;
    private String matchNo;
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
        Intent intent=getIntent();
        if(intent!=null){
            teamA=intent.getStringExtra("TA");
            teamB=intent.getStringExtra("TB");
            matchNo=intent.getStringExtra("MNO");
        }
    }
}