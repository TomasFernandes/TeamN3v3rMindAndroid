package com.example.tomas1207portable.jsonread;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {
 private Button registar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("JsonRead",MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weclome);
        sharedPreferences.getBoolean("FirstTime",false);
        registar = findViewById(R.id.Btn_Register);
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,Nav.class));
            }
        });
    }
}
