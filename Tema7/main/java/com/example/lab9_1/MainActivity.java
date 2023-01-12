package com.example.lab9_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView tvcReceivedData;
    private EditText etcIP, etcPORT;
    private Button btnClientConnect;
    private String ServerName;
    private int ServerPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");

        tvcReceivedData = findViewById(R.id.textView);
        etcIP = findViewById(R.id.etcip);
        etcPORT = findViewById(R.id.etcport);
        btnClientConnect = findViewById(R.id.btnClientConn);
    }

    public void onClickBtnClientConnect(View view) {
        Log.i(TAG, "onClickBtnClientConnect: ");
        ServerName=etcIP.getText().toString();
        ServerPort = Integer.valueOf(etcPORT.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(ServerName, ServerPort);

                    BufferedReader br_input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String txtFromServer = br_input.readLine();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvcReceivedData.setText(txtFromServer);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
