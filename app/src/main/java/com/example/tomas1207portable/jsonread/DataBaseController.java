package com.example.tomas1207portable.jsonread;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DataBaseController extends AppCompatActivity {
    private Button bnt_dataBaseTest;
    private TextView tv_dataBaseTest;
    private String nomeShared = "JsonShared";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_controller);
        bnt_dataBaseTest=findViewById(R.id.Download_test);
        tv_dataBaseTest=findViewById(R.id.DataBase_test);
        editor = getSharedPreferences(nomeShared,MODE_PRIVATE).edit();

        new GetContactsMain(this).execute();

        bnt_dataBaseTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(DataBaseController.this,""+ sharedPreferences.getBoolean("DataFromServer",false), Toast.LENGTH_SHORT).show();


            }
        });
        editor.apply();
    }

}
