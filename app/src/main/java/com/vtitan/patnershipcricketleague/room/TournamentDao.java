package com.vtitan.patnershipcricketleague.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vtitan.patnershipcricketleague.model.Tournament;

import java.util.List;

@Dao
public interface TournamentDao {

   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTounament(Tournament tournament);*/
    @Insert
    Long insertTounament(Tournament tournament);

    @Query("SELECT * FROM Tournament")
    abstract LiveData<List<Tournament>> getAllTournament();

    @Query("SELECT * FROM Tournament WHERE tournamentID = :tournamentID LIMIT 1")
    abstract LiveData<Tournament> getTournamentData(String tournamentID);

    @Query("UPDATE Tournament SET match_schedule_state = :match_schedule_state WHERE tournamentID =:tournamentID")
    abstract void updateMatchScheduleState( int  match_schedule_state,int tournamentID);

    @Query("SELECT match_schedule_state FROM Tournament")
    abstract LiveData<Integer> getMatchScheduleState();


}
