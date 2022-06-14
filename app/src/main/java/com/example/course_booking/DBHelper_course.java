package com.example.course_booking;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_course extends SQLiteOpenHelper {

    public static final String DBNAME = "Course.db";
    private static final String TABLE_NAME = "courses";
    private static final String COLUMN_CODE = "crsCode";
    private static final String COLUMN_NAME = "crsName";
    public DBHelper_course(Context context){
        super(context, "Course.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL("create Table TABLE_NAME(COLUMN_CODE TEXT primary key, COLUMN_NAME TEXT, COLUMN_NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists courses");
    }

    public void insertCourse(String code, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("crsCode", code);
        contentValues.put("crsName", name);
        db.insert("courses",null,contentValues);
    }

    public void deleteCourse(String code){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("courses",code,null);
    }

    public void editCourse(String codeOld,String nameOld,String codeNew,String nameNew){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("courses",codeOld,null);
        ContentValues contentValues = new ContentValues();
        contentValues.put("crsCode", codeNew);
        contentValues.put("crsName", nameNew);
        db.insert("courses",null,contentValues);
    }
}
