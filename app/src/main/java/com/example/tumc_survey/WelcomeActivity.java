package com.example.tumc_survey;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome); // Make sure this layout exists

        // Initialize buttons
        Button newProjectBtn = findViewById(R.id.newProjectBtn);
        Button searchProjectBtn = findViewById(R.id.searchProjectBtn);

        // Set click listeners for buttons
        newProjectBtn.setOnClickListener(v -> {
            // Start New Project Activity
            Intent intent = new Intent(WelcomeActivity.this, NewProjectActivity.class);
            startActivity(intent);
        });

        searchProjectBtn.setOnClickListener(v -> {
            // Start Search Project Activity
            Intent intent = new Intent(WelcomeActivity.this, SearchProjectActivity.class);
            startActivity(intent);
        });
    }
}
