package com.vtitan.patnershipcricketleague.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vtitan.patnershipcricketleague.model.Teams;

import java.util.List;

@Dao
public interface TeamDao {

    @Insert
    Long insertTeam(Teams teamModel);

    @Query("SELECT * FROM Teams WHERE t_ID =:tId")
    abstract LiveData<List<Teams>> getAllTeams(String tId);

    @Query("SELECT COUNT(team_name) FROM Teams WHERE t_ID =:tournamentID")
    abstract LiveData<Integer> getTeamCount(String tournamentID);

    @Query("SELECT teamID,team_name,team_location FROM Teams WHERE teamID =:tId")
    abstract LiveData<Teams> getTeamDetails(int tId);

    @Query("SELECT teamID FROM Teams WHERE team_name =:teamName")
    abstract int getTeamId(String teamName);

    @Query("UPDATE TEAMS SET team_name =:teamName,team_location =:teamLocation WHERE teamID =:teamId")
    int updateTeamDetails(String teamName,String teamLocation,int teamId);

    @Query("SELECT team_name FROM Teams WHERE teamID =:teamId")
    String getTeamName(int teamId);





}
