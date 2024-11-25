package com.example.skillsexchangemobileapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;
import com.example.skillsexchangemobileapp.utils.DBHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileManagementActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView profilePicture;
    private EditText nameEditText, skillsEditText;
    private Button saveButton;

    private Uri selectedImageUri;
    private Bitmap selectedBitmap;

    private DBHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize views
        profilePicture = findViewById(R.id.profilePicture);
        nameEditText = findViewById(R.id.nameEditText);
        skillsEditText = findViewById(R.id.skillsEditText);
        saveButton = findViewById(R.id.saveButton);

        // Initialize database helper
        databaseHelper = new DBHelper(this);

        // Load user data from the database
        loadUserProfile();

        // Handle profile picture click
        profilePicture.setOnClickListener(v -> openImagePicker());

        // Handle save button click
        saveButton.setOnClickListener(v -> saveUserProfile());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            try {
                selectedBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                profilePicture.setImageBitmap(selectedBitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadUserProfile() {
        // Fetch user data from the database
        String[] userData = databaseHelper.getUserProfile();

        if (userData != null) {
            nameEditText.setText(userData[0]);
            skillsEditText.setText(userData[1]);

            // Load profile picture if available
            Bitmap profileImage = databaseHelper.getProfilePicture();
            if (profileImage != null) {
                profilePicture.setImageBitmap(profileImage);
            }
        }
    }

    private void saveUserProfile() {
        String name = nameEditText.getText().toString().trim();
        String skills = skillsEditText.getText().toString().trim();

        if (name.isEmpty() || skills.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert selected image to byte array
        byte[] profileImage = null;
        if (selectedBitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            selectedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            profileImage = stream.toByteArray(); // Convert Bitmap to byte[]
        }

        // Save user data to the database
        boolean isSaved = databaseHelper.updateUserProfile(name, skills, profileImage);
        if (isSaved) {
            Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProfileManagementActivity.this, LearnerHomeActivity.class);
            startActivity(intent);
            finish(); // Optional
        } else {
            Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }


}
