package com.example.tomas1207portable.jsonread.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.tomas1207portable.jsonread.Nav;
import com.example.tomas1207portable.jsonread.R;

import com.example.tomas1207portable.jsonread.Core.InitApplication;



public class Preferences extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Boolean isNight;
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        PreferenceManager preferenceManager = getPreferenceManager();

        Preference switchOsc = findPreference("KeyOscuro");
        switchOsc.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean isOn = (boolean) newValue;

                if(isOn==true){

                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    InitApplication.getInstance(Preferences.this).setIsNightModeEnabled(true);
                    finish();
                    startActivity(intent);

                }else{

                    InitApplication.getInstance(Preferences.this).setIsNightModeEnabled(false);
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(intent);

            }
                return true;
            }
        });

//        Preference switchDados = findPreference("KeyDados");
//        switchDados.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                Boolean isOn = (boolean) newValue;
//
//                if(isOn==true){
//
//                }
//
//                return true;
//            }
//        });
//
//        ConstraintLayout constraintLayout = findViewById(R.id.constraint_preferences);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View preferencesCompleta = inflater.inflate(R.xml.pref_general, constraintLayout, true);





//
//        if (preferenceManager.getSharedPreferences().getBoolean("keyOscuro", true)) {
//            Intent intent = getIntent();
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            InitApplication.getInstance(Preferences.this).setIsNightModeEnabled(true);
//            finish();
//            startActivity(intent);
//        } else {
//            InitApplication.getInstance(Preferences.this).setIsNightModeEnabled(false);
//            Intent intent = getIntent();
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            finish();
//            startActivity(intent);
//        }
//
//
        if (InitApplication.getInstance(Preferences.this).isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            isNight = true;
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isNight = false;
        }
//
//        SwitchPreference dadosMoveis1 = (SwitchPreference) findPreference("KeyDados");
//        SwitchPreference modoOscuro1 = (SwitchPreference) findPreference("KeyOscuro");
//        modoOscuro1.setChecked(isNight);
//
//
//        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        final NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//
//        final WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//
//
//        dadosMoveis1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object isChecked) {
//
//
//                if (mWifi.isConnected() == false) {
//                    wifiManager.setWifiEnabled(true);
//                    Toast.makeText(Preferences.this, "Desativando auto-refresh", Toast.LENGTH_SHORT).show();
//
//
//                } else {
//                    Toast.makeText(Preferences.this.getApplicationContext(), "Wifi ligado", Toast.LENGTH_LONG).show();
//                }
//                return true;
//            }
//        }
//        );


//        modoOscuro1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//
//
//                if (newValue == true) {
//                    Intent intent = getIntent();
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    InitApplication.getInstance(Preferences.this).setIsNightModeEnabled(true);
//                    finish();
//                    startActivity(intent);
//
//                } else {
//                    InitApplication.getInstance(Preferences.this).setIsNightModeEnabled(false);
//                    Intent intent = getIntent();
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    finish();
//                    startActivity(intent);
//                }return false;
//            }
//        });
//    }

//
    }
}


