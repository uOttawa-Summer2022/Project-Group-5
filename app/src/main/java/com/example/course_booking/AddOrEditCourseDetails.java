package com.example.course_booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddOrEditCourseDetails extends AppCompatActivity {

    Button addDescriptionAndCapacity,editDescriptionAndCapacity,addSession,deleteSession;
    EditText crsDescription,crsCapacity,courseDay,startHour,startMinute,endHour,endMinute;
    TextView unAssign,goBack;
    DBHelper_course db_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_course_details);

        //EditTexts
        crsDescription = findViewById(R.id.crsDescription);
        crsCapacity = findViewById(R.id.crsCapacity);
        courseDay = findViewById(R.id.crsDay);
        startHour = findViewById(R.id.startHour);
        startMinute = findViewById(R.id.startMinute);
        endHour = findViewById(R.id.endHour);
        endMinute = findViewById(R.id.endMinute);

        //TextView
        unAssign = findViewById(R.id.tv_unAssign);
        goBack = findViewById(R.id.tv_backToInstructorActivity);


        //Buttons
        addDescriptionAndCapacity = findViewById(R.id.btn_addDesAndCap);
        editDescriptionAndCapacity = findViewById(R.id.btn_editDesAndCap);
        addSession = findViewById(R.id.btn_addSession);
        deleteSession = findViewById(R.id.btn_deleteSession);

        //DB
        db_course = new DBHelper_course(AddOrEditCourseDetails.this);


        addDescriptionAndCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseDescription = crsDescription.getText().toString();
                String courseCapacity = crsCapacity.getText().toString();

                if (courseCapacity.isEmpty() || courseDescription.isEmpty()){
                    Toast.makeText(AddOrEditCourseDetails.this, "Please fill all fields! ", Toast.LENGTH_SHORT).show();

                }else if (!(courseCapacity.matches("[0-9]+")&&(Integer.parseInt(courseCapacity)>0))){ //Check the input of capacity is a positive integer
                    Toast.makeText(AddOrEditCourseDetails.this,"Please enter a valid capacity(Integer)", Toast.LENGTH_SHORT).show();
                }else{

                    boolean addCap = db_course.editCrsCapacity(MainActivity.currentCourse.getCrsCode(),Integer.parseInt(courseCapacity));
                    boolean addDes = db_course.editCrsDescription(MainActivity.currentCourse.getCrsCode(),courseDescription);
                    if(addDes&&addCap){
                        Toast.makeText(AddOrEditCourseDetails.this,"Details of courseDescription and courseCapacity are added successfully!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddOrEditCourseDetails.this,"Details of courseDescription and courseCapacity are added failed!",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        editDescriptionAndCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseDescription = crsDescription.getText().toString();
                String courseCapacity = crsCapacity.getText().toString();

                if (courseCapacity.isEmpty() || courseDescription.isEmpty()){
                    Toast.makeText(AddOrEditCourseDetails.this, "Please fill all fields! ", Toast.LENGTH_SHORT).show();

                }else if (!(courseCapacity.matches("[0-9]+")&&(Integer.parseInt(courseCapacity)>0))){ //Check the input of capacity is a positive integer
                    Toast.makeText(AddOrEditCourseDetails.this,"Please enter a valid capacity(Integer)", Toast.LENGTH_SHORT).show();
                }else{

                    boolean addCap = db_course.editCrsCapacity(MainActivity.currentCourse.getCrsCode(),Integer.parseInt(courseCapacity));
                    boolean addDes = db_course.editCrsDescription(MainActivity.currentCourse.getCrsCode(),courseDescription);
                    if(addDes&&addCap){
                        Toast.makeText(AddOrEditCourseDetails.this,"Details of courseDescription and courseCapacity are edited successfully!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddOrEditCourseDetails.this,"Details of courseDescription and courseCapacity are edited failed!",Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        addSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = courseDay.getText().toString();
                String crsStartHour = startHour.getText().toString();
                String crsStartMinute = startMinute.getText().toString();
                String crsEndHour = endHour.getText().toString();
                String crsEndMinute = endMinute.getText().toString();


                if (day.isEmpty() || crsStartHour.isEmpty() || crsStartMinute.isEmpty() || crsEndHour.isEmpty() || crsEndMinute.isEmpty()) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please fill all fields! ", Toast.LENGTH_SHORT).show();

                } else if (!day.equals("Monday") && !day.equals("Tuesday") && !day.equals("Wednesday") && !day.equals("Thursday") && !day.equals("Friday")) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please enter a valid day! ", Toast.LENGTH_SHORT).show();
                } else if (!(crsStartHour.matches("[0-9]+") && (Integer.parseInt(crsStartHour) >= 0) && (Integer.parseInt(crsStartHour) < 24))) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please enter an Integer 0-23", Toast.LENGTH_SHORT).show();
                } else if (!(crsEndHour.matches("[0-9]+")) && (Integer.parseInt(crsEndHour) >= 0) && (Integer.parseInt(crsEndHour) < 24)) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please enter an Integer 0-23", Toast.LENGTH_SHORT).show();
                } else if (!(crsStartMinute.matches("[0-9]+")) && (Integer.parseInt(crsStartMinute) >= 0) && (Integer.parseInt(crsStartMinute) < 60)) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please enter 00-59", Toast.LENGTH_SHORT).show();
                } else if (!(crsEndMinute.matches("[0-9]+")) && (Integer.parseInt(crsEndMinute) >= 0) && (Integer.parseInt(crsEndMinute) < 60)){
                    Toast.makeText(AddOrEditCourseDetails.this, "Please enter 00-59", Toast.LENGTH_SHORT).show();
                } else if(Integer.parseInt(crsStartHour)>Integer.parseInt(crsEndHour)) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please make sure that the start time isn't after the end time", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(crsStartHour)==Integer.parseInt(crsEndHour) && Integer.parseInt(crsStartMinute)>Integer.parseInt(crsEndMinute)){
                    Toast.makeText(AddOrEditCourseDetails.this, "Please make sure that the start time isn't after the end time", Toast.LENGTH_SHORT).show();
                } else{
                    Session session = new Session(stringToDay(day),Integer.parseInt(crsStartHour),Integer.parseInt(crsStartMinute),Integer.parseInt(crsEndHour),Integer.parseInt(crsEndMinute));
                    boolean addSession =  db_course.addCrsSession(MainActivity.currentCourse.getCrsCode(),session);
                    Log.d("CrsCode", MainActivity.currentCourse.getCrsCode());

                    if(addSession){
                        Toast.makeText(AddOrEditCourseDetails.this,"Session added successfully!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddOrEditCourseDetails.this,"Session added failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        deleteSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = courseDay.getText().toString();
                String crsStartHour = startHour.getText().toString();
                String crsStartMinute = startMinute.getText().toString();
                String crsEndHour = endHour.getText().toString();
                String crsEndMinute = endMinute.getText().toString();

                if (day.isEmpty() || crsStartHour.isEmpty() || crsStartMinute.isEmpty() || crsEndHour.isEmpty() || crsEndMinute.isEmpty()) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please fill all fields! ", Toast.LENGTH_SHORT).show();

                } else if (!day.equals("Monday") && !day.equals("Tuesday") && !day.equals("Wednesday") && !day.equals("Thursday") && !day.equals("Friday")) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please enter a valid day! ", Toast.LENGTH_SHORT).show();
                } else if (!(crsStartHour.matches("[0-9]+") && (Integer.parseInt(crsStartHour) >= 0) && (Integer.parseInt(crsStartHour) < 24))) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please enter an Integer 0-23", Toast.LENGTH_SHORT).show();
                } else if (!(crsEndHour.matches("[0-9]+")) && (Integer.parseInt(crsEndHour) >= 0) && (Integer.parseInt(crsEndHour) < 24)) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please enter an Integer 0-23", Toast.LENGTH_SHORT).show();
                } else if (!(crsStartMinute.matches("[0-9]+")) && (Integer.parseInt(crsStartMinute) >= 0) && (Integer.parseInt(crsStartMinute) < 60)) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please enter 00-59", Toast.LENGTH_SHORT).show();
                } else if (!(crsEndMinute.matches("[0-9]+")) && (Integer.parseInt(crsEndMinute) >= 0) && (Integer.parseInt(crsEndMinute) < 60)){
                    Toast.makeText(AddOrEditCourseDetails.this, "Please enter 00-59", Toast.LENGTH_SHORT).show();
                } else if(Integer.parseInt(crsStartHour)>Integer.parseInt(crsEndHour)) {
                    Toast.makeText(AddOrEditCourseDetails.this, "Please make sure that the start time isn't after the end time", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(crsStartHour)==Integer.parseInt(crsEndHour) && Integer.parseInt(crsStartMinute)>Integer.parseInt(crsEndMinute)){
                    Toast.makeText(AddOrEditCourseDetails.this, "Please make sure that the start time isn't after the end time", Toast.LENGTH_SHORT).show();
                }else{
                    Session session = new Session(stringToDay(day),Integer.parseInt(crsStartHour),Integer.parseInt(crsStartMinute),Integer.parseInt(crsEndHour),Integer.parseInt(crsEndMinute));

                    boolean delete = db_course.deleteCrsSession(MainActivity.currentCourse.getCrsCode(),session);
                    if(delete){
                        Toast.makeText(AddOrEditCourseDetails.this,"Session deleted successfully!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddOrEditCourseDetails.this,"Session deleted failed!(It doesn't exist)", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        unAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instructor, Capacity,Description,Session-->null
                db_course.editCrsInstructor(MainActivity.currentCourse.getCrsCode(),null,true);
                Toast.makeText(AddOrEditCourseDetails.this,"Instructor unassigned", Toast.LENGTH_SHORT).show();
                MainActivity.currentCourse = null;
                startActivity(new Intent(getApplicationContext(),InstructorActivity.class));
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InstructorActivity.class));
            }
        });

    }

    private Day stringToDay(String str){
        switch (str) {
            case "Monday":
                return Day.MONDAY;
            case "Tuesday":
                return Day.TUESDAY;
            case "Wednesday":
                return Day.WEDNESDAY;
            case "Thursday":
                return Day.THURSDAY;
            default:
                return Day.FRIDAY;
        }
    }
}