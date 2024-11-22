package com.example.skillsexchangemobileapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;

public class UserProfileActivity extends AppCompatActivity {
    private EditText nameEditText, skillsEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        nameEditText = findViewById(R.id.nameEditText);
        skillsEditText = findViewById(R.id.skillsEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> saveUserProfile());
    }

    private void saveUserProfile() {
        String name = nameEditText.getText().toString();
        String skills = skillsEditText.getText().toString();

        if (name.isEmpty() || skills.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add backend save logic here
        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
    }
}
