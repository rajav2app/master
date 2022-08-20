package com.vtitan.patnershipcricketleague.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vtitan.patnershipcricketleague.model.Tournament;
import com.vtitan.patnershipcricketleague.repository.TournamentRepository;

import org.json.JSONObject;

import java.util.List;

public class TournamentViewModel extends AndroidViewModel {
    private final TournamentRepository mRepository;
    public TournamentViewModel(Application application) {
        super(application);
        mRepository = new TournamentRepository(application);
    }

    public long insertTournament(Tournament tournament) {
     return  mRepository.insertTournament(tournament);
    }
    public void updateTournament(String tName,String tlocation,long start_date,long end_date,int tid){
      mRepository.updateTournament(tName,tlocation,start_date,end_date,tid);
    }

    public LiveData<List<Tournament>>getAllTournament()
    {
      return   mRepository.getAllTournament();
    }
    public LiveData<Tournament>getTournamentData(String tournamentID){
        return mRepository.getTournamentLiveData(tournamentID);
    }

    public void updateMatchScheduleState(int matchScheduleState,int tournamentId){
        mRepository.updateMatchScheduleState(matchScheduleState,tournamentId);
    }

    public LiveData<Integer>getMatchScheduleState(){
        return mRepository.getMatchesScheduleState();
    }

    public Tournament getTournamentDetails(int tournamentId){
        return mRepository.getTournamentDetails(tournamentId);
    }
    public void deleteTournament(int tid){
        mRepository.deleteTournament(tid);
        mRepository.deleteTournamentTeams(tid);
       // mRepository.deleteScore(tid);
        mRepository.deleteTorunamentMatches(tid);
    }

}
