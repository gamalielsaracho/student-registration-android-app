package com.gama.student_registration_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.gama.student_registration_android_app.utilities.Utilities;

public class ShowStudentActivity extends AppCompatActivity {

    EditText studentIdSearchInput, studentNameInput, studentTelephoneInput;
    // connection
    ConnectionSQLiteHelper connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);

        // connection
        connection = new ConnectionSQLiteHelper(getApplicationContext(), "db_students", null, 1);

        // mapping
        studentIdSearchInput = findViewById(R.id.studentIdSearchInput);
        studentNameInput = findViewById(R.id.studentNameInput);
        studentTelephoneInput = findViewById(R.id.studentTelephoneInput);


    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchStudent:
                searchStudentUsingSql();
                break;
            case R.id.btnUpdateStudent:
                updateStudent();
                break;
            case R.id.btnDeleteStudent:
                deleteStudent();
                break;
        }
    }

    private void deleteStudent() {
        SQLiteDatabase db = connection.getWritableDatabase();
        String[] params = { studentIdSearchInput.getText().toString() };

        db.delete(Utilities.STUDENT_TABLE, Utilities.FIELD_ID+"=?", params);

        Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();

        // clearing the student form
        clearForm();

        db.close();
    }

    private void updateStudent() {
        // open de connection of data base to execute sql queries.
        SQLiteDatabase db = connection.getWritableDatabase();
        // the params will be in the where of sql
        String[] params = {studentIdSearchInput.getText().toString()};

        // new object of ContentValues
        ContentValues values = new ContentValues();
        // insert into value object
        values.put(Utilities.FIELD_NAME, studentNameInput.getText().toString());
        values.put(Utilities.FIELD_TELEPHONE, studentTelephoneInput.getText().toString());

        // Calling the update method passing the table name, the values, the where Sql Field, the params used in the where
        db.update(Utilities.STUDENT_TABLE, values, Utilities.FIELD_ID+"=?", params);

        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();

        db.close();
    }

    private void searchStudentUsingSql() {
        // open de connection of data base to execute sql queries.
        SQLiteDatabase db = connection.getWritableDatabase();
        // the params will be in the where of sql
        String[] params = {studentIdSearchInput.getText().toString()};

        try {
            // select name, telephone from student where id = ?
            Cursor cursor = db.rawQuery("SELECT "+Utilities.FIELD_NAME+","+Utilities.FIELD_TELEPHONE+
                    " FROM "+Utilities.STUDENT_TABLE+" WHERE "+Utilities.FIELD_ID+"=? ", params);

            // the moveToFirst method get the first data (get only one data and no more)
            cursor.moveToFirst();

            // show the data returned in the edit text views
            studentNameInput.setText(cursor.getString(0));
            studentTelephoneInput.setText(cursor.getString(1));
            cursor.close();

        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "The data don't exist", Toast.LENGTH_SHORT).show();
            clearForm();
        }
    }

    private void searchStudent() {
        // open de connection of data base to execute sql queries.
        SQLiteDatabase db = connection.getWritableDatabase();

        // the params will be in the where of sql
        String[] params = {studentIdSearchInput.getText().toString()};
        // this will be in the select of sql query, (the fields are that we want return in sql)
        String[] fields = {Utilities.FIELD_NAME, Utilities.FIELD_TELEPHONE};

        try {
            // the 3 null params are related to the groupBy, Having, and Orderby
            Cursor cursor = db.query(Utilities.STUDENT_TABLE, fields, Utilities.FIELD_ID+"=?", params, null, null, null);
            // the moveToFirst method get the first data (get only one data and no more)
            cursor.moveToFirst();

            // show the data returned in the edit text views
            studentNameInput.setText(cursor.getString(0));
            studentTelephoneInput.setText(cursor.getString(1));
            cursor.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "The data don't exist", Toast.LENGTH_SHORT).show();
            clearForm();
        }


    }

    private void clearForm() {
        studentIdSearchInput.setText("");
        studentNameInput.setText("");
        studentTelephoneInput.setText("");
    }
}
