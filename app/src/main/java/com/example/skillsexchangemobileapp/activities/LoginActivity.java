package com.example.skillsexchangemobileapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;
import com.example.skillsexchangemobileapp.utils.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView signUpTextView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.signUpTextView);

        dbHelper = new DatabaseHelper(this);

        // Set onClick listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkCredentials(email, password)) {
                        // If credentials are correct, go to HomeActivity
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish(); // Close the login activity
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set onClick listener for sign up link
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to RegisterActivity if the user doesn't have an account
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to check if the email and password match a record in the database
    private boolean checkCredentials(String email, String password) {
        // Access database for user credentials
        Cursor cursor = dbHelper.getReadableDatabase().query(
                DatabaseHelper.TABLE_USERS,   // Table to query
                new String[]{DatabaseHelper.COLUMN_EMAIL, DatabaseHelper.COLUMN_PASSWORD}, // Columns to return
                DatabaseHelper.COLUMN_EMAIL + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?", // WHERE clause
                new String[]{email, password}, // Selection arguments
                null, // Group by
                null, // Having
                null  // Order by
        );

        boolean isValid = cursor.getCount() > 0; // If a matching record is found
        cursor.close(); // Close cursor after use
        return isValid; // Return if the credentials are valid
    }
}
