package com.example.tomas1207portable.jsonread;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private Button bnt;
    private ArrayList<String> streamArray;
    private JSONObject Channel;
    private String nomeShared = "JsonShared";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Button move;
    private String Display_Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(nomeShared,MODE_PRIVATE);
        editor = getSharedPreferences(nomeShared,MODE_PRIVATE).edit();

        txt = findViewById(R.id.Login_MainScreen);
        bnt = findViewById(R.id.PostOnData);
        move = findViewById(R.id.Move);

        streamArray = new ArrayList<>();

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DataBaseController.class));
            }
        });
        new GetContacts().execute();
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new postOnServer().execute();
            }
        });
        editor.commit();
    }



    
    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://tomasfernandes.pt/Beta/Streams.php";
            String jsonStr = sh.makeServiceCall(url);


            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray contacts = jsonObj.getJSONArray("streams");

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String stream_private = c.getString("stream");
                        if(stream_private.equals("null" ))
                        {
                            JSONObject stream = c.getJSONObject("stream");
                            Channel = stream.getJSONObject("channel");
                            Display_Name = Channel.getString("display_name");
                            Log.w("Info","Json:"+ stream);
                            streamArray.add(Display_Name);
                            editor.putString("DisplayName",Display_Name);
                            editor.putString("InLive",streamArray.toString());
                            editor.commit();
                        }
                    }

                }
                    catch (final JSONException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();

                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            txt.setText(streamArray.toString());

        }
    }
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
