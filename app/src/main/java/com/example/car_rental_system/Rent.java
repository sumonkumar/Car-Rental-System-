package com.example.car_rental_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Rent extends AppCompatActivity /*implements View.OnClickListener*/ {

    private TextView carName, carNumber, carPrice, totalCost;
    private EditText rentDate, rentTime;
    private Button RentCar;
    DatabaseReference databaseReference;
    public String carUid;
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        databaseReference = FirebaseDatabase.getInstance().getReference("RentDateTime");

        carName = (TextView) findViewById(R.id.carName);
        carNumber = (TextView) findViewById(R.id.carNumber);
        carPrice = (TextView) findViewById(R.id.carPrice);
        rentDate = findViewById(R.id.date);
        rentTime = findViewById(R.id.time);
        totalCost = findViewById(R.id.cost);
        RentCar = findViewById(R.id.rentCar);

        carName.setText(getIntent().getStringExtra("car_Name"));
        carNumber.setText(getIntent().getStringExtra("car_Number"));
        carPrice.setText(getIntent().getStringExtra("car_Value"));



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        rentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Rent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month = month + 1;
                        String date = "Rent date: " + day + "/" + month + "/" + year;
                        rentDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        totalCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRentDateTimeShowCost();
            }
        });
        RentCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "You may rent a car in your time", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Rent.this, Payment.class);
                startActivity(intent);
            }
        });

    /*    Intent intent = getIntent();
        carUid = intent.getStringExtra("carUid");


        dbRef = FirebaseDatabase.getInstance().getReference("Cars");




        dbRef.child(carUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {


                    String carname = ""+snapshot.child("carname").getValue();
                    String carnumbernumberId = ""+snapshot.child("carnumbernumberId").getValue();
                    String carvalue = ""+snapshot.child("carvalue").getValue();

                    carName.setText("Car Name: "+carname);
                    carNumber.setText("Car Number: "+carnumbernumberId);
                    carPrice.setText("Value per hour: "+carvalue);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    */    //RentCar.setOnClickListener(this);


    }

    private void addRentDateTimeShowCost() {

        String Date = rentDate.getText().toString().trim();
        String Time = rentTime.getText().toString().trim();

        String key = databaseReference.push().getKey();
        dateTime dt = new dateTime(Date, Time);
        databaseReference.child(key).setValue(dt);
        Toast.makeText(getApplicationContext(), "You may rent a car in your time", Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(Rent.this, Dashboard.class);
        //startActivity(intent);

        rentDate.setText("");
        rentTime.setText("");
        int num2 = Integer.parseInt(Time);
        String numS = carPrice.getText().toString();
        int num3 = Integer.parseInt(numS);
        int nCost = num2*num3;
        totalCost.setText(String.valueOf("You need to pay "+nCost+" Taka for rent"));

  /*  @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rentCar:
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);

                break;
        }
    }*/
    }
}