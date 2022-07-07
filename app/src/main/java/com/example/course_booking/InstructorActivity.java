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
import java.util.List;
import java.util.Objects;

public class InstructorActivity extends AppCompatActivity{
    EditText crsCode,crsName;
    ListView crsListView;
    TextView welcomeInstructor;
    DBHelper db;
    DBHelper_course db_course;
    Button btnLogout,btnViewALL,btnSearch, btnAssign, btnCheckAssign,btnModifyAssign;

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
        btnAssign = findViewById(R.id.btn_assign);
        btnCheckAssign = findViewById(R.id.btn_checkAssigned);
        btnModifyAssign = findViewById(R.id.btn_modify_assigned);

        //list
        crsList= new ArrayList<>();

        //listview
        crsListView = findViewById(R.id.productListView);

        //db handler
        db_course = new DBHelper_course(InstructorActivity.this);

        //welcome
        welcomeInstructor = (TextView)findViewById(R.id.welcomeInstructor);
        db = new DBHelper(this);
        UserModel user = MainActivity.currentUser;
        String msg = "Welcome " +user.getName()+"! You are logged in as instructor.";
        welcomeInstructor.setText(msg);


        //button listeners
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.currentUser = null;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnCheckAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crsCode.getText().toString().equals("")){
                    Toast.makeText(InstructorActivity.this, "Specify the crsCode", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = crsCode.getText().toString();
                if(!db_course.checkCourse(code)){
                    Toast.makeText(InstructorActivity.this, "Course does not exist",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(db_course.checkCrsInstructor(code)){
                    Toast.makeText(InstructorActivity.this, "This course has an instructor",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InstructorActivity.this, "This course does not have an instructor",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crsCode.getText().toString().equals("")){
                    Toast.makeText(InstructorActivity.this, "Specify the crsCode", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = crsCode.getText().toString();
                if(!db_course.checkCourse(code)){
                    Toast.makeText(InstructorActivity.this, "Course does not exist",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(db_course.checkCrsInstructor(code)){
                    Toast.makeText(InstructorActivity.this, "This course has an instructor",Toast.LENGTH_SHORT).show();
                } else {
                    if(db_course.editCrsInstructor(code, MainActivity.currentUser.getName(), false)){
                        Toast.makeText(InstructorActivity.this, "Course assignment succesful",Toast.LENGTH_SHORT).show();
                        MainActivity.currentCourse = db_course.getCourse(code);
                        startActivity(new Intent(getApplicationContext(), AddOrEditCourseDetails.class));
                    } else{
                        Toast.makeText(InstructorActivity.this, "Course assignment failed",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        btnModifyAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crsCode.getText().toString().equals("")){
                    Toast.makeText(InstructorActivity.this, "Specify the crsCode", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = crsCode.getText().toString();
                if(!db_course.checkCourse(code)){
                    Toast.makeText(InstructorActivity.this, "Course does not exist",Toast.LENGTH_SHORT).show();
                    return;
                }
                MainActivity.currentCourse = db_course.getCourse(code);
                if(!MainActivity.currentCourse.getcrsInstructor().equals(MainActivity.currentUser.getName())){
                    Toast.makeText(InstructorActivity.this, "You are not the instructor of this course",Toast.LENGTH_SHORT).show();
                    MainActivity.currentCourse = null;
                    return;
                }
                Log.d("currentCourse", MainActivity.currentCourse.toString());
                Log.d("test", "just making sure");
                startActivity(new Intent(getApplicationContext(), AddOrEditCourseDetails.class));

            }
        });


        btnViewALL.setOnClickListener(new View.OnClickListener() {
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
                } else {
                    viewCourses();
                    return;
                }
                viewCourses(crsList);

            }
        });


    }
    private void viewCourses(ArrayList<String> list) {
        adapter = new ArrayAdapter<>(InstructorActivity.this, android.R.layout.simple_list_item_1, list);
        crsListView.setAdapter(adapter);
    }

    private void viewCourses() {
        crsList.clear();
        Cursor cursor = db_course.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(InstructorActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                crsList.add(cursor.getString(0) + " - " +cursor.getString(1));
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, crsList);
        crsListView.setAdapter(adapter);
    }
}
