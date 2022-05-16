package com.vtitan.patnershipcricketleague.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.adapter.HomeTabAdapter;
import com.vtitan.patnershipcricketleague.adapter.TeamDetailsTabAdapter;
import com.vtitan.patnershipcricketleague.fragments.CreateNewTeamFragment;
import com.vtitan.patnershipcricketleague.fragments.CreatePlayerFragment;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.util.DebouncedOnClickListener;
import com.vtitan.patnershipcricketleague.util.SessionManager;
import com.vtitan.patnershipcricketleague.viewmodel.TeamViewModel;

public class TeamDetailsActivity extends AppCompatActivity {
    private Teams teamDetails;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_teams));
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
        final TeamViewModel mteamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        final SessionManager sessionManager=new SessionManager(this);
        final TextView txtTeamName=findViewById(R.id.txtTeamName);
        final TextView txtTeamLocation=findViewById(R.id.txtTeamLocation);
        final TextView txtMatchPlayed=findViewById(R.id.txtMatchPlayed);
        final TextView txtMatchWon=findViewById(R.id.txtMatchWon);
        final ViewPager viewPager = findViewById(R.id.ViewPager);
        final TabLayout tabs = findViewById(R.id.tabTeam);
        final ImageView imbEdit=findViewById(R.id.imbEdit);


        mteamViewModel.getTeamDetails(sessionManager.getTeamId()).observe(this, new Observer<Teams>() {
            @Override
            public void onChanged(Teams teams) {
                teamDetails=teams;
                txtTeamName.setText(teams.getTeam_name());
                txtTeamLocation.setText(teams.getTeam_location());

            }
        });

        TeamDetailsTabAdapter tabsPagerAdapter = new TeamDetailsTabAdapter(this,this.getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabGravity(TabLayout.INDICATOR_GRAVITY_CENTER);
        tabsPagerAdapter.notifyDataSetChanged();

        imbEdit.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(getString(R.string.key_team_name),teamDetails.getTeam_name());
                bundle.putString(getString(R.string.key_team_location),teamDetails.getTeam_location());
                bundle.putInt(getString(R.string.key_team_id),teamDetails.getTeamID());
                CreateNewTeamFragment createNewTeamFragment=new CreateNewTeamFragment();
                createNewTeamFragment.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("ConfirmDialog");
                if (prev == null) {
                    ft.addToBackStack(null);
                    createNewTeamFragment.show(ft, "CreatePlayerDialog");
                }
            }
        });


    }
}