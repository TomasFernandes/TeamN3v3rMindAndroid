package com.example.tomas1207portable.jsonread;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;


public class Nav extends AppCompatActivity //principal
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ListView streamList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
//
//        WebView webView;
//        webView = findViewById(R.id.Twitch);
//        URL url = null;
//        try {
//             url = new URL("https://twitch.tv/tomas1207");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        webView.loadUrl(String.valueOf(url));

//TODO:Ver como fazer para ler a apagina da twicht na app

        MakeCall();// call the server

        sharedPreferences = getSharedPreferences("JsonShared",MODE_PRIVATE);//shared init
        editor = sharedPreferences.edit();

        streamList = (ListView) findViewById(R.id.StreamsList);
        customAdtpor adtpor = new customAdtpor();
        streamList.setAdapter(adtpor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //show bar in both of phone with info from API but need pass from sharedpreference Name:JsonRead key:inLive nomes dos streams
                Snackbar.make(view, sharedPreferences.getString("InLive","Sao todos uns preguiçosos"), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                }
        });

        deleteAllShared();//delete all shared if existed


        if(sharedPreferences.getBoolean("FirstTime",true) != false){
        startActivity(new Intent(this, Welcome.class));
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void MakeCall(){
        new GetStreams(this).execute();//call class to get API context
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //TODO:Change Mudar os nomes dos ids :P
        if (id == R.id.nav_camera) {
            startActivity(new Intent(Nav.this, Teste.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_settings) {
            startActivity(new Intent (Nav.this, Settings.class));

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //TODO:Maybey Create a  sharedController.class


    /**
     * delete all shared preferences from app,
     * @return null;
     */
    private void deleteAllShared(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("DisplayName");
        editor.remove("InLive");
        editor.apply();
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
            convertView = getLayoutInflater().inflate(R.layout.customlistviewstreams,null);
            ImageButton streamButton = convertView.findViewById(R.id.Bnt_StreamImage);
            TextView StreamName = convertView.findViewById(R.id.StreamerName);
            TextView StreamLiveTitle = convertView.findViewById(R.id.StreamTitle);
            TextView StreamViews = convertView.findViewById(R.id.StreamLiveViews);
            StreamName.setText(sharedPreferences.getString("InLive",null));


            return convertView;
        }
    }
}
