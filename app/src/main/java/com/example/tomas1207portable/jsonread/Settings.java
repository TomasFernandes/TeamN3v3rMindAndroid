package com.example.tomas1207portable.jsonread;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.MailTo;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Boolean isNight;
        super.onCreate(savedInstanceState);

        if (InitApplication.getInstance(Settings.this).isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            isNight = true;
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isNight = false;
        }
        setContentView(R.layout.activity_settings);

        Switch dadosMoveis = findViewById(R.id.switch2);
        Switch modoOscuro = findViewById(R.id.switch1);
        modoOscuro.setChecked(isNight);

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);


        dadosMoveis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked == true && mWifi.isConnected() == false) {
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(Settings.this, "Desativando auto-refresh", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Wifi ligado", Toast.LENGTH_LONG).show();
                }
            }
        });

        modoOscuro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked == true) {
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    InitApplication.getInstance(Settings.this).setIsNightModeEnabled(true);
                    finish();
                    startActivity(intent);

                } else {
                    InitApplication.getInstance(Settings.this).setIsNightModeEnabled(false);
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Nav.class));
        super.onBackPressed();
    }
}



