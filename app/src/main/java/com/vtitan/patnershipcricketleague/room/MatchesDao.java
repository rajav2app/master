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

    @Query("UPDATE Matches SET team_name_home =:teamName WHERE team_name_home =:oldTeamName")
    void updateTeamDetails(String teamName,String oldTeamName);

    @Query("UPDATE Matches SET team_name_away =:teamName WHERE team_name_away =:oldTeamName")
    void updateawayTeamDetails(String teamName,String oldTeamName);

    @Query("SELECT * FROM Matches WHERE team_id_home =:teamId OR team_id_away =:teamId")
    abstract LiveData<List<Matches>> getTeamMatches(int teamId);

    @Query("UPDATE Matches SET  match_venue =:venue,match_date =:date,match_total_over =:over,match_won_toss =:toss,decided_to = :bat WHERE match_no =:matchNo")
    int updateMatchDetails(String venue, long date, int over, int toss, int bat,int matchNo);

    @Query("SELECT * FROM Matches WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract LiveData<Matches>getMatchDetails(int matchNo,int tournamentId);

    @Query("UPDATE Matches SET match_status =:matchStatus WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract void updateMatchStatus(int matchStatus,int matchNo,int tournamentId);

    @Query("UPDATE Matches SET over_b1 =:run WHERE match_no =:matchNo AND t_ID =:tournamentId AND player_id =:pid")
    abstract void updateb1Score(int run ,int matchNo,int tournamentId,int pid);

    @Query("UPDATE Matches SET over_b2 =:run WHERE match_no =:matchNo AND t_ID =:tournamentId AND player_id =:pid")
    abstract void updateb2Score(int run ,int matchNo,int tournamentId,int pid);

    @Query("UPDATE Matches SET over_b3 =:run WHERE match_no =:matchNo AND t_ID =:tournamentId AND player_id =:pid")
    abstract void updateb3Score(int run ,int matchNo,int tournamentId,int pid);

    @Query("UPDATE Matches SET over_b4 =:run WHERE match_no =:matchNo AND t_ID =:tournamentId AND player_id =:pid")
    abstract void updateb4Score(int run ,int matchNo,int tournamentId,int pid);

    @Query("UPDATE Matches SET over_b5 =:run WHERE match_no =:matchNo AND t_ID =:tournamentId AND player_id =:pid")
    abstract void updateb5Score(int run ,int matchNo,int tournamentId,int pid);

    @Query("UPDATE Matches SET over_b6 =:run WHERE match_no =:matchNo AND t_ID =:tournamentId AND player_id =:pid")
    abstract void updateb6Score(int run ,int matchNo,int tournamentId,int pid);

    @Query("UPDATE Matches SET extra =:extra WHERE match_no =:matchNo AND t_ID =:tournamentId AND player_id =:pid")
    abstract void updatextraScore(int extra ,int matchNo,int tournamentId,int pid);

    @Query("UPDATE Matches SET totalrun =:run WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract void updateTotalrun(int run ,int matchNo,int tournamentId);

    @Query("SELECT totalrun FROM Matches WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract LiveData<Integer>getTotalrun(int matchNo,int tournamentId);

    @Query("SELECT over_b1 FROM Matches WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract Integer getB1run(int matchNo,int tournamentId);

    @Query("SELECT over_b2 FROM Matches WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract Integer getB2run(int matchNo,int tournamentId);

    @Query("SELECT over_b3 FROM Matches WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract Integer getB3run(int matchNo,int tournamentId);

    @Query("SELECT over_b4 FROM Matches WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract Integer getB4run(int matchNo,int tournamentId);

    @Query("SELECT over_b5 FROM Matches WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract Integer getB5run(int matchNo,int tournamentId);

    @Query("SELECT over_b6 FROM Matches WHERE match_no =:matchNo AND t_ID =:tournamentId")
    abstract Integer getB6run(int matchNo,int tournamentId);

    @Query("DELETE FROM Matches WHERE t_ID = :id ")
    abstract void delete(int id);

}
