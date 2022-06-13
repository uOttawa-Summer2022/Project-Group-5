package com.example.course_booking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity{
    TextView welcomeStudent;
    DBHelper db;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
        welcomeStudent = (TextView)findViewById(R.id.welcomeStudent);
        db = new DBHelper(this);
        UserModel user = MainActivity.currentUser;
        String msg = "Welcome " +user.getName()+"! You are logged in as student.";
        Log.d("MSG", msg);
        Log.d("name", user.getName());
        welcomeStudent.setText(msg);

    }
}
