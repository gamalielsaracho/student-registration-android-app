package com.gama.student_registration_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void onclick(View view) {
        if(view.getId() == R.id.btnGoToStudents) {
            Intent intent = new Intent(DashboardActivity.this, StudentsActivity.class);
            startActivity(intent);
        }
    }
}
