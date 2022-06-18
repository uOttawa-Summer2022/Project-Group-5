package com.example.course_booking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity{
    TextView welcomeStudent;
    DBHelper db;
    Button btnLogout;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        btnLogout = findViewById(R.id.btnLogoutStudent);

        welcomeStudent = (TextView)findViewById(R.id.welcomeStudent);
        db = new DBHelper(this);
        UserModel user = MainActivity.currentUser;
        String msg = "Welcome " +user.getName()+"! You are logged in as student.";
        Log.d("MSG", msg);
        Log.d("name", user.getName());
        welcomeStudent.setText(msg);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.currentUser = null;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
