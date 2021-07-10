package com.example.car_rental_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_AddCar extends AppCompatActivity {

    private Button addcarbutton;
    private EditText nameEditText, caridEditText, carvalueEditText;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_car);

        databaseReference= FirebaseDatabase.getInstance().getReference("Cars");

        addcarbutton=findViewById(R.id.addcarbuttonId);
        nameEditText=findViewById(R.id.addcarnameId);
        caridEditText = findViewById(R.id.addcaridnumberId);
        carvalueEditText= findViewById(R.id.car_value);
        addcarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCarData();
            }
        });
    }
    public void addCarData()
    {
        String carname=nameEditText.getText().toString().trim();
        String carnumberid=caridEditText.getText().toString().trim();
        String carvalue=carvalueEditText.getText().toString().trim();

        String key= databaseReference.push().getKey();
        Car car = new Car(carname,carnumberid,carvalue);
        databaseReference.child(key).setValue(car);
        Toast.makeText(getApplicationContext(),"New Car added successfully",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Admin_AddCar.this,Admin_Dashboard.class);
        startActivity(intent);

        nameEditText.setText("");
        caridEditText.setText("");
        carvalueEditText.setText("");
    }
}
