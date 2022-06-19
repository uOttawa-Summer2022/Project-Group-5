package com.example.course_booking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USERNAME = "name";
    private static final String COLUMN_TYPE = "accType";

    public DBHelper(Context context){
        super(context, DBNAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //Create Table
        MyDB.execSQL("CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_USERNAME + " VARCHAR(20) PRIMARY KEY, " +
                COLUMN_PASSWORD + " VARCHAR(20)," +
                COLUMN_TYPE + " VARCHAR(20))");

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,"admin");
        values.put(COLUMN_PASSWORD,"admin");
        values.put(COLUMN_TYPE,"ADMIN");
        MyDB.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop table if exists users");

    }

    public boolean insertData(String name, String password, Type accType){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("accType", String.valueOf(accType));
        long result = myDB.insert(TABLE_NAME,null,contentValues);
        return result != -1;
    }

    public boolean deleteData(String username){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = \""
                + username + "\"";
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

    public boolean checkusername(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where name = ?",new String[]{name});
        return cursor.getCount() > 0;
    }

    public UserModel findUser(String name){
        UserModel target = new UserModel();
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where name = ?",new String[]{name});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            target.setName(name);
            target.setPassword(cursor.getString(1));
            String accTypeSTR = cursor.getString(2);
            Type accType = null;
            if (accTypeSTR.equals("STUDENT")){
                accType = Type.STUDENT;
            } else if (accTypeSTR.equals("INSTRUCTOR")){
                accType = Type.INSTRUCTOR;
            } else {
                accType = Type.ADMIN;
            }
            target.setAccType(accType);
            return target;

        }
        return null;
    }

    public boolean checkpassword(String name, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where name = ? and password = ?",new String[]{name,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}
