package com.example.course_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    DBHelper db;
    EditText username,password;
    Button login;
    TextView signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.signUp);
        db = new DBHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);



        //When Login button is pressed
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();


                if(nameTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter your name or password", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkuserpass = db.checkpassword(nameTxt,passwordTxt);
                    if (checkuserpass){
                        Toast.makeText(MainActivity.this,"Log in successfully",Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getApplicationContext(),InstructorActivity.class));
                        //startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                        //startActivity(new Intent(getApplicationContext(),AdminActivity.class));
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

                //Still trying to figure out how to switch pages
                //Intent intent = new Intent(this, SignupActivity);
                //startActivity(intent);


                startActivity(new Intent(getApplicationContext(), SignupActivity.class));

            }
        });
    }

}