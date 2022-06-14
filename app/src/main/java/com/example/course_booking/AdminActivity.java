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

public class AdminActivity extends AppCompatActivity{
    TextView welcomeAdmin;
    DBHelper db_account;
    DBHelper_course db_course;
    Button createCourse,editCourse,deleteCourse,deleteAccount;
    EditText crsCode,crsName,accName;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        welcomeAdmin = (TextView)findViewById(R.id.welcomeAdmin);
        db_account = new DBHelper(this);
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
                String code = crsCode.getText().toString();
                String name = crsName.getText().toString();
                if(code.isEmpty() || name.isEmpty()) {
                    Toast.makeText(AdminActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    CourseModel course = new CourseModel(code, name);
                    db_course.insertCourse(course);
                }
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
                String code = crsCode.getText().toString();
                if(code.isEmpty()) {
                    Toast.makeText(AdminActivity.this, "Please fill specify the code of the course you want to delete.", Toast.LENGTH_SHORT).show();
                } else {
                    db_course.deleteCourse(code);
                }
            }
        });


        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTxt = accName.getText().toString();

                //check if user fill all the fields before sending data to firebase
                if(nameTxt.isEmpty()){
                    Toast.makeText(AdminActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkUser = db_account.checkusername(nameTxt);
                    UserModel target = db_account.findUser(nameTxt);
                    boolean delete = false;

                    if(checkUser){
                        switch(target.getAccType()){
                            case ADMIN:
                                Toast.makeText(AdminActivity.this,"Cannot delete Admin account",Toast.LENGTH_SHORT).show();
                            case INSTRUCTOR:
                                delete = db_account.deleteData(nameTxt);
                            case STUDENT:
                                delete = db_account.deleteData(nameTxt);
                        }
                        if(delete){
                            Toast.makeText(AdminActivity.this,"Deleteion success",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(AdminActivity.this,"Deletion fail",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(AdminActivity.this, "User does not exist!",Toast.LENGTH_SHORT).show();
                    }



                }

            }
        });



    }
}

