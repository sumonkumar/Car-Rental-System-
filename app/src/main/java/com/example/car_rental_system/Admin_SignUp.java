package com.example.car_rental_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Admin_SignUp extends AppCompatActivity implements View.OnClickListener {


    private EditText UserNameSignUp, EmailSignUp,PhoneSignUp, PasswordSignUP;
    private Button SignUpButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_sign_up);

        mAuth = FirebaseAuth.getInstance();

        //UserNameSignUp = findViewById(R.id.usernameId);
        EmailSignUp = findViewById(R.id.emailId);
        //PhoneSignUp = findViewById(R.id.phoneId);
        PasswordSignUP = findViewById(R.id.passwordId);

        SignUpButton = findViewById(R.id.signUpButtonId);

        SignUpButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signUpButtonId:
                userRegister();

                break;
        }

    }

    private void userRegister() {

        //String usernamesignup = UserNameSignUp.getText().toString().trim();
        String emailsignup = EmailSignUp.getText().toString().trim();
        //String phonesignup = PhoneSignUp.getText().toString().trim();
        String passwordsignup = PasswordSignUP.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(emailsignup,passwordsignup).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Sign up successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Admin_Dashboard.class);
                    startActivity(intent);
                }
                if (task.getException() instanceof FirebaseAuthUserCollisionException)
                {
                    Toast.makeText(getApplicationContext(), "User is Already Registered", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Signup Unsuccessfull :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
