package com.vtitan.patnershipcricketleague.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vtitan.patnershipcricketleague.model.Matches;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.repository.TournamentRepository;

import java.util.List;

public class MatchViewModel extends AndroidViewModel {

    private final TournamentRepository mRepository;
    public MatchViewModel(Application application) {
        super(application);
        mRepository = new TournamentRepository(application);
    }

    public Long[] insertMatches(List<Matches> matches){
      return mRepository.insertMatches(matches);
    }

    public LiveData<List<String>> getAllRounds(String tID){
        return mRepository.getAllRounds(tID);
    }

    public LiveData<List<Matches>> getAllMatches(String rounds){
        return mRepository.getAllMatches(rounds);
    }

    public LiveData<List<Matches>> getMatches(){
        return mRepository.getMatches();
    }

    public LiveData<List<Matches>> getTeamMatches(int teamId){
        return mRepository.getTeamMatches(teamId);
    }
    public int updateMatchDetails(String venue,long date,int over,int toss,int bat,int matchNo)
    {
        return mRepository.updateMatchDetails(venue,date,over,toss,bat,matchNo);
    }

    public LiveData<Matches>getMatchDetails(int matchNo,int tournamentId){
        return mRepository.getMatchDetails(matchNo,tournamentId);
    }

    public void updateMatchStatus(int status,int matchNo,int tournamentId){
        mRepository.updateMatchStatus(status,matchNo,tournamentId);
    }
}
