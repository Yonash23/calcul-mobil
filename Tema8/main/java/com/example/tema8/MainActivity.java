package com.example.tema8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";

    Spinner spinner1, spinner2;
    EditText editAmount;
    TextView resultText;
    String valuta1;
    String jsonURL = "http://www.floatrates.com/daily/ron.json";
    //String jsonURL = "https://reqres.in/api/users";
    Float curency, amount, result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        editAmount = findViewById(R.id.Amount);
        amount = Float.valueOf(10);
        //Float.valueOf(editAmount.getText().toString());

        resultText = findViewById(R.id.result);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.valute2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.valute, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemSelected: ");
        valuta1 = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), valuta1, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onClickConvert(View view) {
        Log.i(TAG, "onClickConvert: ");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(jsonURL)
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(TAG, "onFailure: ");
                //Toast.makeText( MainActivity. this,  "Something went wrong :(", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.i(TAG, "onResponse: ");
                String recData = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //resultText.setText(recData);
                        Log.i(TAG, "onResponse: data:" + recData);
                        try {
                            JSONObject json = new JSONObject(recData);
                            if (valuta1 == "USD") {
                                curency = Float.valueOf(json.getJSONObject("usd").getString("inverseRate"));
                                Log.i(TAG, "run: curency == " + curency);
                                result = amount / curency;
                                resultText.setText(result.toString());
                            } else if (valuta1 == "EUR") {
                                curency = Float.valueOf(json.getJSONObject("eur").getString("inverseRate"));
                                result = amount / curency;
                                resultText.setText(result.toString());
                            } else if (valuta1 == "GBP") {
                                curency = Float.valueOf(json.getJSONObject("gbp").getString("inverseRate"));
                                result = amount / curency;
                                resultText.setText(result.toString());
                            } else if (valuta1 == "UAH") {
                                curency = Float.valueOf(json.getJSONObject("auh").getString("inverseRate"));
                                result = amount / curency;
                                resultText.setText(result.toString());
                            } else if (valuta1 == "MDL") {
                                curency = Float.valueOf(json.getJSONObject("mdl").getString("inverseRate"));
                                result = amount / curency;
                                resultText.setText(result.toString());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });
    }
}