package com.gama.student_registration_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectionSQLiteHelper connection = new ConnectionSQLiteHelper(this, "db_students", null, 1);
    }

    public void onclick(View view) {
        if (view.getId() == R.id.btnGoToDashboard) {
            // this is using for navigate between activities.
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            // start our intent that go to DashboardActivity
            startActivity(intent);
        }
    }
}
