package com.example.skillsexchangemobileapp.activities;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skillsexchangemobileapp.R;

public class ResourcePeopleHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resource_people_home);

        // Example of an action
        Toast.makeText(this, "Welcome to Resource People Dashboard", Toast.LENGTH_SHORT).show();

    }
}
