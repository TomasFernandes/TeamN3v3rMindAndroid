package com.example.tomas1207portable.jsonread.Core;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class GetStreams extends AsyncTask<Void, Void, Void> {
    private JSONObject Channel;//channel of json
    private Context context;
    private ArrayList<String> streamArray = new ArrayList<>();//streamer array
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor ;
    private String Display_Name;//string of display name
    private Boolean HaveData = false;//check if server is on
    private ArrayList<String> Logo = new ArrayList<>();
    private String logoString;
    public GetStreams(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Json Data is downloading", Toast.LENGTH_LONG).show();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
       sharedPreferences = context.getSharedPreferences("JsonShared",Context.MODE_PRIVATE);
       editor = sharedPreferences.edit();
        HttpHandler sh = new HttpHandler();//make a call to httpHandler class
        // Making a request to url and getting response
        String url = "http://tomasfernandes.pt/Beta/Streams.php";// server url
        String jsonStr = sh.makeServiceCall(url);//json get
        if(HaveData != null){
            HaveData = true;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                JSONArray streams = jsonObj.getJSONArray("streams");//Get streams object

                for (int i = 0; i <= streams.length(); i++) {
                    JSONObject c = streams.getJSONObject(i);
                    String stream_private = c.getString("stream");
                    if (stream_private != "null") {
                        JSONObject stream = c.getJSONObject("stream");//get stream object
                        Channel = stream.getJSONObject("channel");//get channel object
                        Display_Name = Channel.getString("display_name");//get displayname property
                        logoString = Channel.getString("logo");
                        Logo.add(logoString);
                        Log.d("Json", "Json:" + stream);//write in log for developer
                        streamArray.add(Display_Name); // add to array: DisplayName
                        editor.putString("DisplayName", Display_Name);
                        editor.putString("InLive", streamArray.toString());
                        editor.putString("LogoURL",Logo.toString());
                        editor.putBoolean("DataFromServer",HaveData);
                        editor.apply();
                    }else {
                    }
                }

            } catch (final JSONException e) {
                Log.d("Json",e.getMessage());
            }
        }
        }else  {
            HaveData = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
    }
}
