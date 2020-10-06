package com.gama.student_registration_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.gama.student_registration_android_app.entities.Student;
import com.gama.student_registration_android_app.utilities.Utilities;

import java.util.ArrayList;

public class FindStudentSpinnerActivity extends AppCompatActivity {

    Spinner spinnerStudents;
    TextView showStudentName, showStudentTelephone;

    // Creating an arrayList to pupulate the spinner students
    ArrayList<String> studentsList;
    ArrayList<Student> studentsListEnti;

    ConnectionSQLiteHelper connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_student_spinner);

        // connection
        connection = new ConnectionSQLiteHelper(getApplicationContext(), "db_students", null, 1);

        // mapping
        spinnerStudents = findViewById(R.id.spinnerStudents);
        showStudentName = findViewById(R.id.showStudentName);
        showStudentTelephone = findViewById(R.id.showStudentTelephone);

        // this method is used to call students from data base
        findStudentsList();

        // create an adapter to get the data form the studentsList
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, studentsList);

        // set the adapter of spinnerStudent view.
        spinnerStudents.setAdapter(adapter);
    }

    private void findStudentsList() {
        // open the db connection
        SQLiteDatabase db = connection.getWritableDatabase();

        // new New Object of Student
        Student student = null;

        // define a new Array List of Students
        studentsListEnti = new ArrayList<Student>();

        // select * from students
        Cursor cursor = db.rawQuery("select * from "+ Utilities.STUDENT_TABLE, null);

        while (cursor.moveToNext()) {
            // new instance of student is used to populate the studentsListEnti
            student = new Student();

            // populate
            student.setId(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setTelephone(cursor.getString(2));

            studentsListEnti.add(student);
        }

        getStudentList();
    }

    private void getStudentList() {
        studentsList = new ArrayList<String>();

        studentsList.add("Select a student");

        for (int i = 0; i < studentsListEnti.size(); i++) {
            studentsList.add(studentsListEnti.get(i).getId()+"  "+studentsListEnti.get(i).getName());
        }
    }
}
