package com.example.course_booking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InstructorActivity extends AppCompatActivity{
    EditText crsCode,crsName;
    ListView crsListView;
    TextView welcomeInstructor;
    DBHelper db;
    DBHelper_course db_course;
    Button btnLogout,btnViewALL,btnSearch;

    ArrayList<String> crsList;
    ArrayAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor);

        //edittext
        crsCode = findViewById(R.id.et_crsCode);
        crsName = findViewById(R.id.et_crsName);

        //buttons
        btnLogout = findViewById(R.id.btnLogoutInstructor);
        btnViewALL = findViewById(R.id.btn_viewAll);
        btnSearch = findViewById(R.id.btn_search);

        //listview
        crsListView = findViewById(R.id.productListView);

        //db handler
        db_course = new DBHelper_course(InstructorActivity.this);

        //welcome
        welcomeInstructor = (TextView)findViewById(R.id.welcomeInstructor);
        db = new DBHelper(this);
        UserModel user = MainActivity.currentUser;
        String msg = "Welcome " +user.getName()+"! You are logged in as instructor.";
        Log.d("MSG", msg);
        Log.d("name", user.getName());
        welcomeInstructor.setText(msg);


        //button listeners
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.currentUser = null;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        btnViewALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCourses(crsList);
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crsList.clear();
                if(crsCode.getText().toString().equals("") && !crsName.getText().toString().equals("")){
                    crsList = db_course.searchCourse1(crsName.getText().toString());
                } else if (!crsCode.getText().toString().equals("") && crsName.getText().toString().equals("")){
                    crsList = db_course.searchCourse2(crsCode.getText().toString());
                } else if (!crsCode.getText().toString().equals("") && !crsName.getText().toString().equals("")){
                    crsList = db_course.searchCourse3(crsCode.getText().toString(),crsName.getText().toString());
                }
                viewCourses(crsList);

            }
        });


    }
    private void viewCourses(ArrayList<String> list) {
        adapter = new ArrayAdapter<>(InstructorActivity.this, android.R.layout.simple_list_item_1, list);
        crsListView.setAdapter(adapter);
    }
}
