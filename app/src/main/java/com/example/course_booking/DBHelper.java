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
        super(context, "Login.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table TABLE_NAME(COLUMN_USERNAME TEXT primary key, COLUMN_PASSWORD TEXT, COLUMN_TYPE TEXT)");

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
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean checkusername(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where name = ?",new String[]{name});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
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
