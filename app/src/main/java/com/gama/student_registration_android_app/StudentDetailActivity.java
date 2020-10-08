package com.gama.student_registration_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.gama.student_registration_android_app.entities.Student;

public class StudentDetailActivity extends AppCompatActivity {

    TextView showStudentId, showStudentName, showStudentTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        // mapping
        showStudentId = findViewById(R.id.showStudentId);
        showStudentName = findViewById(R.id.showStudentName);
        showStudentTelephone = findViewById(R.id.showStudentTelephone);

        // create a bundle instance to get student object as parameter sends from FindStudentSpinnerActivity
        Bundle sendedObject = getIntent().getExtras();

        Student student = null;

        if (sendedObject != null) {
            student = (Student) sendedObject.getSerializable("student");

            // set the text of view
            showStudentId.setText(student.getId().toString());
            showStudentName.setText(student.getName());
            showStudentTelephone.setText(student.getTelephone());
        }
    }
}
