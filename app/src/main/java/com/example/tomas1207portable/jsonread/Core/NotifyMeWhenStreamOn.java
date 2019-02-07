package com.example.tomas1207portable.jsonread.Core;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.tomas1207portable.jsonread.Nav;
import com.example.tomas1207portable.jsonread.R;


import static android.support.v4.app.NotificationCompat.*;
import static com.example.tomas1207portable.jsonread.Core.App.CHANNEL_ID;

public class NotifyMeWhenStreamOn extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent notifyIntent = new Intent(this, Nav.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notifyIntent,0);

        Notification notification = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            notification = new Notification.Builder(this,CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Ola").setContentText("Estou a correr")
                     .setContentIntent(pendingIntent).build();
            startForeground(1000,notification);
        }else  {

            Notification builder = new Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build();
        startForeground(10,builder);
        }







        return START_STICKY;
    }
}
