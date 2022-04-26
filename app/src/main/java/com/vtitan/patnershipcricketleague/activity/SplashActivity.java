package com.vtitan.patnershipcricketleague.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.activity.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        final int DISPLAY_SPEED=2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Start MainActivity */
                startActivity(new Intent(getApplicationContext(), TournamentSelectionActivity.class));
                overridePendingTransition(R.anim.enter,R.anim.exit);
                finish();
            }
        },DISPLAY_SPEED);

    }
}