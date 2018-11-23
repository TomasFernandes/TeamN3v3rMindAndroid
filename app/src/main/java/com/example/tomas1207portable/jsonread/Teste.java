package com.example.tomas1207portable.jsonread;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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
        setContentView(R.layout.activity_main);
        new GetContactsMain(this).execute();
        int sou;
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
                new postOnServer().execute();
            }
        });
        editor.apply();
    }



//TODO:Edit Fazer o remove desta class e por pelo GetContactsMain para aqui com sharedPref
    private static class postOnServer extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            URL url;
            try
            {
                url = new URL("http://www.tomasfernandes.pt/Rest/example/addalunos");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                String urlParams = "nome=tomas&idade=25&turma=407&curso=DDM";
                connection.setRequestMethod("POST");

                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParams);
                dStream.flush();
                dStream.close();

                int respondeCode = connection.getResponseCode();

            }
            catch (ProtocolException e) {
                e.printStackTrace();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
