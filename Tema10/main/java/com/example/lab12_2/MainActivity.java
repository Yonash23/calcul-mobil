package com.example.lab12_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView textView;
    SeekBar seekBar;
    SensorManager sensorManager;
    Sensor mySensor;
    private int brightness;
    int some;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askPermission(this);

        textView = findViewById(R.id.textview);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setMax(255);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                Settings.System.putInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS, progress);
                textView.setText("Light intensity \n" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        if (mySensor == null) {
            Toast.makeText(this, "No light sensor found in device.", Toast.LENGTH_LONG).show();
            finish();
        } else {
            sensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        try {
            Settings.System.putInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

            brightness = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);


        } catch (Exception e) {
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }


    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            //textView.setText("Light intensity \n" + event.values[0]);

            some = Math.round(event.values[0]) / 157;

            textView.setText("Light intensity \n" + some);
            Settings.System.putInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS, some);

        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    public void askPermission(Context c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(c)) {
                // you have permissio to write settings
            }
            else{
                    Intent i = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    c.startActivity(i);
            }
        }
    }
}

















