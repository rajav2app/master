package com.vtitan.patnershipcricketleague.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.adapter.HomeTabAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.txt_pcl));
        setSupportActionBar(toolbar);

        int position = 0;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            position = extras.getInt("viewpager_position");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final ViewPager viewPager = findViewById(R.id.ViewPager);
        final TabLayout tabs = findViewById(R.id.tabHome);
        HomeTabAdapter tabsPagerAdapter = new HomeTabAdapter(this,this.getSupportFragmentManager(),position);
        viewPager.setAdapter(tabsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
        tabsPagerAdapter.notifyDataSetChanged();
    }


}