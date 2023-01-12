package com.example.tema3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button prev, next, details;
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        details = findViewById(R.id.detail);

        Intent intent = new Intent(this, SecondActivity.class);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count == 1)
                    replaceFragment(new Fragment1());
                else if(count == 2)
                    replaceFragment(new Fragment2());
                else if(count == 3)
                    replaceFragment(new Fragment3());
                else if(count == 4)
                    replaceFragment(new Fragment4());
                else if(count == 5)
                    replaceFragment(new Fragment5());
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                if(count == 1)
                    replaceFragment(new Fragment1());
                else if(count == 2)
                    replaceFragment(new Fragment2());
                else if(count == 3)
                    replaceFragment(new Fragment3());
                else if(count == 4)
                    replaceFragment(new Fragment4());
                else if(count == 5)
                    replaceFragment(new Fragment5());
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("key", count);
                startActivity(intent);
            }
        });
    }

    private void replaceFragment(Fragment fragmentParam){
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction. replace(R.id.frameLayout, fragmentParam);
        fragTransaction.commit();
    }
}