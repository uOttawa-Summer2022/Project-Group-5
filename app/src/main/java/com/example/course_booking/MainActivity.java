package com.example.course_booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.btnLogin);
        signup = (TextView) findViewById(R.id.signUp);


        //When Login button is pressed
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //When Signup TextView is pressed
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Still trying to figure out how to switch pages
                //Intent intent = new Intent(this, SignupActivity);
                //startActivity(intent);
            }
        });
    }

}