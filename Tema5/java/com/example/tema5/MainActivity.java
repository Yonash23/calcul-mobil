package com.example.tema5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    int numRan;
    int min=1,max=10;
    EditText variable;
    TextView text;
    private volatile boolean stopTask = false;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView2);
    }

    static int getRandomNumber(int max, int min)
    {
        return (int)((Math.random() * (max - min)) + min);
    }

    public void makeToast(String str)
    {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }
    public void clickFunction(View view)
    {
        int num;
        variable = findViewById(R.id.editText);
        num = Integer.parseInt(variable.getText().toString());


        if (num < numRan) {

            makeToast("E un numar mai mare");
        }
        else if (num > numRan) {
            makeToast("E un numar mai mic");
        }
        else {
            makeToast("Felicitări, ai ghicit");
            text.setText("Numărul ghicit este - "+ numRan);
            stopThread();
        }
    }

    public void startThread(View view) {
        stopTask = false;
        //se genereaza un numar random
        numRan = getRandomNumber(min, max);

        GuessNum runnable = new GuessNum(1000);
        new Thread(runnable).start();
    }

    public void stopThread(){
        stopTask = true;
        Log.i(TAG, "stopThread: ");
        //makeToast("Numar a fost gâcit de calculator: "+ numRan);

        text.setText("Numărul ghicit de calculator este - "+ numRan);

    }


class GuessNum implements Runnable{
        int seconds;

        public GuessNum(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            Log.i(TAG, "numar generat: "+ numRan);
            for(int i=0; i<seconds; i++){
                Log.i(TAG, i+" secunde trecute.");
                int numGenerat = getRandomNumber(min, max);
                Log.i(TAG, "numar generat: "+ numRan);
                Log.i(TAG, "run: "+numGenerat);
                if(numGenerat == numRan) {
                    stopThread();
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


