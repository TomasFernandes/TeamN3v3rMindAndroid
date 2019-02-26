package com.example.tomas1207portable.jsonread.Activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tomas1207portable.jsonread.Core.InitApplication;
import com.example.tomas1207portable.jsonread.R;

public class Feed extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (InitApplication.getInstance(Feed.this).isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_feed);


        final ListView listView =  findViewById(R.id.List1);
        final customAdtpor Custom = new customAdtpor();
        listView.setAdapter(Custom);
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.SwipeUp);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listView.setAdapter(Custom);
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }
    public  class customAdtpor extends BaseAdapter{

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
            ImageButton streamButton = convertView.findViewById(R.id.Bnt_StreamImage);
            TextView StreamName = convertView.findViewById(R.id.TextFeed);
            TextView StreamLiveTitle = convertView.findViewById(R.id.TituloFeed);
            StreamName.setText("" + position);


            return convertView;
        }
    }
}
