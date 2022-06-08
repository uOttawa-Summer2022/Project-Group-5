package com.example.course_booking;

import android.app.Activity;
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

public class SignupActivity extends AppCompatActivity {
    Activity context = this;
    EditText username,password;
    Button btnSignUp;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        
        username = findViewById(R.id.usernameForm);
        password = findViewById(R.id.passwordForm);
        btnSignUp = findViewById(R.id.btnSignup);
        tv = findViewById(R.id.textView);
        
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        
    }

    private void signup() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnCompleteListener(context,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    tv.setText("Result: "+user.getEmail()+"Registration success and you can login in");

                } else {
                    tv.setText("Result: Registration failed"+task.getException());
                }
            }
        });
    }
}
