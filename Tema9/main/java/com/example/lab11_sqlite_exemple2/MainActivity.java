package com.example.lab11_sqlite_exemple2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, searchText;
    CheckBox cbStatus;
    Button btnView, btnAdd, btnSearch;
    ListView mainListView;
    ArrayAdapter studentArrayAdapter;
    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        cbStatus = findViewById(R.id.cbStatus);
        btnView = findViewById(R.id.btnView);
        btnAdd = findViewById(R.id.btnAdd);
        mainListView = findViewById(R.id.mainListView);
        btnSearch = findViewById(R.id.btnsearch);
        searchText = findViewById(R.id.searchText);


        //displaying the DB content inside the listview as soon as the app starts
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        //DisplayStudentsInListView(dataBaseHelper);
        studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone());
        mainListView.setAdapter(studentArrayAdapter);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<StudentModel> everyone = dataBaseHelper.getEveryone();
                //DisplayStudentsInListView(dataBaseHelper);
                studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                mainListView.setAdapter(studentArrayAdapter);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudentModel studentModel = null;

                try{
                    studentModel = new StudentModel(-1, etName.getText().toString(),Integer.parseInt(etAge.getText().toString()), cbStatus.isChecked());
                    Toast.makeText(getApplicationContext(), studentModel.toString(),Toast.LENGTH_LONG).show();
                }
                catch(Exception exception){
                    studentModel = new StudentModel(-1,"Error",0,false);
                    Toast.makeText(getApplicationContext(), exception.toString(),Toast.LENGTH_LONG).show();
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean actionSuccess = dataBaseHelper.addOne(studentModel);
                Toast.makeText(getApplicationContext(), "Added successfully: "+actionSuccess,Toast.LENGTH_LONG).show();

                //update the listview content
                dataBaseHelper = new DataBaseHelper(MainActivity.this);
                //DisplayStudentsInListView(dataBaseHelper);
                studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone());
                mainListView.setAdapter(studentArrayAdapter);

            }
        });

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentModel clickedStudent = (StudentModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedStudent);

                studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone());
                mainListView.setAdapter(studentArrayAdapter);

                Toast.makeText(getApplicationContext(), "Student deleted: "+clickedStudent.toString(),Toast.LENGTH_LONG).show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<StudentModel> searched = dataBaseHelper.findSearched(Integer.parseInt(searchText.getText().toString()));
                //DisplayStudentsInListView(dataBaseHelper);
                studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1, searched);
                mainListView.setAdapter(studentArrayAdapter);
            }
        });


    }//onCreate

}//main