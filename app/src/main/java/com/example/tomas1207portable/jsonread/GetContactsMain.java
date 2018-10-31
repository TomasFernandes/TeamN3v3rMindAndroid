package com.example.tomas1207portable.jsonread;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class GetContactsMain extends AsyncTask<Void, Void, Void> {
    private JSONObject Channel;
    private Context context;
    private ArrayList<String> streamArray = new ArrayList<String>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor ;
    private String Display_Name;
    private Boolean HaveData = false;
    public GetContactsMain(Context context) {
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
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://tomasfernandes.pt/Beta/Streams.php";
        String jsonStr = sh.makeServiceCall(url);
        if(HaveData != null){
            HaveData = true;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                JSONArray contacts = jsonObj.getJSONArray("streams");

                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);
                    String stream_private = c.getString("stream");
                    if (stream_private != "null") {
                        JSONObject stream = c.getJSONObject("stream");
                        Channel = stream.getJSONObject("channel");
                        Display_Name = Channel.getString("display_name");
                        Log.d("Json", "Json:" + stream);
                        streamArray.add(Display_Name);

                        editor.putString("DisplayName", Display_Name);
                        editor.putString("InLive", streamArray.toString());
                        editor.putBoolean("DataFromServer",HaveData);
                        editor.commit();
                    }else {
                        editor.remove("DisplayName");
                        editor.remove("InLive");
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
