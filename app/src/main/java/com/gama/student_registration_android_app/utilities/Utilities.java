package com.gama.student_registration_android_app.utilities;

public class Utilities {

    // const fields students table
    public static final String STUDENT_TABLE = "students";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_TELEPHONE = "telephone";


    // script to create the students table in the data base
    public static final String CREATE_TABLE_STUDENT = "CREATE TABLE "+
            STUDENT_TABLE +"("+FIELD_ID+" INTEGER,"+FIELD_NAME+" TEXT,"+FIELD_TELEPHONE+" TEXT)";
}