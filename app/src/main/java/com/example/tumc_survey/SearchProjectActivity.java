package com.example.tumc_survey;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchProjectActivity extends Activity {

    private ListView lvSearchResults;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_project); // Ensure this matches your XML layout file name

        // Initialize the ListView and DatabaseHelper
        lvSearchResults = findViewById(R.id.lvSearchResults);
        databaseHelper = new DatabaseHelper(this);

        // Load data from the database
        loadProjectList();

        // Set click listener for ListView
        lvSearchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item as a string
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Show a Toast for the selected item
                Toast.makeText(SearchProjectActivity.this, "Selected item: " + selectedItem, Toast.LENGTH_SHORT).show();

                // Validate the selected item
                // Validate the selected item
                if (selectedItem != null && selectedItem.contains("|")) {
                    try {
                        // Extract project ID from the string (before the '|')
                        String[] parts = selectedItem.split("\\|");

                        if (parts.length > 1) {
                            String idPart = parts[0].trim(); // Extract and trim the ID part

                            // Validate if the ID part is numeric
                            if (idPart.matches("\\d+")) {
                                long projectId = Integer.parseInt(idPart);

                                // Show a Toast for the project ID
                                Toast.makeText(SearchProjectActivity.this, "ProjectID: " + projectId, Toast.LENGTH_SHORT).show();

                                // Pass the project ID to the next activity
                                Intent intent = new Intent(SearchProjectActivity.this, ProjectDetailsActivity.class);
                                intent.putExtra("PROJECT_ID", projectId);
                                startActivity(intent);

                            } else {
                                // Show a Toast if the project ID is non-numeric
                                Toast.makeText(SearchProjectActivity.this, "Invalid project ID format.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Show a Toast if the data is malformed
                            Toast.makeText(SearchProjectActivity.this, "Malformed project data.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        // Show a Toast if there’s an error parsing the project ID
                        Toast.makeText(SearchProjectActivity.this, "Error parsing project ID.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Show a Toast if the selected item is invalid
                    Toast.makeText(SearchProjectActivity.this, "Invalid project selected.", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }

    private void loadProjectList() {
        Cursor cursor = databaseHelper.getAllProjects();
        ArrayList<String> projectList = new ArrayList<>();

        // Check if the database contains data
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Get the project ID and name from the cursor
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String projectName = cursor.getString(cursor.getColumnIndexOrThrow("project_name"));

                // Ensure the project ID and name are valid before adding them to the list
                if (projectName != null && !projectName.trim().isEmpty()) {
                    // Format the string as "ID | ProjectName (0 survey)"
                    projectList.add(id + " | " + projectName + " (0 survey)");
                }
            } while (cursor.moveToNext());

            cursor.close();
        } else {
            Toast.makeText(this, "No projects found.", Toast.LENGTH_SHORT).show();
        }

        // Check if the project list is empty
        if (projectList.isEmpty()) {
            Toast.makeText(this, "Project list is empty.", Toast.LENGTH_SHORT).show();
        }

        // Use an ArrayAdapter to display the data in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, projectList);
        lvSearchResults.setAdapter(adapter);
    }
}
