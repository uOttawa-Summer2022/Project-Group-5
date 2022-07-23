package com.example.course_booking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

public class StudentActivity extends AppCompatActivity{
    EditText crsCode,crsName, day;
    ListView crsListView;
    TextView welcomeStudent;
    DBHelper db;
    DBHelper_course db_course;
    Button btnLogout, btnViewAll, btnSearch, btnEnroll, btnUnenroll, btnViewEnrolled;

    ArrayList<String> crsList;
    ArrayAdapter adapter;

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
        btnViewEnrolled = findViewById(R.id.btn_viewEnrolled);

        //list
        crsList= new ArrayList<>();

        //listview
        crsListView = findViewById(R.id.productListView);

        //db handler
        db_course = new DBHelper_course(StudentActivity.this);

        //Welcome
        welcomeStudent = (TextView)findViewById(R.id.welcomeStudent);
        db = new DBHelper(this);
        UserModel user = MainActivity.currentUser;
        String msg = "Welcome " +user.getName()+"! You are logged in as student.";
        Log.d("MSG", msg);
        Log.d("name", user.getName());
        welcomeStudent.setText(msg);


        //button Listeners
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.currentUser = null;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCourses();
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
                } else if (!day.getText().toString().equals("")) {
                    crsList = db_course.searchCourse4(day.getText().toString());
                } else {
                    viewCourses();
                    return;
                }
                viewCourses(crsList);

            }
        });

        btnEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crsCode.getText().toString().equals("")){
                    Toast.makeText(StudentActivity.this, "Specify the crsCode", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = crsCode.getText().toString();
                if(!db_course.checkCourse(code)){
                    Toast.makeText(StudentActivity.this, "Course does not exist",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(db_course.checkCrsCapacity(code)){
                    //Add in student
                    if(db_course.addStudent(code, MainActivity.currentUser.getName())){
                        Toast.makeText(StudentActivity.this, "Course Registration successful",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(StudentActivity.this, "Course Registration Failed",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Toast that course is full
                    Toast.makeText(StudentActivity.this, "This course is full",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUnenroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crsCode.getText().toString().equals("")){
                    Toast.makeText(StudentActivity.this, "Specify the crsCode", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = crsCode.getText().toString();
                if(!db_course.checkCourse(code)){
                    Toast.makeText(StudentActivity.this, "Course does not exist",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if(db_course.deleteStudent(code, MainActivity.currentUser.getName())){
                        Toast.makeText(StudentActivity.this, "Course Unenrollment successful",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(StudentActivity.this, "Course Unenrollment Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnViewEnrolled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crsList.clear();
                crsList = db_course.searchEnrolled(MainActivity.currentUser.getName());
                viewCourses(crsList);
            }
        });
    }

    private void viewCourses(ArrayList<String> list) {
        adapter = new ArrayAdapter<>(StudentActivity.this, android.R.layout.simple_list_item_1, list);
        crsListView.setAdapter(adapter);
    }

    private void viewCourses() {
        crsList.clear();
        Cursor cursor = db_course.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(StudentActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                crsList.add(cursor.getString(0) + " - " +cursor.getString(1));
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, crsList);
        crsListView.setAdapter(adapter);
    }

}
