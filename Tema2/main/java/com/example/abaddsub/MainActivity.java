package com.example.abaddsub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    Button send_button;
    EditText send_A;
    EditText send_B;
    TextView Result;
    TextView Sub_N;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        String Add = intent.getStringExtra("ADD_key");
        String Subs = intent.getStringExtra("SUB_key");

        Result = findViewById(R.id.Rez);

        Result.setText("Result sum: "+ Add + ", substruct: " + Subs);

        
        //Sub_N.setText(Subs);
    }

    public void SaveAndMove(View view){
        Intent intent_1 = new Intent(this, SecondPage.class);

        send_A = findViewById(R.id.TextA);
        send_B = findViewById(R.id.TextB);
        send_button = findViewById(R.id.button1);

        send_button.setOnClickListener(v -> {
            String a = send_A.getText().toString();
            String b = send_B.getText().toString();

            intent_1.putExtra("A_key", a);
            intent_1.putExtra("B_key", b);
            Toast.makeText(getApplicationContext(), "Data was sent!", Toast.LENGTH_SHORT).show();
            startActivity(intent_1);
        });



    }
}