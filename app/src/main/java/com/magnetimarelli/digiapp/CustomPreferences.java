package com.magnetimarelli.digiapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CustomPreferences extends Activity{
    //Define the pref and main editor
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    //Define editor on class build.
    public CustomPreferences(Context context){
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
    }

    //Get login details and save to shared preferences.
    public void inPref(String key, String value){
        editor.putString(key, value);
        editor.apply();
    }

    public String outPref (String key){
        return pref.getString(key, "0");
    }
}
