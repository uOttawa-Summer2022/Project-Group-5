package com.example.course_booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddOrEditCourseDetails extends AppCompatActivity {

    Button addDescriptionAndCapacity,editDescriptionAndCapacity,addSession,deleteSession;
    EditText crsDescription,crsCapacity,courseDay,startHour,startMinute,endHour,endMinute;
    TextView unAssign,goBack;

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


        addDescriptionAndCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editDescriptionAndCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deleteSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        unAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InstructorActivity.class));
            }
        });

    }
}