package com.vtitan.patnershipcricketleague.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.model.Teams;

import java.util.List;

@Dao
public interface PlayersDao {

    @Insert
    Long insertPlayers(Players players);

    @Query("SELECT * FROM Players WHERE team_id =:teamID")
    abstract LiveData<List<Players>> getAllPlayers(int teamID);

    @Query("SELECT COUNT(player_name) FROM Players WHERE team_id =:teamID")
    LiveData<Integer> getPlayersCount(int teamID);
}
