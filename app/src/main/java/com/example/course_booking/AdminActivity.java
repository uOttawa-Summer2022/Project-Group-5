package com.example.course_booking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity{
    TextView welcomeAdmin;
    DBHelper db;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        welcomeAdmin = (TextView)findViewById(R.id.welcomeAdmin);
        db = new DBHelper(this);
        UserModel user = MainActivity.currentUser;
        String msg = "Welcome " +user.getName()+"! You are logged in as admin.";
        Log.d("MSG", msg);
        Log.d("name", user.getName());
        welcomeAdmin.setText(msg);

    }
}

