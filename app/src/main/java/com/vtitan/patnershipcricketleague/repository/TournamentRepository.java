package com.vtitan.patnershipcricketleague.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.room.LocalDatabase;
import com.vtitan.patnershipcricketleague.model.Tournament;
import com.vtitan.patnershipcricketleague.room.MatchesDao;
import com.vtitan.patnershipcricketleague.room.PlayersDao;
import com.vtitan.patnershipcricketleague.room.ScoreDao;
import com.vtitan.patnershipcricketleague.room.TeamDao;
import com.vtitan.patnershipcricketleague.room.TournamentDao;

import java.util.List;

public class TournamentRepository {

    private final TournamentDao tournamentDao;
    private final TeamDao mTeamDao;
    private final PlayersDao playersDao;
    private final MatchesDao matchesDao;
    private final ScoreDao mScoreDao;
    public TournamentRepository(Context appContext) {
        LocalDatabase db= LocalDatabase.getDatabase(appContext);
        tournamentDao=db.TournamentDao();
        mTeamDao=db.TeamDao();
        playersDao=db.PlayersDao();
        matchesDao=db.MatchesDao();
        mScoreDao=db.ScoreDao();
    }
    public long insertTournament(Tournament tournament) {
        return tournamentDao.insertTounament(tournament);
    }
    public void updateTournament(String tname,String tlocation,long startdate,long enddate,int tid){
       tournamentDao.updateTournament(tname,tlocation,startdate,enddate,tid);
    }


    public LiveData<Tournament> getTournamentLiveData(String devID){
        final MediatorLiveData<Tournament> outputLiveData = new MediatorLiveData<>();
        outputLiveData.addSource(tournamentDao.getTournamentData(devID), new Observer<Tournament>() {

            boolean mFirstTime = true;

            @Override
            public void onChanged(Tournament currentValue) {
                final Tournament previousValue = outputLiveData.getValue();
                if (mFirstTime
                        || (previousValue == null && currentValue != null)
                        || (previousValue != null && !previousValue.equals(currentValue))) {
                    mFirstTime = false;
                    outputLiveData.setValue(currentValue);
                }
            }
        });
        return outputLiveData;
    }

    public LiveData<List<Tournament>> getAllTournament(){
        final MediatorLiveData<List<Tournament>> outputLiveData = new MediatorLiveData<>();
        outputLiveData.addSource(tournamentDao.getAllTournament(), new Observer<List<Tournament>>() {

            boolean mFirstTime = true;

            @Override
            public void onChanged(List<Tournament> currentValue) {
                final List<Tournament> previousValue = outputLiveData.getValue();
                if (mFirstTime
                        || (previousValue == null && currentValue != null)
                        || (previousValue != null && !previousValue.equals(currentValue))) {
                    mFirstTime = false;
                    outputLiveData.setValue(currentValue);
                }
            }
        });
        return outputLiveData;
    }

    public Tournament getTournamentDetails(int tournamentId){
     return tournamentDao.getTournamentDetails(tournamentId);
    }

    /*---------------------- TEAM DATA ------------------------------*/

    public long insertTeam(Teams teamModel) {
        return mTeamDao.insertTeam(teamModel);
        // new insertTournamentTask(tournamentDao).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,tournament);
    }

    public LiveData<Integer> teamCount(String tournamentID){
        return mTeamDao.getTeamCount(tournamentID);
    }

    public long insertPlayers(Players players) {
        return playersDao.insertPlayers(players);
    }

    public int updatePlayerDetails(String playerName,int playerState,int playerid) {
        return playersDao.updatePlayerDetails(playerName,playerState,playerid);
    }
    public int updateTeamDetails(String teamName,String teamLocation,int teamid) {
        return mTeamDao.updateTeamDetails(teamName,teamLocation,teamid);
    }
    public LiveData<Integer> playerCount(int teamID){
        return playersDao.getPlayersCount(teamID);
    }

    public LiveData<List<Players>>getAllPlayers(int teamId){
      return playersDao.getAllPlayers(teamId);
    }
    public LiveData<List<Players>>getBattingTeamPlayers(int teamId){
        return playersDao.getBattingTeamPlayers(teamId);
    }
    public LiveData<List<Players>>getBowlingTeamPlayers(int teamId){
        return playersDao.getBowlingTeamPlayers(teamId);
    }


    public LiveData<List<Teams>> getAllTeams(String tID){
        return mTeamDao.getAllTeams(tID);
    }

    public LiveData<Teams> getTeamDetails(int tID){
        return mTeamDao.getTeamDetails(tID);
    }

    public Long[] insertMatches(List<Matches> matches) {
       return matchesDao.insertMatches(matches);
    }

    public LiveData<List<String>> getAllRounds(String tID){
        return matchesDao.getAllRounds(tID);
    }

    public LiveData<List<Matches>> getAllMatches(String round){
        return matchesDao.getAllMatches(round);
    }

    public LiveData<List<Matches>> getMatches(){
        return matchesDao.getMatches();
    }

    public LiveData<List<Matches>> getTeamMatches(int teamId){
        return matchesDao.getTeamMatches(teamId);
    }

    public LiveData<Integer> getMatchesScheduleState(){
        return tournamentDao.getMatchScheduleState();
    }
    public void updateMatchScheduleState(int matchScheduleState,int tournamentID) {
       tournamentDao.updateMatchScheduleState(matchScheduleState,tournamentID);
    }
    public int getTeamID(String teamName){
     return  mTeamDao.getTeamId(teamName);
    }

    public LiveData<Integer> getPlayerState(int teamID){
        return playersDao.getPlayerState(teamID);
    }

    public Integer getPlayerCount(int teamID){
        return playersDao.getPlayerCount(teamID);
    }

    public int updateMatchDetails(String venue,long date,int over,int toss,int bat,int matchNo) {
        return matchesDao.updateMatchDetails(venue,date,over,toss,bat,matchNo);
    }

   /* public LiveData<Matches>getMatchDetails(int matchNo,int tournamentId){
        return matchesDao.getMatchDetails(matchNo,tournamentId);
    }*/

    public LiveData<Matches> getMatchDetails(int matchNo,int tournamentId) {
        final MediatorLiveData<Matches> outputLiveData = new MediatorLiveData<>();
        outputLiveData.addSource(matchesDao.getMatchDetails(matchNo,tournamentId), new Observer<Matches>() {

            boolean mFirstTime = true;

            @Override
            public void onChanged(Matches currentValue) {
                final Matches previousValue = outputLiveData.getValue();
                if (mFirstTime
                        || (previousValue == null && currentValue != null)
                        || (previousValue != null && !previousValue.equals(currentValue))) {
                    mFirstTime = false;
                    outputLiveData.setValue(currentValue);
                }
            }
        });
        return outputLiveData;
    }

    public String getTeamName(int teamId){
        return mTeamDao.getTeamName(teamId);
    }

    public void updateMatchStatus(int status,int matchNo,int tournamentId){
        matchesDao.updateMatchStatus(status,matchNo,tournamentId);
    }

    public void updateb1Score(int run,int matchNo,int tournamentId,int pid){
        matchesDao.updateb1Score(run,matchNo,tournamentId,pid);
    }
    public void updateb2Score(int run,int matchNo,int tournamentId,int pid){
        matchesDao.updateb2Score(run,matchNo,tournamentId,pid);
    }
    public void updateb3Score(int run,int matchNo,int tournamentId,int pid){
        matchesDao.updateb3Score(run,matchNo,tournamentId,pid);
    }
    public void updateb4Score(int run,int matchNo,int tournamentId,int pid){
        matchesDao.updateb4Score(run,matchNo,tournamentId,pid);
    }
    public void updateb5Score(int run,int matchNo,int tournamentId,int pid){
        matchesDao.updateb5Score(run,matchNo,tournamentId,pid);
    }

    public void updateb6Score(int run,int matchNo,int tournamentId,int pid){
        matchesDao.updateb6Score(run,matchNo,tournamentId,pid);
    }
    public void updatextra(int run,int matchNo,int tournamentId,int pid){
        matchesDao.updatextraScore(run,matchNo,tournamentId,pid);
    }
    public void updateTotalRun(int run,int matchNo,int tournamentId){
        matchesDao.updateTotalrun(run,matchNo,tournamentId);
    }
    public LiveData<Integer>getTotalrun(int matchNo,int tournamentId){
    return  matchesDao.getTotalrun(matchNo,tournamentId);
    }

    public void updateTeamHomeDetails(String teamName,String oldTeamName){
        matchesDao.updateTeamDetails(teamName,oldTeamName); }
    public void updateawayTeamDetails(String teamName,String oldTeamName){
        Log.i("TEAM_NAME",teamName);
        matchesDao.updateawayTeamDetails(teamName,oldTeamName); }

    public Integer getB1Run(int matchNo,int tournamentId){
        return  matchesDao.getB1run(matchNo,tournamentId);
    }
    public Integer getB2Run(int matchNo,int tournamentId){
        return  matchesDao.getB2run(matchNo,tournamentId);
    }
    public Integer getB3Run(int matchNo,int tournamentId){
        return  matchesDao.getB3run(matchNo,tournamentId);
    }
    public Integer getB4Run(int matchNo,int tournamentId){
        return  matchesDao.getB4run(matchNo,tournamentId);
    }
    public Integer getB5Run(int matchNo,int tournamentId){
        return  matchesDao.getB5run(matchNo,tournamentId);
    }
    public Integer getB6Run(int matchNo,int tournamentId){
        return  matchesDao.getB6run(matchNo,tournamentId);
    }

    public void deleteTorunamentMatches(int tid){
        matchesDao.delete(tid);
    }
    public void deleteTournamentTeams(int tid){
        mTeamDao.delete(tid);
    }
    public void deleteTournament(int tid){
        tournamentDao.delete(tid);
    }
    /*public void deleteScore(int tid){
       mScoreDao.delete(tid);
    }*/

    public List<Players>getBTPlayers(int teamId){
        return playersDao.getBTPlayers(teamId);
    }

}
