package com.example.course_booking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class InstructorActivity extends AppCompatActivity{
    TextView welcomeInstructor;
    DBHelper db;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor);
        welcomeInstructor = (TextView)findViewById(R.id.welcomeInstructor);
        db = new DBHelper(this);
        UserModel user = MainActivity.currentUser;
        String msg = "Welcome " +user.getName()+"! You are logged in as instructor.";
        Log.d("MSG", msg);
        Log.d("name", user.getName());
        welcomeInstructor.setText(msg);

    }
}
