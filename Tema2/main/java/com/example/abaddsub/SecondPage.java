package com.example.abaddsub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondPage extends AppCompatActivity {

    TextView received;
    Button send_button;

    private static final String TAG = "SecondPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        received = findViewById(R.id.received);
        Intent intent_2 = getIntent();

        String A = intent_2.getStringExtra("A_key");
        String B = intent_2.getStringExtra("B_key");
        received.setText("Numbers: "+ A + ", " + B);
//        int AN = Integer.parseInt(A);
//        int BN = Integer.parseInt(B);
    }

    public void ADD(View view){
        setContentView(R.layout.activity_second_page);




        Intent intent = getIntent();

        String A = intent.getStringExtra("A_key");
        String B = intent.getStringExtra("B_key");


        int AN = Integer.parseInt(A);
        int BN = Integer.parseInt(B);

        int sum = AN + BN;
        Log.i(TAG, "ADD: " + sum);

        Intent intent_2 = new Intent(this, MainActivity.class);
        send_button = findViewById(R.id.ADDbutton);
        send_button.setOnClickListener(v -> {

            intent_2.putExtra("SUM_key", sum);
            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
            startActivity(intent_2);
        });
    }

    public void SUBSTRACT(View view){
        setContentView(R.layout.activity_second_page);

        Intent intent = getIntent();

        String A = intent.getStringExtra("A_key");
        String B = intent.getStringExtra("B_key");
        int AN = Integer.parseInt(A);
        int BN = Integer.parseInt(B);

        Intent intent_1 = new Intent(this, MainActivity.class);
        send_button = findViewById(R.id.ADDbutton);
        send_button.setOnClickListener(v -> {
            intent_1.putExtra("SUB_key", AN - BN);

            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
            startActivity(intent_1);
        });
    }

}







