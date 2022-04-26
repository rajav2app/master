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
}
