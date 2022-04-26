package com.vtitan.patnershipcricketleague.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Tournament {

    @PrimaryKey(autoGenerate = true)
    private int tournamentID = 0;

    @NonNull
    @ColumnInfo(name = "tournament_name")
    private String tournament_name;

    @ColumnInfo(name = "tournament_location")
    private String tournament_location;

    @ColumnInfo(name = "tournament_start_time")
    private long tournament_start_time;

    @ColumnInfo(name = "tournament_end_time")
    private long tournament_end_time;

    @ColumnInfo(name = "match_schedule_state")
    private long match_schedule_state;

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    @NonNull
    public String getTournament_name() {
        return tournament_name;
    }

    public void setTournament_name(@NonNull String tournament_name) {
        this.tournament_name = tournament_name;
    }

    public String getTournament_location() {
        return tournament_location;
    }

    public void setTournament_location(String tournament_location) {
        this.tournament_location = tournament_location;
    }

    public long getTournament_start_time() {
        return tournament_start_time;
    }

    public void setTournament_start_time(long tournament_start_time) {
        this.tournament_start_time = tournament_start_time;
    }

    public long getTournament_end_time() {
        return tournament_end_time;
    }

    public void setTournament_end_time(long tournament_end_time) {
        this.tournament_end_time = tournament_end_time;
    }

    public long getMatch_schedule_state() {
        return match_schedule_state;
    }

    public void setMatch_schedule_state(long match_schedule_state) {
        this.match_schedule_state = match_schedule_state;
    }
}
