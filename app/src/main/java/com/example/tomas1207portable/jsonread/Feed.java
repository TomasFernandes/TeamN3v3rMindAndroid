package com.example.tomas1207portable.jsonread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class Feed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

    }
    public  class customAdtpor extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customlistviewfeed,null);
            ImageButton streamButton = convertView.findViewById(R.id.ImageStremerFeed);
            TextView titulo = convertView.findViewById(R.id.TituloFeed);
            TextView desc = convertView.findViewById(R.id.TextFeed);

            return convertView;
        }
    }
}
