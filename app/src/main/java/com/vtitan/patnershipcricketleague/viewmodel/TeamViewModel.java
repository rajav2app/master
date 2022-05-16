package com.vtitan.patnershipcricketleague.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vtitan.patnershipcricketleague.model.Players;
import com.vtitan.patnershipcricketleague.model.Teams;
import com.vtitan.patnershipcricketleague.repository.TournamentRepository;

import java.util.List;

public class TeamViewModel extends AndroidViewModel {
    private final TournamentRepository mRepository;
    public TeamViewModel(Application application) {
        super(application);
        mRepository = new TournamentRepository(application);
    }
    public LiveData<Integer> getTeamCount(String tournamentID){
        return mRepository.teamCount(tournamentID);
    }
    public long insertTeam(Teams teamModel){
        return mRepository.insertTeam(teamModel);
    }

    public LiveData<Integer> getPlayerCount(int teamID){
        return mRepository.playerCount(teamID);
    }
    public long insertPlayers(Players players){
        return mRepository.insertPlayers(players);
    }

    public int updatePlayerdetails(String playername,int playerstate,int playerid){
        return mRepository.updatePlayerDetails(playername,playerstate,playerid);
    }
    public LiveData<List<Players>>getAllPlayers(int teamID){
      return mRepository.getAllPlayers(teamID);
    }

    public LiveData<List<Teams>>getAllTeams(String tID){
     return mRepository.getAllTeams(tID);
    }
    public LiveData<Teams>getTeamDetails(int tID){
        return mRepository.getTeamDetails(tID);
    }

    public int getTeamId(String teamName){
        return mRepository.getTeamID(teamName);
    }
    public LiveData<Integer> getPlayerState(int teamId){
        return mRepository.getPlayerState(teamId);
    }

    public int updateTeamDetails(String teamName,String teamLocation,int teamId){
        return mRepository.updateTeamDetails(teamName,teamLocation,teamId);
    }
    public String getTeamName(int teamId){
        return mRepository.getTeamName(teamId);
    }
}
