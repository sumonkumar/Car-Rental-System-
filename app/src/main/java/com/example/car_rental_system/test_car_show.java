package com.example.car_rental_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class test_car_show extends AppCompatActivity {

    private Context context;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private List<User_Car> carList;
    User_CustomAdapter user_customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_car_show);

        recyclerView = findViewById(R.id.recyclerviewid);
        carList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cars");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                carList.clear();

                for (DataSnapshot snapshot1: snapshot.getChildren()) {
                    User_Car car = snapshot1.getValue(User_Car.class);
                    carList.add(car);
                }
                user_customAdapter = new User_CustomAdapter(test_car_show.this,carList);
                recyclerView.setAdapter(user_customAdapter);

                recyclerView.setLayoutManager(layoutManager);

                user_customAdapter.setOnItemClickListener(new User_CustomAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {

                        Toast.makeText(getApplicationContext(),"Item clicked : "+position,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onItemLongClick(int position, View v) {
                        Toast.makeText(getApplicationContext(),"Item Long clicked : "+position,Toast.LENGTH_LONG).show();
                    }
                });
    }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}