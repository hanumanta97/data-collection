package com.example.tumc_survey;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewProjectActivity extends AppCompatActivity {

    // Declare EditTexts and Button
    private EditText etProjectName, etCreatorName, etRTO, etSector, etURP, etURC, etURS, etBlock;
    private Button btnAddProject, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        // Initialize views
        etProjectName = findViewById(R.id.etProjectName);
        etCreatorName = findViewById(R.id.etCreatorName);
        etRTO = findViewById(R.id.etRTO);
        etSector = findViewById(R.id.etSector);
        etURP = findViewById(R.id.etURP);
        etURC = findViewById(R.id.etURC);
        etURS = findViewById(R.id.etURS);
        etBlock = findViewById(R.id.etBlock);
        btnAddProject = findViewById(R.id.btnAddProject);
        btnCancel = findViewById(R.id.btnCancel);

        // Set OnClickListener for Add Project button
        btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProjectData();
            }
        });

        // Set OnClickListener for Cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void saveProjectData() {
        // Retrieve input data
        String projectName = etProjectName.getText().toString().trim();
        String creatorName = etCreatorName.getText().toString().trim();
        String rto = etRTO.getText().toString().trim();
        String sector = etSector.getText().toString().trim();
        String urp = etURP.getText().toString().trim();
        String urc = etURC.getText().toString().trim();
        String urs = etURS.getText().toString().trim();
        String block = etBlock.getText().toString().trim();

        // Validate input fields
        if (projectName.isEmpty() || creatorName.isEmpty() || rto.isEmpty() || sector.isEmpty() ||
                urp.isEmpty() || urc.isEmpty() || urs.isEmpty() || block.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save data into the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        long result = dbHelper.insertProject(projectName, creatorName, rto, sector, urp, urc, urs, block);

        if (result != -1) {
            // Navigate to ProjectDetailsActivity and pass the project ID
            Intent intent = new Intent(NewProjectActivity.this, ProjectDetailsActivity.class);
            intent.putExtra("PROJECT_ID", result); // Pass the project ID
            startActivity(intent);
            finish(); // Close the current activity
        } else {
            Toast.makeText(this, "Failed to save project", Toast.LENGTH_SHORT).show();
        }
    }


    private void clearFields() {
        etProjectName.setText("");
        etCreatorName.setText("");
        etRTO.setText("");
        etSector.setText("");
        etURP.setText("");
        etURC.setText("");
        etURS.setText("");
        etBlock.setText("");
    }
}
