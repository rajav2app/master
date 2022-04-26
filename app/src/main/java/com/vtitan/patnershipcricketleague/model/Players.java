package com.vtitan.patnershipcricketleague.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Players {
    @PrimaryKey(autoGenerate = true)
    private int playerID = 0;

    @ColumnInfo(name = "team_id")
    private int team_id;

    @NonNull
    @ColumnInfo(name = "player_name")
    private String player_name;

    @ColumnInfo(name = "player_type")
    private int player_type;

    @ColumnInfo(name = "player_state")
    private int player_state;

    @ColumnInfo(name = "match_type")
    private int match_type;

    @ColumnInfo(name = "runs")
    private int runs;

    @ColumnInfo(name = "wickets")
    private int wickets;

    @ColumnInfo(name = "sixes")
    private int sixes;

    @ColumnInfo(name = "fours")
    private int fours;

    @ColumnInfo(name = "catches")
    private int catches;

    @ColumnInfo(name = "fifties")
    private int fifties;

    @ColumnInfo(name = "hundreds")
    private int hundreds;

    @ColumnInfo(name = "innings")
    private int innings;

    @ColumnInfo(name = "overs")
    private int overs;

    @ColumnInfo(name = "match_no")
    private int match_no;


    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    @NonNull
    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(@NonNull String player_name) {
        this.player_name = player_name;
    }

    public int getPlayer_type() {
        return player_type;
    }

    public void setPlayer_type(int player_type) {
        this.player_type = player_type;
    }

    public int getMatch_type() {
        return match_type;
    }

    public void setMatch_type(int match_type) {
        this.match_type = match_type;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getCatches() {
        return catches;
    }

    public void setCatches(int catches) {
        this.catches = catches;
    }

    public int getFifties() {
        return fifties;
    }

    public void setFifties(int fifties) {
        this.fifties = fifties;
    }

    public int getHundreds() {
        return hundreds;
    }

    public void setHundreds(int hundreds) {
        this.hundreds = hundreds;
    }

    public int getInnings() {
        return innings;
    }

    public void setInnings(int innings) {
        this.innings = innings;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getPlayer_state() {
        return player_state;
    }

    public void setPlayer_state(int player_state) {
        this.player_state = player_state;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public int getMatch_no() {
        return match_no;
    }

    public void setMatch_no(int match_no) {
        this.match_no = match_no;
    }
}
