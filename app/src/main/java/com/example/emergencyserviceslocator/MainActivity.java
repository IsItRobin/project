package com.example.emergencyserviceslocator;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);


        // Set OnClickListener for button1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to open Activity1
                Intent intent = new Intent(MainActivity.this,Login.class);
                // Start Activity1
                startActivity(intent);
            }
        });

        // Set OnClickListener for button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to open Activity2
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                // Start Activity2
                startActivity(intent);
            }
        });
    }
}