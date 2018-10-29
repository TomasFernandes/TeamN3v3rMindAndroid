package com.example.tomas1207portable.jsonread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DataBaseController extends AppCompatActivity {
    private Button bnt_dataBaseTest;
    private TextView tv_dataBaseTest;
    private DataBaseTester dataBaseTester;
    private Boolean haveData = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_controller);
        bnt_dataBaseTest=findViewById(R.id.Download_test);
        tv_dataBaseTest=findViewById(R.id.DataBase_test);
        new DataBaseTester().execute();

        bnt_dataBaseTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(haveData){
                Toast.makeText(DataBaseController.this, "Have data", Toast.LENGTH_SHORT).show();
            }

            }
        });
    }
    public class DataBaseTester extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }

        @Override
        protected void onCancelled() {
            haveData = false;
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String url = "http://tomasfernandes.pt/Beta/Streams.php";
            String jsonStr = sh.makeServiceCall(url);
            if(jsonStr == null){
                haveData = false;
            }
            return null;
        }
    }
}
