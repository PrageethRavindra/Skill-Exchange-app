package com.example.skillsexchangemobileapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;

import java.io.IOException;

public class ProfileManagementActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView profilePicture;
    private TextView changePhotoText;
    private EditText nameEditText, skillsEditText;
    private Button saveButton;

    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize views
        profilePicture = findViewById(R.id.profilePicture);
        changePhotoText = findViewById(R.id.changePhotoText);
        nameEditText = findViewById(R.id.nameEditText);
        skillsEditText = findViewById(R.id.skillsEditText);
        saveButton = findViewById(R.id.saveButton);

        // Handle profile picture click
        profilePicture.setOnClickListener(v -> openImagePicker());
        changePhotoText.setOnClickListener(v -> openImagePicker());

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
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                profilePicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
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
    }
}
        // Simulate saving to the database or shared preferences
