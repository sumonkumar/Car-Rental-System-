package com.example.car_rental_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Admin_Dashboard extends AppCompatActivity {

    CardView card_home;
    CardView card_add_car;
    CardView card_view_car;
    CardView card_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_dashboard);

        card_home = findViewById(R.id.Admin_card_home);
        card_add_car = findViewById(R.id.Admin_card_caradd);
        card_view_car = findViewById(R.id.Admin_card_viewcar);
        card_logout = findViewById(R.id.Admin_card_logout);

        card_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Home clicked!", Toast.LENGTH_LONG).show();
            }
        });

        card_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Dashboard.this,Admin_AddCar.class);
                startActivity(intent);
            }
        });

        card_view_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Dashboard.this,Admin_ShowCar.class);
                startActivity(intent);
            }
        });

        card_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.Admin_card_logout:
                        signOut();
                        break;
                }
            }
            private void signOut() {

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(getApplicationContext(),Admin_Login.class);
                startActivity(intent);
            }
        });
    }
}