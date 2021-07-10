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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button RegistrationCreateAccount, call_login;
    private TextInputLayout RegistrationEmail, RegistrationPass,RegistrationName,RegistrationUsername,RegistrationPhone;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mDbase = FirebaseDatabase.getInstance();

        call_login = findViewById(R.id.reg_login_btn);
        RegistrationCreateAccount = findViewById(R.id.reg_btn);
        RegistrationEmail = findViewById(R.id.reg_email);
        RegistrationPass = findViewById(R.id.reg_password);
        RegistrationName = findViewById(R.id.reg_name);
        RegistrationPhone = findViewById(R.id.reg_phone);
        RegistrationUsername = findViewById(R.id.reg_username);

        call_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });


        RegistrationCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_btn:
                registrationmethod ();
                break;

        }
    }

    private void registrationmethod() {
        String email = RegistrationEmail.getEditText().getText().toString().trim();
        String password = RegistrationPass.getEditText().getText().toString().trim();
        String name = RegistrationName.getEditText().getText().toString();
        String phone = RegistrationPhone.getEditText().getText().toString();
        String username = RegistrationUsername.getEditText().getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    user user = new user(name,username,email,phone);
                    FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Account created Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Database Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                        Toast.makeText(getApplicationContext(), "This Email is already registered!", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }
}