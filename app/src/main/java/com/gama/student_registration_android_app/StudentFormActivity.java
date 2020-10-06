package com.gama.student_registration_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gama.student_registration_android_app.utilities.Utilities;

public class StudentFormActivity extends AppCompatActivity {

    EditText userIdInput, userNameInput, userTelephoneInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        // mapping
        userIdInput = findViewById(R.id.userIdInput);
        userNameInput = findViewById(R.id.studentNameInput);
        userTelephoneInput = findViewById(R.id.studentTelephoneInput);
    }

    public void onclick(View view) {
        // addStudent();

        addStudentUsingSql();
    }

    private void addStudentUsingSql() {
        ConnectionSQLiteHelper connection = new ConnectionSQLiteHelper(this, "db_students", null, 1);

        // we open the data base for can be edit.
        SQLiteDatabase db = connection.getWritableDatabase();

        // INTERT INTO students (id, name, telephone) values (1, "gama", "555555")
        String insert = "INSERT INTO "+Utilities.STUDENT_TABLE+" ( "+Utilities.FIELD_ID+","+Utilities.FIELD_NAME+","+Utilities.FIELD_TELEPHONE+") VALUES ("+userIdInput.getText().toString()+", '"+userNameInput.getText().toString()+"','"+userTelephoneInput.getText().toString()+"')";

        // execute the query sql
        db.execSQL(insert);

        Toast.makeText(getApplicationContext(), "Student added", Toast.LENGTH_LONG).show();
        // close the database
        db.close();
    }

    // this method is using contentValues to save the data in the data base
    private void addStudent() {
        ConnectionSQLiteHelper connection = new ConnectionSQLiteHelper(this, "db_students", null, 1);

        // we open the data base for can be edit.
        SQLiteDatabase db = connection.getWritableDatabase();

        // using content values, is lika a hashmap.
        ContentValues values = new ContentValues();
        values.put(Utilities.FIELD_ID, userIdInput.getText().toString());
        values.put(Utilities.FIELD_NAME, userNameInput.getText().toString());
        values.put(Utilities.FIELD_TELEPHONE, userTelephoneInput.getText().toString());

        // insert the data in our data base using the method insert of SQLiteDatabase
        Long idResult = db.insert(Utilities.STUDENT_TABLE, Utilities.FIELD_ID, values);

        Toast.makeText(getApplicationContext(), "id: "+idResult, Toast.LENGTH_SHORT).show();

        // close the database
        db.close();
    }
}
