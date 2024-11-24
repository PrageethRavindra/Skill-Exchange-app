package com.example.skillsexchangemobileapp.activities;

import android.os.Bundle;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import com.example.skillsexchangemobileapp.R;

public class ResourcePeopleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_people);

        // Example: Fetch data dynamically and update UI
        RatingBar person1Rating = findViewById(R.id.resourcePerson1Rating);
        person1Rating.setRating(4.5f); // Set dynamically fetched rating

        // You can fetch more details (e.g., name, expertise) and update the TextViews similarly
    }

}
