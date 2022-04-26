package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.fragments.HomeFragment;
import com.vtitan.patnershipcricketleague.fragments.MatchesFragment;
import com.vtitan.patnershipcricketleague.fragments.PointsFragment;
import com.vtitan.patnershipcricketleague.fragments.StaticsFragment;
import com.vtitan.patnershipcricketleague.fragments.TeamsFragment;

public class HomeTabAdapter extends FragmentPagerAdapter {
    private final HomeFragment lf1 = HomeFragment.newInstance();
    private final TeamsFragment lf2 = TeamsFragment.newInstance();
    private final MatchesFragment lf3 = MatchesFragment.newInstance();
    private final PointsFragment lf4 = PointsFragment.newInstance();
    private final StaticsFragment lf5 = StaticsFragment.newInstance();
    @StringRes
    private static final int[] TAB_TITLES =
            new int[]{R.string.title_home, R.string.title_teams,R.string.title_matches,R.string.title_points,R.string.title_statistics};
    private final Context mContext;
    private final int pos;

    public HomeTabAdapter(Context context, FragmentManager fm,int position ) {
        super(fm);
        this.mContext = context;
        this.pos=position;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return lf1;
            case 1:
                return lf2;
            case 2:
                return lf3;
            case 3:
                return lf4;
            case 4:
                return lf5;

            default:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return TAB_TITLES.length;
    }

}


