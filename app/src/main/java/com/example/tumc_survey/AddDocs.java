package com.example.tumc_survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddDocs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_documents);

        // Find the Add button by its ID
        Button addButton = findViewById(R.id.btnEdit);

        // Set OnClickListener for the Add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to DisplayDocs activity
                Intent intent = new Intent(AddDocs.this, DisplayDocs.class);
                startActivity(intent);
            }
        });

        // Optionally handle the Cancel button (if required)
        Button cancelButton = findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Optionally finish the current activity
                finish();
            }
        });
    }
}
