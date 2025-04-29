package com.example.tumc_survey;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddSurveyActivity extends AppCompatActivity {

    private TextView txtviewProjectNo;
    private Spinner spinnerStructureType, spinnerFloorNo, spinnerTotalFloors, spinnerBuildingType,
            spinnerIsAuthorised, spinnerStructureUse, spinnerCommercialActivity, spinnerAmenityType,
            spinnerOccupancyType, spinnerOccupantGender, spinnerRelationshipWithOwner, spinnerStructureStatus;

    private EditText etStructureNo, etSubStructureNo, etRoomNo, etURP, etBuildingName, etCommercialActivityName,
            etAmenityName, etOwnerName, etOccupantName, etOccupantSpouseName, etOccupantAge, etNoOfFamilyMembers,
            etIfOthers_RelationshipWithOwner, etContactNo, etAddress, etRemarks, etCarpetArea, etLongitude, etLatitude;

    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_survey);

        // Initialize TextViews
        txtviewProjectNo = findViewById(R.id.txtiewProjectDetails);

        // Initialize Spinners
        spinnerStructureType = findViewById(R.id.spinnerStructureType);
        spinnerFloorNo = findViewById(R.id.spinnerFloorNo);
        spinnerTotalFloors = findViewById(R.id.spinnerTotalFloors);
        spinnerBuildingType = findViewById(R.id.spinnerBuildingType);
        spinnerIsAuthorised = findViewById(R.id.spinnerIsAuthorised);
        spinnerStructureUse = findViewById(R.id.spinnerStructureUse);
        spinnerCommercialActivity = findViewById(R.id.spinnerCommercialActivity);
        spinnerAmenityType = findViewById(R.id.spinnerAmenityType);
        spinnerOccupancyType = findViewById(R.id.spinnerOccupancyType);
        spinnerOccupantGender = findViewById(R.id.spinnerOccupantGender);
        spinnerRelationshipWithOwner = findViewById(R.id.spinnerRelationshipWithOwner);
        spinnerStructureStatus = findViewById(R.id.spinnerStructureStatus);

        // Initialize EditTexts
        etStructureNo = findViewById(R.id.etStructureNo);
        etSubStructureNo = findViewById(R.id.etSubStructureNo);
        etRoomNo = findViewById(R.id.etRoomNo);
        etURP = findViewById(R.id.etURP);
        etBuildingName = findViewById(R.id.etBuildingName);
        etCommercialActivityName = findViewById(R.id.etCommercialActivityName);
        etAmenityName = findViewById(R.id.etAmenityName);
        etOwnerName = findViewById(R.id.etOwnerName);
        etOccupantName = findViewById(R.id.etOccupantName);
        etOccupantSpouseName = findViewById(R.id.etOccupantSpouseName);
        etOccupantAge = findViewById(R.id.etOccupantAge);
        etNoOfFamilyMembers = findViewById(R.id.etNoOfFamilyMembers);
        etIfOthers_RelationshipWithOwner = findViewById(R.id.etIfOthers_RelationshipWithOwner);
        etContactNo = findViewById(R.id.etContactNo);
        etAddress = findViewById(R.id.etAddress);
        etRemarks = findViewById(R.id.etRemarks);
        etCarpetArea = findViewById(R.id.etCarpetArea);
        etLongitude = findViewById(R.id.etLongitude);
        etLatitude = findViewById(R.id.etLatitude);

        // Populate Spinners
        populateSpinner(spinnerStructureType, Arrays.asList("Authorised Building", "Un-Authorised Building", "Slum", "Temple", "Toilet", "School", "Police Station", "Fire Station", "Hospital", "Bank", "Ward Office", "Others"));
        populateSpinner(spinnerFloorNo, Arrays.asList("G","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "Others"));
        populateSpinner(spinnerTotalFloors, Arrays.asList("Only Ground floor","Ground +1","Ground +2","Ground +3","Ground +4","Ground +5","Ground +6","Ground +7","Ground +8","Ground +9","Ground +10", "Others"));
        populateSpinner(spinnerBuildingType, Arrays.asList("Building", "Slum", "Other"));
        populateSpinner(spinnerIsAuthorised, Arrays.asList("Yes", "No","Dont Know","Oters"));
        populateSpinner(spinnerStructureUse, Arrays.asList("Residential", "Commercial", "Industrial","Mixed","Amenity","Road","Reservation","Others"));
        populateSpinner(spinnerCommercialActivity, Arrays.asList("Shop", "Clinic", "Library","Hair Salon","Cafe","Others"));
        populateSpinner(spinnerAmenityType, Arrays.asList("Religious", "Educational", "Health Care","Social","Recreational","Others"));
        populateSpinner(spinnerOccupancyType, Arrays.asList("Owner","Occupant", "Tenant","Leasee","Pagadi_Owner","Others"));
        populateSpinner(spinnerOccupantGender, Arrays.asList("Male", "Female", "Other"));
        populateSpinner(spinnerRelationshipWithOwner, Arrays.asList("Self", "Brother", "Sister","Uncle","Aunt","Father","Mother","Grand Father","Grand Mother","Friend","No Relation","Others"));
        populateSpinner(spinnerStructureStatus, Arrays.asList("Vacant Land", "Authorised","Unauthorised","Others"));

        // Get the data passed from the intent
        Intent intent = getIntent();
        long projectId = intent.getLongExtra("PROJECT_ID", -1);

        if (projectId != -1) {
            Toast.makeText(this, "ProjectID: " + projectId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Invalid project ID", Toast.LENGTH_SHORT).show();
            finish();
        }
        String projectNo = intent.getStringExtra("PROJECT_NO");
        String projectName = intent.getStringExtra("PROJECT_NAME");

        // Display the data
        if (projectNo != null && projectName != null) {
            txtviewProjectNo.setText(projectNo + "-" + projectName);
        } else {
            Toast.makeText(this, "No project data received", Toast.LENGTH_SHORT).show();
            finish();
        }
        // Set button listener
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                displayValuesInToast();
                displayProjectDetails_for_survey(projectId);
            }

        });
    }

    private void populateSpinner(Spinner spinner, List<String> items) {
        List<String> modifiedItems = new ArrayList<>();
        modifiedItems.add("Select");
        modifiedItems.addAll(items);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, modifiedItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void displayValuesInToast() {
        String data = "Structure Type: " + getSelectedSpinnerValue(spinnerStructureType) + "\n" +
                "Floor No: " + getSelectedSpinnerValue(spinnerFloorNo) + "\n" +
                "Total Floors: " + getSelectedSpinnerValue(spinnerTotalFloors) + "\n" +
                "Building Type: " + getSelectedSpinnerValue(spinnerBuildingType) + "\n" +
                "Is Authorised: " + getSelectedSpinnerValue(spinnerIsAuthorised) + "\n" +
                "Structure Use: " + getSelectedSpinnerValue(spinnerStructureUse) + "\n" +
                "Commercial Activity: " + getSelectedSpinnerValue(spinnerCommercialActivity) + "\n" +
                "Amenity Type: " + getSelectedSpinnerValue(spinnerAmenityType) + "\n" +
                "Occupancy Type: " + getSelectedSpinnerValue(spinnerOccupancyType) + "\n" +
                "Occupant Gender: " + getSelectedSpinnerValue(spinnerOccupantGender) + "\n" +
                "Relationship with Owner: " + getSelectedSpinnerValue(spinnerRelationshipWithOwner) + "\n" +
                "Structure Status: " + getSelectedSpinnerValue(spinnerStructureStatus) + "\n" +
                "Structure No: " + etStructureNo.getText().toString() + "\n" +
                "Sub-Structure No: " + etSubStructureNo.getText().toString() + "\n" +
                "Room No: " + etRoomNo.getText().toString() + "\n" +
                "URP: " + etURP.getText().toString() + "\n" +
                "Building Name: " + etBuildingName.getText().toString() + "\n" +
                "Commercial Activity Name: " + etCommercialActivityName.getText().toString() + "\n" +
                "Amenity Name: " + etAmenityName.getText().toString() + "\n" +
                "Owner Name: " + etOwnerName.getText().toString() + "\n" +
                "Occupant Name: " + etOccupantName.getText().toString() + "\n" +
                "Occupant Spouse Name: " + etOccupantSpouseName.getText().toString() + "\n" +
                "Occupant Age: " + etOccupantAge.getText().toString() + "\n" +
                "No. of Family Members: " + etNoOfFamilyMembers.getText().toString() + "\n" +
                "If Others (Relationship with Owner): " + etIfOthers_RelationshipWithOwner.getText().toString() + "\n" +
                "Contact No: " + etContactNo.getText().toString() + "\n" +
                "Address: " + etAddress.getText().toString() + "\n" +
                "Remarks: " + etRemarks.getText().toString() + "\n" +
                "Carpet Area: " + etCarpetArea.getText().toString() + "\n" +
                "Longitude: " + etLongitude.getText().toString() + "\n" +
                "Latitude: " + etLatitude.getText().toString();


        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
    private void displayProjectDetails_for_survey(long projectId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getProjectById(projectId);

        if (cursor != null && cursor.moveToFirst()) {
            // Extract data from the cursor and display it
            String projectName = cursor.getString(1);
            String creatorName = cursor.getString(2);
            String rto = cursor.getString(3);
            String sector = cursor.getString(4);
            String urp = cursor.getString(5);
            String urc = cursor.getString(6);
            String urs = cursor.getString(7);
            String block = cursor.getString(8);

            cursor.close();
            // Spinner values
            String structureType = getSelectedSpinnerValue(spinnerStructureType);
            String floorNo = getSelectedSpinnerValue(spinnerFloorNo);
            String totalFloors = getSelectedSpinnerValue(spinnerTotalFloors);
            String buildingType = getSelectedSpinnerValue(spinnerBuildingType);
            String isAuthorised = getSelectedSpinnerValue(spinnerIsAuthorised);
            String structureUse = getSelectedSpinnerValue(spinnerStructureUse);
            String commercialActivity = getSelectedSpinnerValue(spinnerCommercialActivity);
            String amenityType = getSelectedSpinnerValue(spinnerAmenityType);
            String occupancyType = getSelectedSpinnerValue(spinnerOccupancyType);
            String occupantGender = getSelectedSpinnerValue(spinnerOccupantGender);
            String relationshipWithOwner = getSelectedSpinnerValue(spinnerRelationshipWithOwner);
            String structureStatus = getSelectedSpinnerValue(spinnerStructureStatus);
            // EditText values
            String structureNo = etStructureNo.getText().toString();
            String subStructureNo = etSubStructureNo.getText().toString();
            String roomNo = etRoomNo.getText().toString();
            String urp_name = etURP.getText().toString();
            String buildingName = etBuildingName.getText().toString();
            String commercialActivityName = etCommercialActivityName.getText().toString();
            String amenityName = etAmenityName.getText().toString();
            String ownerName = etOwnerName.getText().toString();
            String occupantName = etOccupantName.getText().toString();
            String occupantSpouseName = etOccupantSpouseName.getText().toString();
            String occupantAge = etOccupantAge.getText().toString();
            String noOfFamilyMembers = etNoOfFamilyMembers.getText().toString();
            String ifOthersRelationshipWithOwner = etIfOthers_RelationshipWithOwner.getText().toString();
            String contactNo = etContactNo.getText().toString();
            String address = etAddress.getText().toString();
            String remarks = etRemarks.getText().toString();
            // Handle carpet area input
            String carpetAreaString = etCarpetArea.getText().toString();
            int carpetArea = 0; // Default value
            if (!carpetAreaString.isEmpty()) {
                try {
                    carpetArea = Integer.parseInt(carpetAreaString);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Please enter a valid number for carpet area", Toast.LENGTH_SHORT).show();
                }
            }
            String longitude = etLongitude.getText().toString();
            String latitude = etLatitude.getText().toString();
            String get_structure = structureType.substring(0, 1).toUpperCase();
            String suid=rto+"/"+urp+"/"+urc+"/"+block+"_"+get_structure+"/"+floorNo+"/"+structureNo+"_"+subStructureNo;
            Toast.makeText(this, suid, Toast.LENGTH_LONG).show();

            DatabaseHelper dbHelper1 = new DatabaseHelper(this);
            long result = dbHelper1.insertSurveyDetails(projectName, creatorName,rto, sector,urp, urc, urs, block, structureNo,subStructureNo, suid, urp_name, buildingType,
                    buildingName, isAuthorised, floorNo, totalFloors,
                   structureUse, structureType, commercialActivity,
                    commercialActivityName, amenityType, amenityName,
                    occupancyType, ownerName, occupantName, occupantSpouseName,
                    occupantGender, occupantAge, relationshipWithOwner, roomNo,
                    noOfFamilyMembers, ifOthersRelationshipWithOwner, contactNo,carpetArea, address,  structureStatus, remarks,
                    latitude, longitude);

            if (result != -1) {
                // Navigate to ProjectDetailsActivity and pass the suid
                Intent intent = new Intent(AddSurveyActivity.this, DisplaySurveyActivity.class);
                intent.putExtra("suid", suid); // Pass the suid
                intent.putExtra("PROJECT_ID", projectId);
                intent.putExtra("PROJECT_NAME", projectName);
                startActivity(intent);
                finish(); // Close the current activity
            } else {
                Toast.makeText(this, "Failed to save project", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Project not found", Toast.LENGTH_SHORT).show();
        }


    }

    private String getSelectedSpinnerValue(Spinner spinner) {
        return spinner.getSelectedItem().toString();
    }
}
