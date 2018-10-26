package com.example.tomas1207portable.jsonread;

import android.os.AsyncTask;

import java.net.URL;

public class DataBaseTester extends AsyncTask<Void, Void, Void> {
    private URL url;
    private Boolean haveData;

    public Boolean getHaveData() {
        return haveData;
    }

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
        haveData = jsonStr != null;
        return null;
    }
}
