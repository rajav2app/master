package com.vtitan.patnershipcricketleague.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.vtitan.patnershipcricketleague.R;
import com.vtitan.patnershipcricketleague.activity.MainActivity;
import com.vtitan.patnershipcricketleague.model.Tournament;
import com.vtitan.patnershipcricketleague.util.SessionManager;

import java.util.List;

public class TournamentSliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images ;
    List<Tournament> tournamentList;

    public TournamentSliderAdapter(Context context, List<Tournament>tournamentsList) {
        this.context = context;
        this.tournamentList=tournamentsList;
    }

    @Override
    public int getCount() {
        return  tournamentList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view = layoutInflater.inflate( R.layout.custom_layout, null );
        SessionManager sessionManager=new SessionManager(context);
        ImageView imageView =  view.findViewById( R.id.bannerimg );
        TextView tvTournamentName=view.findViewById(R.id.tvTournamentName);
        LinearLayout llLoadTournament=view.findViewById(R.id.llLoadTournament);
        images=new Integer[tournamentList.size()];
        if(position%2==0){
            images[position]=R.drawable.banner1;
        }else{
            images[position]=R.drawable.banner2;
        }
        imageView.setImageResource( images[position] );
        tvTournamentName.setText(tournamentList.get(position).getTournament_name());
        ViewPager vp = (ViewPager) container;
        vp.addView( view, 0 );
        llLoadTournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.saveTournamentID(String.valueOf(tournamentList.get(position).getTournamentID()));
                Intent intent=new Intent(context,MainActivity.class);
                //intent.putExtra(context.getString(R.string.key_tid) ,tournamentList.get(position).getTournamentID());
                context.startActivity(intent);
            }
        });
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView( view );

    }
}

