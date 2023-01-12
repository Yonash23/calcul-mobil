package com.example.lab11_sqlite_exemple2;

import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String STUDENT_TABLE = "STUDENT_TABLE";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_STUDENT_AGE = "STUDENT_AGE";
    public static final String COLUMN_ACTIVE_STATUS = "ACTIVE_STATUS";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "students.db", null, 1);
    }

    @Override //executed the first time you open the DB, must include code to create table
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + STUDENT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_STUDENT_NAME + " TEXT, " + COLUMN_STUDENT_AGE + " INT, " + COLUMN_ACTIVE_STATUS + " BOOL)";

        db.execSQL(createTableStatement);

    }

    @Override //executes when the version of your DB changes
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(StudentModel studentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STUDENT_NAME,studentModel.getName());
        cv.put(COLUMN_STUDENT_AGE,studentModel.getAge());
        cv.put(COLUMN_ACTIVE_STATUS,studentModel.isActive());

        long insert = db.insert(STUDENT_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }//addOne

    public boolean deleteOne(StudentModel studentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + STUDENT_TABLE + " WHERE " + COLUMN_ID + " = " + studentModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
            // ???   db.close();
        } else {
            return false;
        }

    }//deleteOne

    public List<StudentModel> getEveryone(){
        List<StudentModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " +STUDENT_TABLE;     //get data from the db
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new student objects - inserting them in the list

            do{
                int studentID= cursor.getInt(0);
                String studentNAME= cursor.getString(1);
                int studentAGE= cursor.getInt(2);
                boolean studentACTIVE= cursor.getInt(3) == 1 ? true: false;     // ternary operator

                StudentModel studentModel=new StudentModel(studentID, studentNAME, studentAGE, studentACTIVE);
                returnList.add(studentModel);
            } while (cursor.moveToNext());
        }
        else{
            // the cursor did not select any entries in the DB, do not add anything to the list
        }

        // always close the cursor and the DB
        cursor.close();
        db.close();
        return returnList;
    }//list

    public List<StudentModel> findSearched(Integer age){
        List<StudentModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " +STUDENT_TABLE + " WHERE " + COLUMN_STUDENT_AGE + " = " + age;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new student objects - inserting them in the list

            do{
                int studentID= cursor.getInt(0);
                String studentNAME= cursor.getString(1);
                int studentAGE= cursor.getInt(2);
                boolean studentACTIVE= cursor.getInt(3) == 1 ? true: false;     // ternary operator

                StudentModel studentModel=new StudentModel(studentID, studentNAME, studentAGE, studentACTIVE);
                returnList.add(studentModel);
            } while (cursor.moveToNext());
        }
        else{
            // the cursor did not select any entries in the DB, do not add anything to the list
        }

        // always close the cursor and the DB
        cursor.close();
        db.close();
        return returnList;
    }

}//class
