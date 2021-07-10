package com.example.car_rental_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Rent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        int i = getIntent().getExtras().getInt("key");
    }
}