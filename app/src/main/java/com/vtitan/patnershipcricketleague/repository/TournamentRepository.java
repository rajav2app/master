package com.vtitan.patnershipcricketleague.repository;

import android.content.Context;
import android.os.AsyncTask;

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
import com.vtitan.patnershipcricketleague.room.TeamDao;
import com.vtitan.patnershipcricketleague.room.TournamentDao;

import java.util.List;

public class TournamentRepository {

    private final TournamentDao tournamentDao;
    private final TeamDao mTeamDao;
    private final PlayersDao playersDao;
    private final MatchesDao matchesDao;
    public TournamentRepository(Context appContext) {
        LocalDatabase db= LocalDatabase.getDatabase(appContext);
        tournamentDao=db.TournamentDao();
        mTeamDao=db.TeamDao();
        playersDao=db.PlayersDao();
        matchesDao=db.MatchesDao();
    }
    public long insertTournament(Tournament tournament) {
        return tournamentDao.insertTounament(tournament);
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

    /*---------------------- TEAM DATA ------------------------------*/

    public long insertTeam(Teams teamModel) {
        return mTeamDao.insertTeam(teamModel);
        // new insertTournamentTask(tournamentDao).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,tournament);
    }

    public int teamCount(String tournamentID){
        return mTeamDao.getTeamCount(tournamentID);
    }

    public long insertPlayers(Players players) {
        return playersDao.insertPlayers(players);
    }

    public LiveData<Integer> playerCount(int teamID){
        return playersDao.getPlayersCount(teamID);
    }

    public LiveData<List<Players>>getAllPlayers(int teamId){
      return playersDao.getAllPlayers(teamId);
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

    public LiveData<Integer> getMatchesScheduleState(){
        return tournamentDao.getMatchScheduleState();
    }
    public void updateMatchScheduleState(int matchScheduleState,int tournamentID) {
       tournamentDao.updateMatchScheduleState(matchScheduleState,tournamentID);
    }
    public int getTeamID(String teamName){
     return  mTeamDao.getTeamId(teamName);
    }




}
