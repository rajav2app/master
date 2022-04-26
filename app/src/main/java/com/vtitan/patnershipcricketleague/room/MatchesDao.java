package com.vtitan.patnershipcricketleague.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Players;

import java.util.List;

@Dao
public interface MatchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     Long[]insertMatches(List<Matches> matches);

    @Query("SELECT DISTINCT round FROM Matches WHERE t_ID =:tID")
    abstract LiveData<List<String>> getAllRounds(String tID);

    @Query("SELECT * FROM Matches WHERE round =:round")
    abstract LiveData<List<Matches>> getAllMatches(String round);

    @Query("SELECT * FROM Matches")
    abstract LiveData<List<Matches>> getMatches();


}
