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

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myproject-8ced6-default-rtdb.firebaseio.com/");

    EditText username,password;
    Button login;
    TextView signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.btnLogin);
        signup = findViewById(R.id.signUp);

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
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String getPassword = snapshot.child(nameTxt).child("password").getValue(String.class);
                            if (getPassword.equals(passwordTxt)){
                                Toast.makeText(MainActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                //Just do the jump to the admin interface, it should be changed.
                                startActivity(new Intent(MainActivity.this, AdminActivity.class));
                            }else{
                                Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        //When Signup Button is pressed
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
        });
    }

}