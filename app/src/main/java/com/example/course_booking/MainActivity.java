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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    DBHelper db;
    EditText username,password;
    Button login,signUpFirst;
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
        signUpFirst = findViewById(R.id.signUpFirst);



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
                        //Go to the corresponding home page according to the account type
                        //startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                        //startActivity(new Intent(getApplicationContext(),StudentActivity.class));
                        //startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                    }else{
                        Toast.makeText(MainActivity.this,"invalid password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //When Signup TextView is pressed
        signUpFirst.setOnClickListener(new View.OnClickListener() {
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