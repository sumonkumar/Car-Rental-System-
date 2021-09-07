package com.example.car_rental_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Payment extends AppCompatActivity {
    EditText bName,bPhone,bAmount,bpass;
    TextView bTittle, bMarchent;
    Button bPay;
    ImageView bImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        bName = (EditText) findViewById(R.id.bkashName);
        bPhone = (EditText) findViewById(R.id.bkashPhone);
        bAmount = (EditText) findViewById(R.id.bkashAmount);
        bpass = (EditText) findViewById(R.id.bkashPassword);

        bTittle = (TextView) findViewById(R.id.bTittle);
        bMarchent = (TextView) findViewById(R.id.bkashMarchent);

        bPay = findViewById(R.id.bkashPay);

        bImg = findViewById(R.id.bkashImage);

        bPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(intent);
            }
        });
    }
}