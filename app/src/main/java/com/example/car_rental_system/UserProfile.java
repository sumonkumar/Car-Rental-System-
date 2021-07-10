package com.example.car_rental_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {


    private Button signOutButton;
    private  TextView nameProfileDB, usernameProfileDB, emailProfileDB, phoneProfileDB;



    private FirebaseAuth mAuth;
    private FirebaseDatabase dbase;
    private DatabaseReference dbRef;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        signOutButton = findViewById(R.id.logout);


        nameProfileDB = findViewById(R.id.Name);
        usernameProfileDB = findViewById(R.id.Username);
        emailProfileDB = findViewById(R.id.Email);
        phoneProfileDB = findViewById(R.id.Phone);




        mAuth = FirebaseAuth.getInstance();
        dbase = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("user");




        dbRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user profile = snapshot.getValue(user.class);
                String name = profile.name;
                String username = profile.username;
                String email = profile.email;
                String phone = profile.phone;

                nameProfileDB.setText("Name: "+name);
                usernameProfileDB.setText("Username: "+username);
                phoneProfileDB.setText("Phone: "+phone);
                emailProfileDB.setText("Email: "+email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        signOutButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                signOut();
                break;
        }
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }
}