package com.example.tomas1207portable.jsonread.Core;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static  final  String CHANNEL_ID ="com.example.tomas1207portable.jsonread";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotify();
    }
    public void  createNotify(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel serveChannel = new NotificationChannel(
                    CHANNEL_ID,"Servico", NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serveChannel);
        }
    }
}
