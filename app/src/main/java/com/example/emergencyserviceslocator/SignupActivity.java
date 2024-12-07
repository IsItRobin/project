package com.example.emergencyserviceslocator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;

    FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input values
                String name = signupName.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                // Check if username already exists
                if (isUsernameExists(username)) {
                    Toast.makeText(SignupActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check password strength
                if (!isPasswordStrong(password)) {
                    Toast.makeText(SignupActivity.this, "Password is weak", Toast.LENGTH_SHORT).show();
                    return;
                }

                // If username doesn't exist and password is strong, proceed with signup
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                HelperClass helperClass = new HelperClass(name, username, password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(SignupActivity.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, Login.class);
                startActivity(intent);
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private boolean isUsernameExists(String username) {
        // Query the database to check if the username already exists
        // Return true if exists, false otherwise
        // Example implementation: You need to implement this method according to your database structure
        // For Firebase, you would typically use a ValueEventListener to query the database and check for existing usernames
        return false; // Placeholder return value
    }

    private boolean isPasswordStrong(String password) {
        // Define your criteria for a strong password and validate the password against them
        // Return true if the password is strong, false otherwise
        // Example implementation: You can check for minimum length, presence of uppercase letters, lowercase letters, numbers, special characters, etc.
        // For example:
        return password.length() >= 8 && containsUppercase(password) && containsLowercase(password) && containsDigit(password) && containsSpecialChar(password);
    }

    private boolean containsUppercase(String s) {
        return !s.equals(s.toLowerCase());
    }

    private boolean containsLowercase(String s) {
        return !s.equals(s.toUpperCase());
    }

    private boolean containsDigit(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsSpecialChar(String s) {
        String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
        for (char c : s.toCharArray()) {
            if (specialChars.contains(Character.toString(c))) {
                return true;
            }
        }
        return false;
    }
}
