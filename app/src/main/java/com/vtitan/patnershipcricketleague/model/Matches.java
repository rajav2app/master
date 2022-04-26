package com.vtitan.patnershipcricketleague.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Matches {

    @PrimaryKey(autoGenerate = true)
    private int matchID = 0;

    @ColumnInfo(name = "t_ID")
    private String t_ID;

    @ColumnInfo(name = "match_no")
    private int match_no;

    @ColumnInfo(name = "match_formate")
    private int match_formate;

    @NonNull
    @ColumnInfo(name = "match_date")
    private long match_date;

    @NonNull
    @ColumnInfo(name = "match_details")
    private String match_details;

    @NonNull
    @ColumnInfo(name = "match_venue")
    private String match_venue;

    @NonNull
    @ColumnInfo(name = "match_time")
    private long match_time;

    @NonNull
    @ColumnInfo(name = "round")
    private String round;

    @ColumnInfo(name = "match_status")
    private int match_status;

    @ColumnInfo(name = "match_won_toss")
    private int match_won_toss;

    @ColumnInfo(name = "decided_to")
    private int decided_to;

    @ColumnInfo(name = "match_total_over")
    private int match_total_over;

    @ColumnInfo(name = "match_total_wickets")
    private int match_total_wickets;


    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getMatch_no() {
        return match_no;
    }

    public void setMatch_no(int match_no) {
        this.match_no = match_no;
    }

    public int getMatch_formate() {
        return match_formate;
    }

    public void setMatch_formate(int match_formate) {
        this.match_formate = match_formate;
    }

    public long getMatch_date() {
        return match_date;
    }

    public void setMatch_date(long match_date) {
        this.match_date = match_date;
    }

    @NonNull
    public String getMatch_details() {
        return match_details;
    }

    public void setMatch_details(@NonNull String match_details) {
        this.match_details = match_details;
    }

    @NonNull
    public String getMatch_venue() {
        return match_venue;
    }

    public void setMatch_venue(@NonNull String match_venue) {
        this.match_venue = match_venue;
    }

    public long getMatch_time() {
        return match_time;
    }

    public void setMatch_time(long match_time) {
        this.match_time = match_time;
    }

    @NonNull
    public String getRound() {
        return round;
    }

    public void setRound(@NonNull String round) {
        this.round = round;
    }

    public String getT_ID() {
        return t_ID;
    }

    public void setT_ID(String t_ID) {
        this.t_ID = t_ID;
    }

    public int getMatch_status() {
        return match_status;
    }

    public void setMatch_status(int match_status) {
        this.match_status = match_status;
    }

    public int getMatch_won_toss() {
        return match_won_toss;
    }

    public void setMatch_won_toss(int match_won_toss) {
        this.match_won_toss = match_won_toss;
    }

    public int getDecided_to() {
        return decided_to;
    }

    public void setDecided_to(int decided_to) {
        this.decided_to = decided_to;
    }

    public int getMatch_total_over() {
        return match_total_over;
    }

    public void setMatch_total_over(int match_total_over) {
        this.match_total_over = match_total_over;
    }

    public int getMatch_total_wickets() {
        return match_total_wickets;
    }

    public void setMatch_total_wickets(int match_total_wickets) {
        this.match_total_wickets = match_total_wickets;
    }
}
