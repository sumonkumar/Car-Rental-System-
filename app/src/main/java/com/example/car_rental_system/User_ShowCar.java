package com.example.car_rental_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class User_ShowCar extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;

    static List<Car> carList;
    User_CustomAdapter customAdapter;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_show_car);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cars");
        carList = new ArrayList<>();

        customAdapter = new User_CustomAdapter(User_ShowCar.this,carList);

        listView=findViewById(R.id.user_listview);
        button = findViewById(R.id.rent);
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
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getApplicationContext(),Rent.class);
                        intent.putExtra("key",i);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Rent.class);
                startActivity(intent);
            }
        });*/

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (view.getId()){
                    case R.id.rent:
                        Intent intent = new Intent(getApplicationContext(),Rent.class);
                        Toast.makeText(User_ShowCar.this, "This Item is clicked", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
                Intent intent = new Intent(getApplicationContext(),Rent.class);
                Toast.makeText(User_ShowCar.this, "This Item is clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });*/
    }
}