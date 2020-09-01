package com.example.testapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtil
{
    private SharedPreferences mPref;
    private Context context;

    public static final String KEY_SAVE_DATA_TYPE = "key_save_data_type";
    //public static final String KEY_RADIO_DIALOG_VALUE = "key_radio_dialog_value";

    public PreferenceUtil(Context context)
    {
        this.context = context;
        if(mPref == null)
        {
            mPref = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public void putStringPreference(String key, String value)
    {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public String getStringPreference(String key)
    {
        return mPref.getString(key,null);
    }

    public String getStringPreference(String key, String defaultValue)
    {
        return mPref.getString(key,defaultValue);
    }

    public void putIntPreference(String key, int value)
    {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public int getIntPreference(String key)
    {
        return mPref.getInt(key,0);
    }

    public int getIntPreference(String key, int defaultValue)
    {
        return mPref.getInt(key,defaultValue);
    }

    public void putBooleanPreference(String key, boolean value)
    {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public boolean getBooleanPreference(String key)
    {
        return mPref.getBoolean(key,false);
    }

    public boolean getBooleanPreference(String key, boolean defaultValue)
    {
        return mPref.getBoolean(key,defaultValue);
    }

    public void removePreference(String key)
    {
        mPref.edit().remove(key).commit();
    }



}
