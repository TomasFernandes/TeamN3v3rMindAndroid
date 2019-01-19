package com.example.tomas1207portable.jsonread;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class InitApplication extends Application {
    public static final String NIGHT_MODE = "NIGHT_MODE";
    private boolean isNightModeEnabled = false;
    static SharedPreferences shared;
    private static InitApplication singleton = null;

    public static InitApplication getInstance(Context context) {

        if(singleton == null)
        {
            singleton = new InitApplication();
        }
        shared = context.getSharedPreferences("JsonShared",MODE_PRIVATE);
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        this.isNightModeEnabled = shared.getBoolean(NIGHT_MODE, false);
    }

    public boolean isNightModeEnabled() {
        return isNightModeEnabled;
    }

    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
        this.isNightModeEnabled = isNightModeEnabled;

        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(NIGHT_MODE, isNightModeEnabled);
        editor.apply();
    }
}

