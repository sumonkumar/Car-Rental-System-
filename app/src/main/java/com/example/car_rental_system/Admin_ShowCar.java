package com.example.car_rental_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_ShowCar extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;

    private List<Car> carList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_car);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cars");
        carList = new ArrayList<>();

        customAdapter = new CustomAdapter(Admin_ShowCar.this,carList);

        listView=findViewById(R.id.listviewid);
    }
    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                carList.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Car car = dataSnapshot1.getValue(Car.class);
                    carList.add(car);
                }
                listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}