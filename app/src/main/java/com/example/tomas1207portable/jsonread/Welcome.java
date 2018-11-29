package com.example.tomas1207portable.jsonread;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Welcome extends AppCompatActivity {
 private Button registar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("JsonShared",MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weclome);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("FirstTime",false);
        try {
            Log.w("Pass",PassEncrypter.encrypt("Ola"));

        } catch (Exception e) {
            e.printStackTrace();
        }


        registar = findViewById(R.id.Btn_Register);
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,Nav.class));
            }
        });
    }

}
