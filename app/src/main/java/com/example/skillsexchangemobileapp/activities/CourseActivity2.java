package com.example.skillsexchangemobileapp.activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;

public class CourseActivity2 extends AppCompatActivity {

    private LinearLayout musicCard, photographyCard, fitnessCard, dancingCard, cookingCard, programmingCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Initialize views
        musicCard = findViewById(R.id.musicCard);
        photographyCard = findViewById(R.id.photographyCard);
        fitnessCard = findViewById(R.id.fitnessCard);
        dancingCard = findViewById(R.id.dancingCard);
        cookingCard = findViewById(R.id.cookingCard);
        programmingCard = findViewById(R.id.programmingCard);

        // Set listeners for category cards using a single method to avoid repetition
        setupCategoryCard(musicCard, "Music");
        setupCategoryCard(photographyCard, "Photography");
        setupCategoryCard(fitnessCard, "Fitness");
        setupCategoryCard(dancingCard, "Dancing");
        setupCategoryCard(cookingCard, "Cooking");
        setupCategoryCard(programmingCard, "Programming");

        // Initialize and set listener for SearchView
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search submit action (e.g., filter categories or show results)
                Toast.makeText(CourseActivity2.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
                return false; // Allow normal behavior after submitting the query
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle text change for search, e.g., filtering categories dynamically
                return false; // Add filtering logic if needed
            }
        });
    }

    // Method to set up category card click listeners
    private void setupCategoryCard(LinearLayout card, String category) {
        card.setOnClickListener(v -> showCategory(category));
    }

    // Method to show the selected category
    private void showCategory(String category) {
        Toast.makeText(CourseActivity2.this, "Selected Category: " + category, Toast.LENGTH_SHORT).show();
        // Logic to display the specific courses for the selected category can be added here
        // For example, navigate to a new activity with the category as an extra
    }
}
