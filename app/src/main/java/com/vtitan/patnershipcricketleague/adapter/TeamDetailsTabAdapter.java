package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.fragments.MatchListFragment;
import com.vtitan.patnershipcricketleague.fragments.MatchesFragment;
import com.vtitan.patnershipcricketleague.fragments.PlayersFragment;
import com.vtitan.patnershipcricketleague.fragments.TournamentListFragment;

public class TeamDetailsTabAdapter extends FragmentPagerAdapter {
    private final PlayersFragment lf1 = PlayersFragment.newInstance();
    private final MatchListFragment lf2 = MatchListFragment.newInstance();
    @StringRes
    private static final int[] TAB_TITLES =
            new int[]{R.string.title_players, R.string.title_matches};
    private final Context mContext;

    public TeamDetailsTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return lf1;
            case 1:
                return lf2;

            default:
                PlayersFragment playersFragment = new PlayersFragment();
                return playersFragment;

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
