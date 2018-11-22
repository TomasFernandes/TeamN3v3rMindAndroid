package com.example.tomas1207portable.jsonread;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Weclome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("JsonRead",MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weclome);
        sharedPreferences.getBoolean("FristTime",false);

    }
}
