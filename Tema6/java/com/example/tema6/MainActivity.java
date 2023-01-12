package com.example.tema6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private Button btnCount;
    private EditText p_edit, v_edit, d_edit;
    private MediaPlayer mediaPlayer = null;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.status);
        //btnCount = findViewById(R.id.);
    }

    public void onClickStart(View view) {
        tvStatus.setText("Service status: STARTED");

        int p, v, d;
        p_edit = findViewById(R.id.period); v_edit = findViewById(R.id.value); d_edit = findViewById(R.id.duration);

        p = Integer.parseInt(p_edit.getText().toString());
        v = Integer.parseInt(v_edit.getText().toString());
        d = Integer.parseInt(d_edit.getText().toString());

        System.out.println(v);
        Intent intent = new Intent( this, MyService.class);
        //Bundle extrass = intent.getExtras();
        //extrass.putString("key", String.valueOf(v));


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startService(intent);
//                long start = System.currentTimeMillis();
//                long end = start + d * 1000;
//                while (System.currentTimeMillis() < end) {
//                    startService(intent);
//                }
            }
        }, p*1000);



    }

    public void onClickStop(View view) {
        tvStatus.setText("Service status: STOPPED");
        Intent intent = new Intent( this, MyService.class);
        stopService(intent);
    }

//    public void setVolume(int v){
//        int maxVolume = 300;
//        float log1=(float)(Math.log(maxVolume-v)/Math.log(maxVolume));
//        yourMediaPlayer.setVolume(log1,log1);
//    }


}