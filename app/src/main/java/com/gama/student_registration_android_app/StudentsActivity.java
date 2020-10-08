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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
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
            case R.id.goToStudentsListView:
                intent = new Intent(StudentsActivity.this, ListViewStudentsActivity.class);
                break;
        }

        startActivity(intent);
    }
}
