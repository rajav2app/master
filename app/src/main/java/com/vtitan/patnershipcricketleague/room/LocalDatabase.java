package com.vtitan.patnershipcricketleague.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.model.Tournament;

@Database(entities = {Tournament.class, Teams.class, Matches.class, Players.class},version = 4, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {
    private static final String DB_NAME = "pcl.db";
    private static LocalDatabase INSTANCE;

    public static LocalDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration() //Migration not being handled
                            .allowMainThreadQueries() //TODO: Need to remove and avoid Main thread Queries
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TournamentDao TournamentDao();
    public abstract TeamDao TeamDao();
    public abstract PlayersDao PlayersDao();
    public abstract MatchesDao MatchesDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}