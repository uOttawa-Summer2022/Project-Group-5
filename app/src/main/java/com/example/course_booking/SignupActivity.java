package com.example.course_booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;

public class SignupActivity extends AppCompatActivity {

    EditText username,password;
    Button btnSignUp,returnLog;
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
        returnLog = findViewById(R.id.returnLog);
        db = new DBHelper(this);


        
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get data from EditTexts into String variables
                String nameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();

                //check if user fill all the fields before sending data to firebase
                if(nameTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{

                    boolean checkuser = db.checkusername(nameTxt);
                    if(!checkuser){
                        boolean insert = db.insertData(nameTxt,passwordTxt);
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

        returnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        
    }


}
