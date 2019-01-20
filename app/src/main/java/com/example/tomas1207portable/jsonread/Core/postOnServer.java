package com.example.tomas1207portable.jsonread.Core;

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
    String Link,Dados;

    /**
     *
     * @param context Context for he know here is runing
     * @param link  Link to he know here go POST data
     * @param dados Dados for he POST Data on link
     * @return Code from server to se if he received.
     */
    public postOnServer(Context context,String link, String dados){
    this.context = context;
    this.Link = link;
    this.Dados = dados;
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
            url = new URL("http://www.tomasfernandes.pt"+Link);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            String urlParams = Dados;
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
