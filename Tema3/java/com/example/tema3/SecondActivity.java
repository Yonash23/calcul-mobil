package com.example.tema3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView text;
    Button btn;
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        int defaulT = 0;
        text = findViewById(R.id.textView);
        btn = findViewById(R.id.button);

        Intent intent1 = new Intent(this, MainActivity.class);
        int count = getIntent().getIntExtra("key", defaulT);

        Log.i(TAG, "onCreate: " + count);

        if(count == 1)
            text.setText("Place - Geirangerfjord, Norway");
        else if(count == 2)
            text.setText("Place - Kawachi Fuji Garden, Japan");
        else if(count == 3)
            text.setText("Place - The ancient city of Petra, Jordan");
        else if(count == 4)
            text.setText("Place - Bora-Bora, French Polynesia");
        else if(count == 5)
            text.setText("Place - Mu Cang Chai,  Vietnam");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });
    }
}