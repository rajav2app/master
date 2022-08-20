package com.vtitan.patnershipcricketleague.repository;

import android.content.Context;

import com.vtitan.patnershipcricketleague.room.LocalDatabase;
import com.vtitan.patnershipcricketleague.room.ScoreDao;

public class ScoreRepository {
    private final ScoreDao mScoreDao;
    public ScoreRepository(Context appContext) {
        LocalDatabase db = LocalDatabase.getDatabase(appContext);
        mScoreDao=db.ScoreDao();
    }
}
