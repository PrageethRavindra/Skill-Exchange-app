package com.example.skillsexchangemobileapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnProfile, btnSettings, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        tvWelcome = findViewById(R.id.tvWelcome);
        btnProfile = findViewById(R.id.btnProfile);
        btnSettings = findViewById(R.id.btnSettings);
        btnLogout = findViewById(R.id.btnLogout);


        // Get user data passed from LoginActivity (if any)
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            tvWelcome.setText("Welcome, " + username + "!");
        }

        // Set up button click listeners
        btnProfile.setOnClickListener(v -> {
            // Navigate to Profile Activity
            Intent intent = new Intent(HomeActivity.this, UserProfileActivity.class);
            startActivity(intent);
        });

//        btnSettings.setOnClickListener(v -> {
//            // Navigate to Settings Activity
//            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
//            startActivity(intent);
//        });

        btnLogout.setOnClickListener(v -> {
            // Logout and return to LoginActivity
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
