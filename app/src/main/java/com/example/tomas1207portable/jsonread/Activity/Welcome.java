package com.example.tomas1207portable.jsonread.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tomas1207portable.jsonread.Core.InitApplication;
import com.example.tomas1207portable.jsonread.Nav;
import com.example.tomas1207portable.jsonread.PassEncrypter;
import com.example.tomas1207portable.jsonread.R;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Welcome extends AppCompatActivity {
    private Button registar;
    private EditText user, pass;
    static String Suser;
    static String Spass;
    static int responceCode;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("JsonShared", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        if (InitApplication.getInstance(Welcome.this).isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_weclome);
        editor = sharedPreferences.edit();
        user = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);

        try {
            Log.w("Pass", PassEncrypter.encrypt("Ola"));

        } catch (Exception e) {
            e.printStackTrace();
        }


        registar = findViewById(R.id.Btn_Register);
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string_Welcome_User, string_Welcome_Pass;
                string_Welcome_User = user.getText().toString();
                string_Welcome_Pass = pass.getText().toString();

                if (string_Welcome_User.matches("")) {
                    user.setError("Falta o user");
                } else {
                    if (string_Welcome_Pass.matches("")) {
                        pass.setError("Falta a pass");
                    } else {
                        editor.putBoolean("FirstTime", false);
                        editor.apply();
                        try {
                            Spass = PassEncrypter.encrypt(pass.getText().toString());
                            new postUserPass().execute();
                            Log.w("Server", "" + responceCode);
                            startActivity(new Intent(Welcome.this, Nav.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }

            }


        });
    }

    private static class postUserPass extends AsyncTask<Void, Void, Void> {

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
            try {
                url = new URL("http://www.tomasfernandes.pt/Rest/example/addUser");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                String urlParams = "user=" + Suser + "&pass=" + Spass;
                connection.setRequestMethod("POST");

                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParams);
                dStream.flush();
                dStream.close();

                responceCode = connection.getResponseCode();

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
