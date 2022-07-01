package com.example.course_booking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.util.Strings;

import java.util.ArrayList;

public class DBHelper_course extends SQLiteOpenHelper {

    public static final String DBNAME = "courses.db";
    private static final String TABLE_NAME2 = "courses";
    private static final String COLUMN_CODE = "crsCode";
    private static final String COLUMN_NAME = "crsName";
    private static final String COLUMN_DESCRIPTION = "crsDescription";
    private static final String COLUMN_CAPACITY = "crsCapacity";
    private static final String COLUMN_INSTRUCTOR = "crsInstructor";
    private static final String COLUMN_SESSION_LIST = "crsSessionList";
    public DBHelper_course(Context context){
        super(context, DBNAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //Create Table
            String create_table_cmd = "CREATE TABLE " + TABLE_NAME2 +
                    "(" + COLUMN_CODE + " TEXT PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_CAPACITY + " INT," +
                    COLUMN_INSTRUCTOR + " TEXT," +
                    COLUMN_SESSION_LIST + " TEXT" +")";
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

    public boolean checkCrsInstructor(String code){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from courses where crsInstructor is not null and crsCode = ?",new String[]{code});
        return cursor.getCount() > 0;
    }

    public boolean checkCrsSessionList(String code){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from courses where crsSessionList is not null and crsCode = ?",new String[]{code});
        return cursor.getCount() > 0;
    }

    public boolean checkCrsSession(String[] sessionList, Session newSession){
        for(String session: sessionList){
            String[] singleSess = session.split(",");
            String[] separateSessParts;
            for(String sessionSegment: singleSess){
                separateSessParts = sessionSegment.split(";");
                Day oldDay = null;
                switch (separateSessParts[0]){
                    case "MONDAY":
                        oldDay = Day.MONDAY;
                        break;
                    case "TUESDAY":
                        oldDay = Day.TUESDAY;
                        break;
                    case "WEDNESDAY":
                        oldDay = Day.WEDNESDAY;
                        break;
                    case "THURSDAY":
                        oldDay = Day.THURSDAY;
                        break;
                    case "FRIDAY":
                        oldDay = Day.FRIDAY;
                }
                Session oldSession = new Session(oldDay, Integer.parseInt(separateSessParts[1]),
                        Integer.parseInt(separateSessParts[2]), Integer.parseInt(separateSessParts[3]),
                        Integer.parseInt(separateSessParts[4]));
                if(oldSession.getDay().equals(newSession.getDay())){
                    if(oldSession.getStartHour() == newSession.getStartHour()){return false;}
                    if(oldSession.getEndHour() == newSession.getEndHour()){return false;}
                    if(oldSession.getStartHour() == newSession.getEndHour()){
                        if(oldSession.getStartMinute() <= newSession.getEndMinute()){return false;}
                    }
                    if(newSession.getStartHour() == oldSession.getEndHour()){
                        if(newSession.getStartMinute() <= oldSession.getEndMinute()){return false;}
                    }
                    if(newSession.getStartHour() >= oldSession.getStartHour() &&
                            oldSession.getEndHour() >= newSession.getStartHour()){return false;}
                    if(newSession.getEndHour() >= oldSession.getStartHour() &&
                            oldSession.getEndHour() >= newSession.getEndHour()){return false;}
                }
            }

        }
        return true;
    }

    public boolean deleteCourse(String code){
        boolean result = true;
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_NAME2, "crsCode=?", new String[]{code});
        Log.d("course code", code);
        if (rows == 0){
            result = false;
        }
        db.close();
        return result;
    }

    public boolean editCourse(String codeOld,String nameOld,String codeNew,String nameNew){
        SQLiteDatabase db = this.getWritableDatabase();

        if(!checkCourse(codeOld)){
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("crsCode", codeNew);
        contentValues.put("crsName", nameNew);
        db.update(TABLE_NAME2, contentValues, "crsCode=?", new String[]{codeOld});
        return true;
    }

    public boolean editCrsDescription(String crsCode,String newDescritpion){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("crsDescription", newDescritpion);
        db.update(TABLE_NAME2, contentValues, "crsCode=?", new String[]{crsCode});
        return true;
    }

    public boolean editCrsCapacity(String crsCode, int crsCapacity){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("crsCapacity", crsCapacity);
        db.update(TABLE_NAME2, contentValues, "crsCode=?", new String[]{crsCode});
        return true;
    }

    public boolean addCrsSession(String crsCode, Session newSession){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from courses where crsCode = ?",new String[]{crsCode});
        String sessionListSTR;
        ContentValues contentValues = new ContentValues();
        if(cursor.isNull(5)){
            contentValues.put("crsSessionList", newSession.toString());
        } else{
            sessionListSTR= cursor.getString(5);
            String[] sessionList = cursor.getString(5).split(",");
            if(checkCrsSession(sessionList, newSession)){
                sessionListSTR += ";"+newSession.toString();
                contentValues.put("crsSessionList", sessionListSTR);
            }else{return false;}
        }
        db.update(TABLE_NAME2, contentValues, "crsCode=?", new String[]{crsCode});
        return true;
    }
    public boolean deleteCrsSession(String crsCode, Session targetSession){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from courses where crsCode = ?",new String[]{crsCode});

        ContentValues contentValues = new ContentValues();
        boolean sessionExists = false;
        String newSessionList = "";
        if(cursor.isNull(5)){
            return false;
        } else{
            String[] sessionList = cursor.getString(5).split(",");
            for(String session: sessionList){
                if(session.equals(targetSession.toString())){
                    sessionExists = true;
                }
                else{
                    if(newSessionList.equals("")){
                        newSessionList+=session.toString();
                    }else {
                        newSessionList += "," + session.toString();
                    }
                }
            }
            if(!sessionExists){
                return false;
            }
        }
        if(newSessionList.equals("")){
            contentValues.putNull("crsSessionList");
        }else{
            contentValues.put("crsSessionList", newSessionList);
        }
        db.update(TABLE_NAME2, contentValues, "crsCode=?", new String[]{crsCode});
        return true;
    }

    public boolean editCrsSession(String crsCode, Session oldSession, Session newSession){
        if(deleteCrsSession(crsCode, oldSession)){
            return addCrsSession(crsCode, newSession);
        }
        return false;
    }

    public boolean editCrsInstructor(String crsCode, String crsInstructor, boolean delete){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        if(delete){
            contentValues.putNull("crsInstructor");
            contentValues.putNull("crsCapacity");
            contentValues.putNull("crsDescription");
            contentValues.putNull("crsSessionList");
        } else {
            contentValues.put("crsInstructor", crsInstructor);
        }
        db.update(TABLE_NAME2, contentValues, "crsCode=?", new String[]{crsCode});
        return true;
    }



}
