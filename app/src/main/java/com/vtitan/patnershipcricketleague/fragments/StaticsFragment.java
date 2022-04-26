package com.vtitan.patnershipcricketleague.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vtitan.patnershipcricketleague.R;


public class StaticsFragment extends Fragment {

    public StaticsFragment() {
        // Required empty public constructor
    }

    public static StaticsFragment newInstance() {
        StaticsFragment fragment = new StaticsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView=inflater.inflate(R.layout.fragment_statics, container, false);


        return rootView;
    }
}