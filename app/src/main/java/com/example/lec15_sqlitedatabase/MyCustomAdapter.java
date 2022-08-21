package com.example.lec15_sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyCustomAdapter<S> extends ArrayAdapter<StudentModel> {
    public MyCustomAdapter(@NonNull Context context, int resource, @NonNull List<StudentModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        StudentModel student=getItem(position);

        convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_listview,parent,false);

        //TextView id=convertView.findViewById(R.id.s_id);
        TextView name=convertView.findViewById(R.id.s_name);
        TextView rollnumber=convertView.findViewById(R.id.s_rollnumber);
        TextView isenroll=convertView.findViewById(R.id.s_enroll);

        //id.setText(student.getId());
        name.setText(student.getName());
        rollnumber.setText(student.getRollNmber());
        boolean enrol=student.isEnroll();
        if(enrol){
            isenroll.setText("yess enroll");
        }
        else{
            isenroll.setText("not enroll");
        }

        //set click listener on 3 buttons
        Button updateButton=convertView.findViewById(R.id.update);
        Button deleteButton=convertView.findViewById(R.id.delete);
        Button viewButton=convertView.findViewById(R.id.view);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(null);
                //db.updateStudent(student.getId(),);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(null);
                db.deleteStudent(student.getID());
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
