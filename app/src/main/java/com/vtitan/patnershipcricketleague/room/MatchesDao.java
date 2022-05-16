package com.vtitan.patnershipcricketleague.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

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

    @Query("SELECT * FROM Matches WHERE team_id_home =:teamId OR team_id_away =:teamId")
    abstract LiveData<List<Matches>> getTeamMatches(int teamId);

    @Query("UPDATE Matches SET  match_venue =:venue,match_date =:date,match_total_over =:over,match_won_toss =:toss,decided_to = :bat WHERE match_no =:matchNo")
    int updateMatchDetails(String venue, long date, int over, int toss, int bat,int matchNo);

    @Query("SELECT * FROM Matches WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract LiveData<Matches>getMatchDetails(int matchNo,int tournamentId);

    @Query("UPDATE Matches SET match_status =:matchStatus WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract void updateMatchStatus(int matchStatus,int matchNo,int tournamentId);


}
