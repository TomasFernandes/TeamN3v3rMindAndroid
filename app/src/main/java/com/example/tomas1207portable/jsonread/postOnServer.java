package com.example.tomas1207portable.jsonread;

import android.content.Context;
import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class postOnServer extends AsyncTask<Void, Void, Void> {
    Context context;
public postOnServer(Context context){
    this.context = context;
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
