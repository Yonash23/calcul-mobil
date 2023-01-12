package com.example.tema6;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.EditText;

public class MyService extends Service {

    private MediaPlayer player;
    int FV = 200;
    MainActivity mainActivity = new MainActivity();
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        Intent intent1 = new Intent();
        String value = intent1.getExtras().getString("key");
        //System.out.println(value);


        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setVolume(FV,FV);
        player.setLooping(true);

        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
        player.stop();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i(TAG, "onTaskRemoved: ");
        stopSelf();
        super.onTaskRemoved(rootIntent);
    }
}