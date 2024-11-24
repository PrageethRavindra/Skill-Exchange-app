package com.example.skillsexchangemobileapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;

public class ResourcePeopleHomeActivity extends AppCompatActivity {

    private LinearLayout resourceManagementCard;
    private LinearLayout viewCourseCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resource_people_home);

        resourceManagementCard = findViewById(R.id.resourceManagementCard);
        viewCourseCard = findViewById(R.id.viewCourseCard);// Corrected ID

        viewCourseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResourcePeopleHomeActivity.this, Ongoing_resourcePeople_Courses.class);
                startActivity(intent);
                Toast.makeText(ResourcePeopleHomeActivity.this, "Navigating to Ongoing_resourcePeople_Courses", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for "Courses"
        resourceManagementCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResourcePeopleHomeActivity.this, ResourcePeopleActivity.class);
                startActivity(intent);
                Toast.makeText(ResourcePeopleHomeActivity.this, "Navigating to Resource People Activity Scheduling", Toast.LENGTH_SHORT).show();
            }
        });




        // Example of an action
        Toast.makeText(this, "Welcome to Resource People Dashboard", Toast.LENGTH_SHORT).show();

    }



}



