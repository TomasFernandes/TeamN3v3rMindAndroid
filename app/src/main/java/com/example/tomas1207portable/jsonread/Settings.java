package com.example.tomas1207portable.jsonread;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import static android.Manifest.permission.INTERNET;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Switch dadosMoveis = findViewById(R.id.switch2);
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final WifiManager  wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);


        dadosMoveis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked == true && wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(Settings.this,  wifiManager.getScanResults().toString(), Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Wifi ligado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}


