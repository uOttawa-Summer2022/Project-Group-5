package com.example.course_booking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public void onCreate(SQLiteDatabase db) {db.execSQL("create Table TABLE_NAME(COLUMN_CODE TEXT primary key, COLUMN_NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists courses");
    }

    public void insertCourse(CourseModel course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CODE,course.getCrsCode());
        contentValues.put(COLUMN_NAME,course.getCrsName());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public boolean deleteCourse(String code){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("courses",code,null);
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CODE + " = \""
                + code + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            db.close();
            cursor.close();
            return false;
        }
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_NAME, "name=?", new String[]{idStr});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
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
