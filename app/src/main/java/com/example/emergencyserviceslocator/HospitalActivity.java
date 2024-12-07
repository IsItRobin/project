package com.example.emergencyserviceslocator;

//doctor page

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HospitalActivity extends AppCompatActivity {
    Button Done, cancel,fever,nonfever,Location;

    EditText problemTextInput;

    CheckBox acceptTermsCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hospital);

        Done = findViewById(R.id.done);
        cancel = findViewById(R.id.Cancel);
        fever = findViewById(R.id.fever);
        nonfever = findViewById(R.id.nonfever);
        problemTextInput = findViewById(R.id.problem);

        Location = findViewById(R.id.location);


        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String problemText = problemTextInput.getText().toString();

                if (!problemText.isEmpty()) {

                } else {
                    Toast.makeText(HospitalActivity.this, "Please fill in your problem", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Google Maps with nearby places
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=nearby+Hospital");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Google Maps app not found", Toast.LENGTH_SHORT).show();
                }
            }
        });






//        Previous
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalActivity.this, ServicesActivity.class);
                startActivity(intent); // Start the activity
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalActivity.this, ServicesActivity.class);
                startActivity(intent); // Start the activity
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set OnClickListener for the "fever" button
        nonfever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the phone number with the one you want to dial
                String phoneNumber = "+918360430635";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

// Set OnClickListener for the "nonfever" button
        fever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the URL with the actual URL you want to open
                openWebsiteOrDialNumber("https://www.google.com/search?q=nearby+hospital&rlz=1C1CHBD_en-GBIN1086IN1086&oq=near+by+hos&gs_lcrp=EgZjaHJvbWUqDAgBEAAYChixAxiABDIGCAAQRRg5MgwIARAAGAoYsQMYgAQyBwgCEAAYgAQyBwgDEAAYgAQyBwgEEAAYgAQyCQgFEAAYChiABDIJCAYQABgKGIAEMgcIBxAAGIAEMgcICBAAGIAEMgcICRAAGIAE0gEKMTMzNThqMGoxNagCCLACAQ&sourceid=chrome&ie=UTF-8");
            }
        });

    }




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
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "No app available to handle this URL", Toast.LENGTH_SHORT).show();
            }
        }
    }

}