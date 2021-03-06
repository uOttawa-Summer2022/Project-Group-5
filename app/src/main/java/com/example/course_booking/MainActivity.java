package com.example.course_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DBHelper_course db_course;
    DBHelper db;
    EditText username,password;
    Button login;
    TextView signup;
    static UserModel currentUser = new UserModel();
    static CourseModel currentCourse = new CourseModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.signUp);
        db = new DBHelper(this);
        db_course = new DBHelper_course(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        //Create default Admin account
        //db.insertData("admin", "admin", Type.ADMIN);
        //db.insertData("Max", "secret", Type.ADMIN);



        //When Login button is pressed
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();


                if(nameTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter your name or password", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkUserPass = db.checkPassword(nameTxt,passwordTxt);
                    if (checkUserPass){
                        Toast.makeText(MainActivity.this,"Log in successfully",Toast.LENGTH_SHORT).show();
                        currentUser = db.findUser(nameTxt);
                        Log.d("type", String.valueOf(currentUser));
                        switch (currentUser.getAccType()){
                            case STUDENT:
                                startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                                break;
                            case INSTRUCTOR:
                                startActivity(new Intent(getApplicationContext(), InstructorActivity.class));
                                break;
                            case ADMIN:
                                startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                                break;
                        }

                    }else{
                        Toast.makeText(MainActivity.this,"invalid password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //When Signup TextView is pressed
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), SignupActivity.class));

            }
        });
    }

}