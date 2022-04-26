package com.vtitan.patnershipcricketleague.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Teams {

    @PrimaryKey(autoGenerate = true)
    private int teamID;

    @NonNull
    @ColumnInfo(name = "team_name")
    private String team_name;

    @ColumnInfo(name = "team_location")
    private String team_location;

    @ColumnInfo(name = "t_ID")
    private String t_ID;

    @Embedded(prefix = "player_")
    private Players playerDetails;

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    @NonNull
    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(@NonNull String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_location() {
        return team_location;
    }

    public void setTeam_location(String team_location) {
        this.team_location = team_location;
    }

    public String getT_ID() {
        return t_ID;
    }

    public void setT_ID(String t_ID) {
        this.t_ID = t_ID;
    }

    public Players getPlayerDetails() {
        return playerDetails;
    }

    public void setPlayerDetails(Players playerDetails) {
        this.playerDetails = playerDetails;
    }
}
