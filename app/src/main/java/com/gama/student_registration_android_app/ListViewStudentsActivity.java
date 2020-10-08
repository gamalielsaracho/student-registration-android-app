package com.gama.student_registration_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gama.student_registration_android_app.entities.Student;
import com.gama.student_registration_android_app.utilities.Utilities;

import java.util.ArrayList;

public class ListViewStudentsActivity extends AppCompatActivity {

    ListView listViewStudents;
    // create an arrayList of students
    ArrayList<Student> studentsList;
    //
    ArrayList<String> studentsListShowInfo;

    ConnectionSQLiteHelper connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_students);

        // connection with data of data base
        connection = new ConnectionSQLiteHelper(getApplicationContext(), "db_students", null, 1);

        // mapping
        listViewStudents = findViewById(R.id.listViewStudents);

        // get data form data base
        getStudentsListFromDb();

        // adapter
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, studentsListShowInfo);

        // load adapter or populate
        listViewStudents.setAdapter(adapter);

        // Onclick in item of listView
        listViewStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*String studentData = "Id: "+studentsList.get(position).getId().toString()+"\n";
                studentData += "Name: "+ studentsList.get(position).getName()+"\n";
                studentData += "TelePhone number: "+ studentsList.get(position).getTelephone();

                Toast.makeText(getApplicationContext(), studentData, Toast.LENGTH_LONG).show();*/

                // the student object to send in other activity
                Student student = studentsList.get(position);

                // create a intent to go to StudentDetailActivity
                Intent intent = new Intent(ListViewStudentsActivity.this, StudentDetailActivity.class);

                // create a object bundle to send our student object
                Bundle bundle = new Bundle();
                bundle.putSerializable("student", student);

                // add the bundle created in our intent
                intent.putExtras(bundle);

                // calls the intent
                startActivity(intent);
            }
        });

    }

    private void getStudentsListFromDb() {
        // open connection
        SQLiteDatabase db = connection.getWritableDatabase();

        Student student = null;
        studentsList = new ArrayList<Student>();

        // select * form students
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilities.STUDENT_TABLE, null);

        while(cursor.moveToNext()) {
            student = new Student();
            student.setId(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setTelephone(cursor.getString(2));

            studentsList.add(student);
        }

        getStudentsListAndPopulateListView();
    }

    // this method is used to populate the studentsListShowInfo ArrayList that will be used to populate de ListViewStudents view
    private void getStudentsListAndPopulateListView() {
        studentsListShowInfo = new ArrayList<String>();

        for(int i = 0; i < studentsList.size(); i++) {
            studentsListShowInfo.add(studentsList.get(i).getId().toString()+"  "+ studentsList.get(i).getName());
        }
    }
}
