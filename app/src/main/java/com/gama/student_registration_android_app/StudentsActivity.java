package com.gama.student_registration_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentsActivity extends AppCompatActivity {

    ListView listViewStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        // mapping
        listViewStudents = findViewById(R.id.listViewStudents);

        // create an arrayList of students
        ArrayList<String> students = new ArrayList<String>();

        students.add("Gama");
        students.add("Fer");
        students.add("Juan");
        students.add("Alice");

        // adapter
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, students);

        // load adapter
        listViewStudents.setAdapter(adapter);
    }

    public void onclick(View view) {
        //
        Intent intent = null;

        switch(view.getId()) {
            case R.id.btnGoToStudentForm:
                intent = new Intent(StudentsActivity.this, StudentFormActivity.class);
                break;
            case R.id.btnShowStudent:
                intent = new Intent(StudentsActivity.this, ShowStudentActivity.class);
                break;
            case R.id.btnFindStudentWithSpinner:
                intent = new Intent(StudentsActivity.this, FindStudentSpinnerActivity.class);
                break;
        }

        startActivity(intent);
    }
}
