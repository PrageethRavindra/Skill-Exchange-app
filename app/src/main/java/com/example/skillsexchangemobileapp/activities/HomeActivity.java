package com.example.skillsexchangemobileapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout coursesCard, aboutUsCard, resourcePeopleCard, schedulingCard;
    private Button profileManagementButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        coursesCard = findViewById(R.id.coursesCard);
        aboutUsCard = findViewById(R.id.aboutUsCard);
        resourcePeopleCard = findViewById(R.id.resourcePeopleCard);
        schedulingCard = findViewById(R.id.schedulingCard);
        profileManagementButton = findViewById(R.id.profileManagementButton);

        // Set click listeners for navigation
        coursesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });

        aboutUsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        resourcePeopleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ResourcePeopleActivity.class);
                startActivity(intent);
            }
        });

        schedulingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SchedulingActivity.class);
                startActivity(intent);
            }
        });

        // Profile Management Button click listener
        profileManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileManagementActivity.class);
                startActivity(intent);
            }
        });
    }
}
