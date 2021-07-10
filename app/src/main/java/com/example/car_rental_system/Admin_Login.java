package com.example.car_rental_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Admin_Login extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, signUpButton;
    private TextView textView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        emailEditText = (EditText) findViewById(R.id.emailId);
        passwordEditText = (EditText) findViewById(R.id.passwordId);
        loginButton = (Button) findViewById(R.id.loginButtonId);
        signUpButton = (Button) findViewById(R.id.signUpButtonId);

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.loginButtonId:
                userLogin();
                break;
            case R.id.signUpButtonId:
                Intent intent = new Intent(Admin_Login.this, Admin_SignUp.class);
                startActivity(intent);
                break;
        }
    }

    private void userLogin() {
        String emailloginup = emailEditText.getText().toString().trim();
        String passwordloginup = passwordEditText.getText().toString().trim();

        if (emailloginup.isEmpty()) {
            emailEditText.setError("Enter an email address");

            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailloginup).matches()) {
            emailEditText.setError("Enter a valid email address");
            emailEditText.requestFocus();
            return;
        }
        if (passwordloginup.isEmpty())
        {
            passwordEditText.setError("Enter a password");
            passwordEditText.requestFocus();
            return;
        }
        if (passwordloginup.length()<6)
        {
            passwordEditText.setError("Minimum length of a password should be 6");
            passwordEditText.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(emailloginup,passwordloginup).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    Intent intent=new Intent(getApplicationContext(),Admin_Dashboard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Email or Password is incorrect",Toast.LENGTH_SHORT).show();
                }

            }
        });

        ;

    }
}
