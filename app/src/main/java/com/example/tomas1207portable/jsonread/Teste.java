package com.example.tomas1207portable.jsonread;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Teste extends AppCompatActivity { //streamers
    private TextView txt;
    private Button bnt;
    private ArrayList<String> streamArray;
    private String nomeShared = "JsonShared";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Button move;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_main);
        new GetStreams(this).execute();
        final Context context = this;
        sharedPreferences = getSharedPreferences(nomeShared,MODE_PRIVATE);
        editor = getSharedPreferences(nomeShared,MODE_PRIVATE).edit();

        txt = findViewById(R.id.Login_MainScreen);
        bnt = findViewById(R.id.PostOnData);
        move = findViewById(R.id.Move);

        streamArray = new ArrayList<>();//Array streams online

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Teste.this, DataBaseController.class));
            }
        });

        txt.setText(sharedPreferences.getString("InLive","Estamos OffLine"));
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new postOnServer(context,"/Rest/example/addalunos","nome=tomas&idade=25&turma=407&curso=DDM").execute();
            }
        });
        editor.apply();
    }




}
