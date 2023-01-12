package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText EdTx1, EdTx2, EdTx3;
    TextView text1, text2, text3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View view){
        EdTx1 = findViewById(R.id.editText);
        EdTx2 = findViewById(R.id.editText2);
        EdTx3 = findViewById(R.id.editText3);

        text1 = findViewById(R.id.name);
        text2 = findViewById(R.id.surname);
        text3 = findViewById(R.id.mail);

        text1.setText("Name: "+EdTx1.getText().toString());
        text2.setText("Surname: "+EdTx2.getText().toString());
        text3.setText("Mail: "+EdTx3.getText().toString());
    }
}