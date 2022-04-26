package com.vtitan.patnershipcricketleague.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.adapter.TournamentSliderAdapter;
import com.vtitan.patnershipcricketleague.model.Tournament;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.viewmodel.TournamentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TournamentSelectionActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<String> titleList = new ArrayList<>();
    private ImageView[] dots;
    private TournamentSliderAdapter sliderPagerAdapter;
    private List<Tournament> tournamentList=new ArrayList<>(

    );
    private LinearLayout sliderDotspanel;
    private int page_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_selection);
        final RelativeLayout rlNewTournament = findViewById(R.id.rlNewTournament);
        final RelativeLayout rlLoadTournament = findViewById(R.id.rlLoadTournament);
        final TournamentViewModel mViewModel = new ViewModelProvider(this).get(TournamentViewModel.class);
        viewPager = findViewById(R.id.viewPager);
        sliderDotspanel = findViewById(R.id.SliderDots);
        mViewModel.getAllTournament().observe(this, new Observer<List<Tournament>>() {
            @Override
            public void onChanged(List<Tournament> tournaments) {
                Log.i("TOURNAMENTLIST", new Gson().toJson(tournaments));
                if (tournaments.size() > 0) {
                    tournamentList.addAll(tournaments);
                    init();
                }
            }
        });

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == tournamentList.size()) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                viewPager.setCurrentItem(page_position, true);
            }
        };
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 4000);

        rlNewTournament.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                startActivity(new Intent(TournamentSelectionActivity.this, NewTournamentActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

       /* rlLoadTournament.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                startActivity(new Intent(TournamentSelectionActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);

            }
        });*/


    }


    private void init() {
        sliderPagerAdapter = new TournamentSliderAdapter(this, tournamentList);
        viewPager.setAdapter(sliderPagerAdapter);
        // sliderPagerAdapter.notifyDataSetChanged();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new ImageView[tournamentList.size()];
        if (tournamentList.size() == 0) {
        } else {
            sliderDotspanel.removeAllViews();
            for (int i = 0; i < dots.length; i++) {
                if (getApplicationContext() != null) {
                    dots[i] = new ImageView(this);
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(8, 0, 8, 0);
                    sliderDotspanel.addView(dots[i], params);
                }
            }
        }
        if (dots.length > 0 && getApplicationContext() != null)
            dots[currentPage].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
    }


}