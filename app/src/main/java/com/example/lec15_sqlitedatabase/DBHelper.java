package com.example.lec15_sqlitedatabase;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String STUDENT_ID = "StudentID";
    public static final String STUDENT_NAME = "StudentName";
    public static final String STUDENT_ROLL = "StudentRollNumber";
    public static final String STUDENT_ENROLL = "IsEnrolled";
    public static final String STUDENT_TABLE = "StudentTable";

    //constructor
    public DBHelper(@Nullable Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTableSTatementOne = "CREATE TABLE CustTable(CustomerID Integer PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME_FIRST + " Text, CustomerAge Int, ActiveCustomer BOOL) ";
        String createTableSTatement = "CREATE TABLE " + STUDENT_TABLE + "(" +
                STUDENT_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " Text, "
                + STUDENT_ROLL + " Int, " + STUDENT_ENROLL + " BOOL) ";
        db.execSQL(createTableSTatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
    }

    //---------------CRUD--------------
    //ADD
    public void  addStudent(StudentModel STUDENTModel){
        SQLiteDatabase db = this.getWritableDatabase();         //getting object of Db
        //Hash map, as we did in bundles
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_NAME, STUDENTModel.getName());
        cv.put(STUDENT_ROLL, STUDENTModel.getRollNmber());
        cv.put(STUDENT_ENROLL, STUDENTModel.isEnroll());

        db.insert(STUDENT_TABLE, null, cv);

        db.close();
        //NullCoumnHack
        //long insert =
        //if (insert == -1) { return false; }
        //else{return true;}
    }
    //Update
    public void updateStudent(int id, String newName, int newRollNumber, boolean newEnroll){
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_NAME, newName);
        cv.put(STUDENT_ROLL, newRollNumber);
        cv.put(STUDENT_ENROLL, newEnroll);

        SQLiteDatabase db = this.getWritableDatabase();         //getting object of Db
        int n=db.update(STUDENT_TABLE, cv, STUDENT_ID+"="+id, null);
        Log.d("rows updated: ", Integer.toString(n));
        db.close();
    }
    //Delete
    public void deleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();         //getting object of Db
        //Cursor cursorCourses = db.rawQuery("DELETE FROM " + STUDENT_TABLE, STUDENT_ID==id);

        int n=db.delete(STUDENT_TABLE, STUDENT_ID+"="+id, null);
        Log.d("rows deleted: ", Integer.toString(n));
        db.close();
    }
    //READ
    public ArrayList<StudentModel> getAllStudents() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + STUDENT_TABLE, null);

        ArrayList<StudentModel> studentArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                StudentModel s=new StudentModel(cursorCourses.getString(1),
                        cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1 ? true : false);
                        s.setID(cursorCourses.getInt(0));       //id also set
                studentArrayList.add(s);
            } while (cursorCourses.moveToNext());

        }
        cursorCourses.close();
        db.close();
        Log.d("getAll students"," run ok");
        return studentArrayList;
    }

}
