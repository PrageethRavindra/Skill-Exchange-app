package com.example.skillsexchangemobileapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;
import com.example.skillsexchangemobileapp.utils.DBHelper;

public class LoginActivity extends AppCompatActivity {

    private Spinner roleSpinner;
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView signUpTextView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Make sure this matches your layout XML file name

        // Initialize views
        roleSpinner = findViewById(R.id.roleSpinner);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.signUpTextView);

        // Initialize the database helper
        dbHelper = new DBHelper(this);

        // Set up the Spinner (dropdown)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        // Set listener for role selection
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle role selection if needed
                String selectedRole = parentView.getItemAtPosition(position).toString();
                // You can use the selectedRole to show or hide fields based on the selected role
                Toast.makeText(LoginActivity.this, "Selected Role: " + selectedRole, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        // Handle login button click
        loginButton.setOnClickListener(v -> {
            // Get the entered email, password, and role
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String role = roleSpinner.getSelectedItem().toString();

            // Validate inputs
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Email and password are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if the credentials match in the database
            boolean userExists = dbHelper.checkUserCredentials(email, password, role);

            if (userExists) {
                // If credentials match, navigate to the respective home screen
                Intent intent;
                if (role.equals("Learner")) {
                    intent = new Intent(LoginActivity.this, LearnerHomeActivity.class); // Replace with your Learner home activity
                } else if (role.equals("Resource People")) {
                    intent = new Intent(LoginActivity.this, ResourcePeopleHomeActivity.class); // Replace with your Resource People home activity
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid role", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(intent);
                finish(); // Close the login activity
            } else {
                Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle sign up link click
        signUpTextView.setOnClickListener(v -> {
            // Navigate to the sign-up screen
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
}
