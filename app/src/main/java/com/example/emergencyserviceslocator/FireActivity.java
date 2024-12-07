package com.example.emergencyserviceslocator;

//concern page

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FireActivity extends AppCompatActivity {
    Button Done, cancel, fever, nonfever,Location;
//    here cancel is button is to cancel
//    send is to send report
//    here back is a image button to come on activity2


    EditText problemTextInput;

    CheckBox acceptTermsCheckBox;

    public static final String NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);


        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Apply window insets to adjust padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Done = findViewById(R.id.Done);
        cancel = findViewById(R.id.cancel);
        fever = findViewById(R.id.fever_button);
        nonfever = findViewById(R.id.nonfever_button);
        Location = findViewById(R.id.location);

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Google Maps with nearby places
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=nearby+Fire+station");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Google Maps app not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String problemText = problemTextInput.getText().toString();

                if (!problemText.isEmpty()) {
                    if (acceptTermsCheckBox.isChecked()) {
                        // Start the new activity if the CheckBox is checked
                        Intent intent = new Intent(FireActivity.this, ServicesActivity.class);
                        startActivity(intent);
                    } else {
                        // Show a toast if the CheckBox is not checked
                        Toast.makeText(FireActivity.this, "Please accept the Location otherwise no Map will shown", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FireActivity.this, "Please fill in your problem", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FireActivity.this, ServicesActivity.class);
                startActivity(intent); // Start the activity
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set OnClickListener for the "fever" button
//       / Set OnClickListener for the "fever" button
        fever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the URL with the phone number you want to dial
                openWebsiteOrDialNumber("tel:8360430635");
            }
        });

        // Set OnClickListener for the "nonfever" button
        nonfever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the URL with the phone number you want to dial
                openWebsiteOrDialNumber("tel:8360430635");
            }
        });

    }

    // Method to open a website in a web browser
    // Method to open a website in a web browser or initiate a phone call
    private void openWebsiteOrDialNumber(String url) {
        if (url.startsWith("tel:")) {
            String phoneNumber = url.substring(4);
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "Phone call not supported", Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }
}
