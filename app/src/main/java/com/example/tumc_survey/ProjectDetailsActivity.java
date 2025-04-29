package com.example.tumc_survey;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProjectDetailsActivity extends AppCompatActivity {

    private TextView txtviewProjectNo, txtiewProjectDetails, txtviewCreatorName, txtviewRTO,
            txtviewSector, txtviewURIP, txtviewURC, txtviewURS, txtviewBlock;
    private Button btnAddSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        // Initialize TextViews
        txtviewProjectNo = findViewById(R.id.txtviewProjectNo);
        txtiewProjectDetails = findViewById(R.id.txtiewProjectDetails);
        txtviewCreatorName = findViewById(R.id.txtviewCreatorName);
        txtviewRTO = findViewById(R.id.txtviewRTO);
        txtviewSector = findViewById(R.id.txtviewSector);
        txtviewURIP = findViewById(R.id.txtviewURIP);
        txtviewURC = findViewById(R.id.txtviewURC);
        txtviewURS = findViewById(R.id.txtviewURS);
        txtviewBlock = findViewById(R.id.txtviewBlock);
        btnAddSurvey = findViewById(R.id.btnAddSurvey);

        // Get the project ID from the intent
        Intent intent = getIntent();
        long projectId = intent.getLongExtra("PROJECT_ID", -1);
        Toast.makeText(this, "ProjectID: " + projectId, Toast.LENGTH_SHORT).show();
        if (projectId != -1) {
            displayProjectDetails(projectId);
        } else {
            Toast.makeText(this, "Invalid project ID", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Set up click listener for the Add Survey button
        btnAddSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve project number and name

                String projectNo = txtviewProjectNo.getText().toString();
                String projectName = txtiewProjectDetails.getText().toString();

                // Create an intent to start AddSurveyActivity
                Intent addSurveyIntent = new Intent(ProjectDetailsActivity.this, AddSurveyActivity.class);

                // Put extras
                addSurveyIntent.putExtra("PROJECT_ID", projectId);
                addSurveyIntent.putExtra("PROJECT_NO", projectNo);
                addSurveyIntent.putExtra("PROJECT_NAME", projectName);

                // Start the activity
                startActivity(addSurveyIntent);
            }
        });
    }

    private void displayProjectDetails(long projectId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getProjectById(projectId);

        if (cursor != null && cursor.moveToFirst()) {
            // Extract data from the cursor and display it
            txtviewProjectNo.setText(String.valueOf(cursor.getLong(0))); // Project ID
            txtiewProjectDetails.setText(cursor.getString(1)); // Project Name
            txtviewCreatorName.setText(cursor.getString(2)); // Creator Name
            txtviewRTO.setText(cursor.getString(3)); // RTO
            txtviewSector.setText(cursor.getString(4)); // Sector
            txtviewURIP.setText(cursor.getString(5)); // URP
            txtviewURC.setText(cursor.getString(6)); // URC
            txtviewURS.setText(cursor.getString(7)); // URS
            txtviewBlock.setText(cursor.getString(8)); // Block

            cursor.close();
        } else {
            Toast.makeText(this, "Project not found", Toast.LENGTH_SHORT).show();
        }
    }
}
