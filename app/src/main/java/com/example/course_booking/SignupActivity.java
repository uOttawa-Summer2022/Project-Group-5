package com.example.course_booking;

import android.app.Activity;
import android.database.Cursor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.DatabaseMetaData;

public class SignupActivity extends AppCompatActivity {

    EditText username,password;
    Button btnSignUp;
    TextView returnLogin;
    DBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username = findViewById(R.id.usernameForm);
        password = findViewById(R.id.passwordForm);
        btnSignUp = findViewById(R.id.btnSignup);
        returnLogin = findViewById(R.id.returnLogin);
        RadioGroup radiogroup = findViewById(R.id.grpAccType);


        // find the radio button by returned id

        db = new DBHelper(this);
        //db.insertData("Max","secret",Type.ADMIN);       <- default admin account



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radiogroup.getCheckedRadioButtonId();
                RadioButton radioBtn = findViewById(selectedId);
                //get data from EditTexts into String variables
                String nameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                String accTypeSTR = radioBtn.getText().toString();
                Type accType = null;
                if (accTypeSTR.equals("Student")){
                    accType = Type.STUDENT;
                } else if (accTypeSTR.equals("Instructor")){
                    accType = Type.INSTRUCTOR;
                }

                //check if user fill all the fields before sending data to firebase
                if(nameTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if(accType == null){
                    Toast.makeText(SignupActivity.this, "Select Student or Instructor as account type", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkUser = db.checkUsername(nameTxt);
                    if(!checkUser){
                        boolean insert = db.insertData(nameTxt,passwordTxt,accType);
                        if(insert){
                            Toast.makeText(SignupActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(SignupActivity.this,"Registered failed",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(SignupActivity.this, "User already exists! Please sign in",Toast.LENGTH_SHORT).show();
                    }



                }

            }
        });

        returnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }


}
