package com.example.course_booking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper_course extends SQLiteOpenHelper {

    public static final String DBNAME = "courses.db";
    private static final String TABLE_NAME2 = "courses";
    private static final String COLUMN_CODE = "crsCode";
    private static final String COLUMN_NAME = "crsName";
    public DBHelper_course(Context context){
        super(context, DBNAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //Create Table
            String create_table_cmd = "CREATE TABLE " + TABLE_NAME2 +
                    "(" + COLUMN_CODE + " TEXT PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT" + ")";
            MyDB.execSQL(create_table_cmd);

        ContentValues values = new ContentValues();
        values.put(COLUMN_CODE,"BIO1101");
        values.put(COLUMN_NAME,"Biology 1");
        MyDB.insert(TABLE_NAME2, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean insertCourse(CourseModel course){
        SQLiteDatabase myDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CODE,course.getCrsCode());
        contentValues.put(COLUMN_NAME,course.getCrsName());
        long result = myDB.insert(TABLE_NAME2,null,contentValues);
        return result != -1;
    }
    public boolean checkCourse(String code){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from courses where crsCode = ?",new String[]{code});
        return cursor.getCount() > 0;
    }

    public boolean deleteCourse(String code){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_NAME2, "crsCode=?", new String[]{code});
        if (rows == 0){
            return false;
        }
        db.close();
        return result;
    }

    public boolean editCourse(String codeOld,String nameOld,String codeNew,String nameNew){
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_NAME2, "crsCode=?", new String[]{codeOld});
        if (rows == 0){
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("crsCode", codeNew);
        contentValues.put("crsName", nameNew);
        db.insert(TABLE_NAME2,null,contentValues);
        return true;
    }
}
