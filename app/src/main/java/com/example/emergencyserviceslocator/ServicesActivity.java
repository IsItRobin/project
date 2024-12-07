package com.example.emergencyserviceslocator;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ServicesActivity extends AppCompatActivity {

    private ImageButton Imagebutton1, Imagebutton2, Imagebutton3;
    private Button policebutton, phonebutton, Hospital, Firefighter ,profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
//        Intent intent = new Intent(MainActivity2.this, M.class);
//        startActivity(intent);

        // FrameLayout and buttons inside FrameLayout
        Imagebutton1 = findViewById(R.id.imageButton1);
        Imagebutton2 = findViewById(R.id.imageButton2);
        Imagebutton3 = findViewById(R.id.imageButton3);

        Imagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity3 when button1 is clicked
                Intent intent = new Intent(ServicesActivity.this, HospitalActivity.class);
                startActivity(intent);
            }
        });

        Imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity3 when button2 is clicked
                Intent intent = new Intent(ServicesActivity.this, PoliceActivity.class);
                startActivity(intent);
            }
        });

        Imagebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity3 when button3 is clicked
                Intent intent = new Intent(ServicesActivity.this, FireActivity.class);
                startActivity(intent);
            }
        });

//        userprofile button
//        profile = findViewById(R.id.phone_num);
//
//
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Start MainActivity3 or handle the click as needed
//                Intent intent = new Intent(MainActivity2.this, EditProfileActivity.class);
//                startActivity(intent);
//            }
//        });

        // Upper buttons
        policebutton = findViewById(R.id.police_button);
        Firefighter = findViewById(R.id.Button6);

        Hospital = findViewById(R.id.button5);


        // Set onClickListener for police button
        policebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity3 or handle the click as needed
                Intent intent = new Intent(ServicesActivity.this, PoliceActivity.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for Hospital button
        Hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity3 or handle the click as needed
                Intent intent = new Intent(ServicesActivity.this, HospitalActivity.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for Firefighter button
        Firefighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity3 or handle the click as needed
                Intent intent = new Intent(ServicesActivity.this, FireActivity.class);
                startActivity(intent);
            }
        });
    }
}

