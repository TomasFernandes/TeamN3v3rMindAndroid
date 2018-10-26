package com.example.tomas1207portable.jsonread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DataBaseController extends AppCompatActivity {
    private Button bnt_dataBaseTest;
    private TextView tv_dataBaseTest;
    private DataBaseTester DataBaseTester1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_controller);
        bnt_dataBaseTest=findViewById(R.id.Download_test);
        tv_dataBaseTest=findViewById(R.id.DataBase_test);
        bnt_dataBaseTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Make a server conn whit outter class to see how to make:P
                if (DataBaseTester1.getHaveData() == false) {
                    Toast.makeText(DataBaseController.this, "No connection to server try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
