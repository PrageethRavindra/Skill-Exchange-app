package com.example.skillsexchangemobileapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;

public class SelectRoleActivity extends AppCompatActivity {

    private Button learnerButton, resourcePersonButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);

        // Initialize the buttons and SharedPreferences
        learnerButton = findViewById(R.id.learnerButton);
        resourcePersonButton = findViewById(R.id.resourcePersonButton);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Set click listeners for the buttons
        learnerButton.setOnClickListener(v -> {
            saveUserRole("Learner");
            navigateToLogin("Learner");
        });

        resourcePersonButton.setOnClickListener(v -> {
            saveUserRole("Resource Person");
            navigateToLogin("Resource Person");
        });
    }

    // Method to save selected role in SharedPreferences
    private void saveUserRole(String role) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userRole", role);
        editor.apply();
    }

    // Method to navigate to LoginActivity with selected role
    private void navigateToLogin(String role) {
        Intent intent = new Intent(SelectRoleActivity.this, GetStartedActivity.class);
        intent.putExtra("role", role);
        startActivity(intent);
        finish();
    }
}
