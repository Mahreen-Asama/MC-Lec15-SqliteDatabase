package com.example.lec15_sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class UpdateActivity extends AppCompatActivity {
    Button update;
    EditText name,roll;
    Switch enroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update=findViewById(R.id.uabtn);
        name=findViewById(R.id.uan);
        roll=findViewById(R.id.uarn);
        enroll=findViewById(R.id.uas);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                int rol=Integer.parseInt(roll.getText().toString());
                boolean enrol=enroll.isChecked();

                DBHelper helper=new DBHelper(UpdateActivity.this);
                helper.updateStudent(1,n,rol,enrol);
            }
        });

    }
}