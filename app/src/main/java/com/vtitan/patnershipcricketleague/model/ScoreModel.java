package com.vtitan.patnershipcricketleague.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class ScoreModel {
    @PrimaryKey(autoGenerate = true)
    private int scoreID = 0;

    @ColumnInfo(name = "t_ID")
    private String t_ID;

    @ColumnInfo(name = "match_no")
    private int match_no;

    @ColumnInfo(name = "batting_teamID")
    private int  batting_teamID;

    @ColumnInfo(name = "batting_team_sticker")
    private int batting_team_sticker;

    @ColumnInfo(name = "batting_team_non_sticker")
    private int batting_team_non_sticker;

    @ColumnInfo(name = "batting_team_bowler")
    private int batting_team_bowler;

    @ColumnInfo(name = "balls_no")
    private int balls;

    @ColumnInfo(name = "run_scored")
    private int run_scored;

    @ColumnInfo(name = "remarks")
    private String remarks;

    @ColumnInfo(name = "bowling_team_run_scored")
    private int  bowling_team_run_scored;

    @ColumnInfo(name = "bowling_team_remarks")
    private String bowling_team_remarks;

    @ColumnInfo(name = "bowling_team_sticker")
    private int bowling_team_sticker;

    @ColumnInfo(name = "bowling_non_sticker")
    private int bowling_non_sticker;

    @ColumnInfo(name = "bowling_team_bowler")
    private int bowling_team_bowler;

    @ColumnInfo(name = "innings")
    private int innings;

    public int getScoreID() {
        return scoreID;
    }

    public void setScoreID(int scoreID) {
        this.scoreID = scoreID;
    }

    public String getT_ID() {
        return t_ID;
    }

    public void setT_ID(String t_ID) {
        this.t_ID = t_ID;
    }

    public int getMatch_no() {
        return match_no;
    }

    public void setMatch_no(int match_no) {
        this.match_no = match_no;
    }

    public int getBatting_teamID() {
        return batting_teamID;
    }

    public void setBatting_teamID(int batting_teamID) {
        this.batting_teamID = batting_teamID;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getRun_scored() {
        return run_scored;
    }

    public void setRun_scored(int run_scored) {
        this.run_scored = run_scored;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getBowling_team_run_scored() {
        return bowling_team_run_scored;
    }

    public void setBowling_team_run_scored(int bowling_team_run_scored) {
        this.bowling_team_run_scored = bowling_team_run_scored;
    }

    public String getBowling_team_remarks() {
        return bowling_team_remarks;
    }

    public void setBowling_team_remarks(String bowling_team_remarks) {
        this.bowling_team_remarks = bowling_team_remarks;
    }

    public int getBatting_team_sticker() {
        return batting_team_sticker;
    }

    public void setBatting_team_sticker(int batting_team_sticker) {
        this.batting_team_sticker = batting_team_sticker;
    }

    public int getBatting_team_non_sticker() {
        return batting_team_non_sticker;
    }

    public void setBatting_team_non_sticker(int batting_team_non_sticker) {
        this.batting_team_non_sticker = batting_team_non_sticker;
    }

    public int getBowling_team_sticker() {
        return bowling_team_sticker;
    }

    public void setBowling_team_sticker(int bowling_team_sticker) {
        this.bowling_team_sticker = bowling_team_sticker;
    }

    public int getBowling_non_sticker() {
        return bowling_non_sticker;
    }

    public void setBowling_non_sticker(int bowling_non_sticker) {
        this.bowling_non_sticker = bowling_non_sticker;
    }

    public int getInnings() {
        return innings;
    }

    public void setInnings(int innings) {
        this.innings = innings;
    }

    public int getBatting_team_bowler() {
        return batting_team_bowler;
    }

    public void setBatting_team_bowler(int batting_team_bowler) {
        this.batting_team_bowler = batting_team_bowler;
    }

    public int getBowling_team_bowler() {
        return bowling_team_bowler;
    }

    public void setBowling_team_bowler(int bowling_team_bowler) {
        this.bowling_team_bowler = bowling_team_bowler;
    }
}
