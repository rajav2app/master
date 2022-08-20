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
    public void updateb1Score(int run,int matchNo,int tournamentId,int pid){
        mRepository.updateb1Score(run,matchNo,tournamentId,pid);
    }

    public void updateb2Score(int run,int matchNo,int tournamentId,int pid){
        mRepository.updateb2Score(run,matchNo,tournamentId,pid);
    }
    public void updateb3Score(int run,int matchNo,int tournamentId,int pid){
        mRepository.updateb3Score(run,matchNo,tournamentId,pid);
    }
    public void updateb4Score(int run,int matchNo,int tournamentId,int pid){
        mRepository.updateb4Score(run,matchNo,tournamentId,pid);
    }
    public void updateb5Score(int run,int matchNo,int tournamentId,int pid){
        mRepository.updateb5Score(run,matchNo,tournamentId,pid);
    }
    public void updateb6Score(int run,int matchNo,int tournamentId,int pid){
        mRepository.updateb6Score(run,matchNo,tournamentId,pid);
    }
    public void updatextraScore(int run,int matchNo,int tournamentId,int pid){
        mRepository.updatextra(run,matchNo,tournamentId,pid);
    }
    public void updatTotalrun(int run,int matchNo,int tournamentId){
        mRepository.updateTotalRun(run,matchNo,tournamentId);
    }
    public LiveData<Integer>getTotalrun(int matchNo,int tournamentId){
        return mRepository.getTotalrun(matchNo,tournamentId);
    }
    public void updateMatchDetails(String teamName,String oldTeamName){
       mRepository.updateTeamHomeDetails(teamName,oldTeamName);
    }
    public void updateawayTeamDetails(String teamName,String oldTeamName){
         mRepository.updateawayTeamDetails(teamName,oldTeamName);
    }

    public Integer getB1Run(int matchNo,int tournamentId){
     return  mRepository.getB1Run(matchNo,tournamentId);
    }
    public Integer getB2Run(int matchNo,int tournamentId){
        return  mRepository.getB2Run(matchNo,tournamentId);
    }
    public Integer getB3Run(int matchNo,int tournamentId){
        return  mRepository.getB3Run(matchNo,tournamentId);
    }
    public Integer getB4Run(int matchNo,int tournamentId){
        return  mRepository.getB4Run(matchNo,tournamentId);
    }
    public Integer getB5Run(int matchNo,int tournamentId){
        return  mRepository.getB5Run(matchNo,tournamentId);
    }
    public Integer getB6Run(int matchNo,int tournamentId){
        return  mRepository.getB6Run(matchNo,tournamentId);
    }


}
