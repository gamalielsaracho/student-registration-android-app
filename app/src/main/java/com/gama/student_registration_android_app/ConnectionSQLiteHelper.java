package com.gama.student_registration_android_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.gama.student_registration_android_app.utilities.Utilities;

public class ConnectionSQLiteHelper extends SQLiteOpenHelper {



    // When we call this constructor then it will create the database
    public ConnectionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // the method onCreate is using to generate and create the tables of our entities
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilities.CREATE_TABLE_STUDENT);
    }

    // update the tables of database
    // when we execute or install our application, this verify if the data base already exist a old version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // delete table students
        db.execSQL("DROP TABLE IF EXISTS students");
        // create one more time the students table
        onCreate(db);
    }
}
