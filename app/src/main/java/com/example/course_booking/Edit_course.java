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

public class Edit_course extends AppCompatActivity{
    DBHelper_course db_course;
    Button btn_modify;
    EditText oldCourseCode,oldCourseName,newCourseCode,newCourseName;
    TextView returnAdmin;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        db_course = new DBHelper_course(this);

        btn_modify = findViewById(R.id.btn_modify);

        oldCourseCode = findViewById(R.id.oldCourseCode);
        newCourseCode = findViewById(R.id.newCourseCode);
        oldCourseName = findViewById(R.id.oldCourseName);
        newCourseName = findViewById(R.id.newCourseName);

        returnAdmin = findViewById(R.id.returnAdmin);



        returnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.currentUser = new UserModel();
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
            }
        });

        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldCode = oldCourseCode.getText().toString();
                String newCode = newCourseCode.getText().toString();
                String oldName = oldCourseName.getText().toString();
                String newName = newCourseName.getText().toString();
                if(oldCode.isEmpty() || newCode.isEmpty() || oldName.isEmpty() || newName.isEmpty()) {
                    Toast.makeText(Edit_course.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    boolean result = db_course.editCourse(oldCode, oldName, newCode, newName);
                    if (result){
                        Toast.makeText(Edit_course.this,"Modification of course success",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Edit_course.this,"Course does not exist",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }
}


