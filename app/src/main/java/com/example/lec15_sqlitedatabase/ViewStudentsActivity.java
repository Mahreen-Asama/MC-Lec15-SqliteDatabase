package com.example.lec15_sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentsActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        lv=findViewById(R.id.lv);

        DBHelper dbHelper = new DBHelper(ViewStudentsActivity.this);
        List<StudentModel> list = dbHelper.getAllStudents();

//                ArrayAdapter arrayAdapter=new ArrayAdapter(
//                        ViewStudentsActivity.this, android.R.layout.simple_list_item_1,list);

        MyCustomAdapter arrayAdapter = new MyCustomAdapter
                (ViewStudentsActivity.this,0,list);
        lv.setAdapter(arrayAdapter);
        Log.d("get also ok"," run");
    }
}