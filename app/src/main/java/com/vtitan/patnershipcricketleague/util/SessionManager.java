package com.vtitan.patnershipcricketleague.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {


    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Pref";
    public static final String KEY_THEAM = "light_mode";
    public static final String KEY_TID ="TID";
    public static final String KEY_TEAM_ID ="TEAM_ID";
    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void savelightmode(boolean theamID) {
        editor.putBoolean(KEY_THEAM, theamID);
        editor.commit();
    }

    public void saveTournamentID(String tournamentID) {
        editor.putString(KEY_TID, tournamentID);
        editor.commit();
    }
    public void saveTeamID(int teamID) {
        editor.putInt(KEY_TEAM_ID, teamID);
        editor.commit();
    }



    public boolean isLightModeOn() {
        return pref.getBoolean(KEY_THEAM, false);
    }

    public String getTournamentId(){
        return pref.getString(KEY_TID,null);
    }

    public int getTeamId(){
        return pref.getInt(KEY_TEAM_ID,0);
    }

    public void clearallData() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }
}
