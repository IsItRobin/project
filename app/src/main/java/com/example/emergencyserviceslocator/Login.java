package com.example.emergencyserviceslocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emergencyserviceslocator.MapsActivity;
import com.example.emergencyserviceslocator.R;
import com.example.emergencyserviceslocator.SignupActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    // Declaring variables for UI elements
    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initializing UI elements
        // R.id is reference id
        loginUsername = findViewById(R.id.signup_username);
        loginPassword = findViewById(R.id.signup_password);
        loginButton = findViewById(R.id.signup_button);
        signupRedirectText = findViewById(R.id.loginRedirectText);

        // Set onClickListener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate username and password
                if (!validateUsername() | !validatePassword()) {
                    // If validation fails, do nothing
                } else {
                    // If validation succeeds, proceed to check user
//                    checkUser();
                    Intent intent = new Intent(Login.this, MapsActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Set onClickListener for signup redirection text
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to signup activity when clicked
                Intent intent = new Intent(Login.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    //    if username is empty
    // Method to validate username
    public Boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("Username cannot be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }
    //    if password is empty
    // Method to validate password
    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }


    // Method to check user credentials
    public void checkUser(){
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        // Get reference to the "users" node in Firebase Realtime Database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        // Query to check if the entered username exists in the database
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // If the username exists in the database
                if (snapshot.exists()){
                    loginUsername.setError(null); // Clear any previous error message

                    // Retrieve the password associated with the username from the database
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    // If the entered password matches the one retrieved from the database
                    if (passwordFromDB.equals(userPassword)) {
                        loginUsername.setError(null); // Clear any previous error message

                        // Retrieve user data (name, email, username) from the database
                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);

                        // Redirect to MainActivity2 and pass user data as extras in the intent
                        Intent intent = new Intent(Login.this, MapsActivity.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else {
                        // If the entered password is incorrect, display an error message
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
                    }
                } else {
                    // If the username does not exist in the database, display an error message
                    loginUsername.setError("You are not on party List");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
}
