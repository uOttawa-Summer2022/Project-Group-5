package com.example.course_booking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity{
    EditText crsCode,crsName, day;
    ListView crsListView;
    TextView welcomeStudent;
    DBHelper db;
    DBHelper_course db_course;
    Button btnLogout, btnViewAll, btnSearch, btnEnroll, btnUnenroll;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        //EditText
        crsCode = findViewById(R.id.et_crsCode2);
        crsName = findViewById(R.id.et_crsName2);
        day = findViewById(R.id.et_day);

        //Buttons
        btnLogout = findViewById(R.id.btnLogoutStudent);
        btnViewAll = findViewById(R.id.btn_viewAll2);
        btnSearch = findViewById(R.id.btn_search2);
        btnEnroll = findViewById(R.id.btn_enroll);
        btnUnenroll = findViewById(R.id.btn_unenroll);

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
