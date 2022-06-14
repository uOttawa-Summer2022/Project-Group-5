package com.example.course_booking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity{
    TextView welcomeAdmin;
    DBHelper db;
    Button createCourse,editCourse,deleteCourse,deleteAccount;
    EditText crsCode,crsName,accName;

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

        createCourse = findViewById(R.id.btn_createCourse);
        editCourse = findViewById(R.id.btn_editCourse);
        deleteCourse = findViewById(R.id.btn_deleteCourse);
        deleteAccount = findViewById(R.id.btn_deleteAccount);
        crsCode = findViewById(R.id.crsCodeForm);
        crsName = findViewById(R.id.crsNameForm);
        accName = findViewById(R.id.accNameForm);


        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}

