package com.example.tumc_survey;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class EditSurvey extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_survey);

        databaseHelper = new DatabaseHelper(this);

        // Get SUID from Intent
        Intent intent = getIntent();
        String suid = intent.getStringExtra("suid");

        if (suid == null || suid.isEmpty()) {
            Toast.makeText(this, "Invalid SUID provided!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Display the SUID in the layout
        TextView txtViewSUID = findViewById(R.id.txtViewSUID);
        txtViewSUID.setText(suid);

        // Initialize Spinners

        initializeSpinner(R.id.spinnerIsAuthorised, getIsAuthorised());
        initializeSpinner(R.id.spinnerTotalFloors, getTotalFloors());
        initializeSpinner(R.id.spinnerBuildingType, getBuildingTypes());
        initializeSpinner(R.id.spinnerStructureUse, getStructureUse());
        initializeSpinner(R.id.spinnerRelationshipWithOwner, getRelationshipWithOwner());
        initializeSpinner(R.id.spinnerOccupantGender, getGenderOptions());
        initializeSpinner(R.id.spinnerStructureStatus, getStructureStatuses());
        initializeSpinner(R.id.spinnerCommercialActivity, getCommercialActivities());
        initializeSpinner(R.id.spinnerAmenityType, getAmenityTypes());
        initializeSpinner(R.id.spinnerOccupancyType, getOccupancyTypes());

        // Fetch and display survey details
        populateSurveyDetails(suid);

        // Handle update action
        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(v -> updateSurveyDetails(suid));

        // Handle cancel action
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> finish());
    }

    private void initializeSpinner(int spinnerId, List<String> values) {
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    private List<String> getIsAuthorised() {
        return Arrays.asList("Yes", "No","Dont Know","Oters");
    }
    private List<String> getTotalFloors() {
        return Arrays.asList("Only Ground floor","Ground +1","Ground +2","Ground +3","Ground +4","Ground +5","Ground +6","Ground +7","Ground +8","Ground +9","Ground +10", "Others");
    }
    private List<String> getStructureUse() {
        return Arrays.asList("Residential", "Commercial", "Industrial","Mixed","Amenity","Road","Reservation","Others");
    }
    private List<String> getRelationshipWithOwner() {
        return Arrays.asList("Self", "Brother", "Sister","Uncle","Aunt","Father","Mother","Grand Father","Grand Mother","Friend","No Relation","Others");
    }
    private List<String> getBuildingTypes() {
        return Arrays.asList("Building", "Slum", "Other");
    }

    private List<String> getStructureTypes() {
        return Arrays.asList("Concrete", "Steel", "Wood", "Mixed");
    }

    private List<String> getGenderOptions() {
        return Arrays.asList("Male", "Female", "Other");
    }

    private List<String> getStructureStatuses() {
        return Arrays.asList("Vacant Land", "Authorised","Unauthorised","Others");
    }

    private List<String> getCommercialActivities() {
        return Arrays.asList("Shop", "Clinic", "Library","Hair Salon","Cafe","Others");
    }

    private List<String> getAmenityTypes() {
        return Arrays.asList("Religious", "Educational", "Health Care","Social","Recreational","Others");
    }

    private List<String> getOccupancyTypes() {
        return Arrays.asList("Owner","Occupant", "Tenant","Leasee","Pagadi_Owner","Others");
    }

    private void populateSurveyDetails(String suid) {
        Cursor cursor = databaseHelper.getSurveyDetailsBySUID(suid);

        if (cursor != null && cursor.moveToFirst()) {
            // Populate all fields
            ((TextView) findViewById(R.id.txtiewProjectDetails)).setText(cursor.getString(cursor.getColumnIndex("project_name")));
            ((TextView) findViewById(R.id.txtviewStructureType)).setText(cursor.getString(cursor.getColumnIndex("structure_type")));
            ((TextView) findViewById(R.id.txtviewStructureNo)).setText(cursor.getString(cursor.getColumnIndex("structure_no")));
            ((TextView) findViewById(R.id.txtviewSubStructureNo)).setText(cursor.getString(cursor.getColumnIndex("sub_structure_no")));
            ((TextView) findViewById(R.id.txtviewFloorNo)).setText(cursor.getString(cursor.getColumnIndex("floor_no")));
            ((TextView) findViewById(R.id.etRoomNo)).setText(cursor.getString(cursor.getColumnIndex("room_no")));
            ((TextView) findViewById(R.id.etURP)).setText(cursor.getString(cursor.getColumnIndex("urp_name")));
            ((TextView) findViewById(R.id.etIfOthers_RelationshipWithOwner)).setText(cursor.getString(cursor.getColumnIndex("other_relationship_with_owner")));
            ((TextView) findViewById(R.id.etLongitude)).setText(cursor.getString(cursor.getColumnIndex("latitude")));
            ((TextView) findViewById(R.id.etLatitude)).setText(cursor.getString(cursor.getColumnIndex("longitude")));
            ((EditText) findViewById(R.id.etBuildingName)).setText(cursor.getString(cursor.getColumnIndex("building_name")));
            ((EditText) findViewById(R.id.etRemarks)).setText(cursor.getString(cursor.getColumnIndex("remarks")));
            setSpinnerSelectionByValue(R.id.spinnerTotalFloors, cursor.getString(cursor.getColumnIndex("total_floors")));
            setSpinnerSelectionByValue(R.id.spinnerIsAuthorised, cursor.getString(cursor.getColumnIndex("is_authorised")));
            setSpinnerSelectionByValue(R.id.spinnerRelationshipWithOwner, cursor.getString(cursor.getColumnIndex("relationship_with_owner")));
            setSpinnerSelectionByValue(R.id.spinnerBuildingType, cursor.getString(cursor.getColumnIndex("building_type")));
            setSpinnerSelectionByValue(R.id.spinnerStructureStatus, cursor.getString(cursor.getColumnIndex("structure_use")));
            ((EditText) findViewById(R.id.etOwnerName)).setText(cursor.getString(cursor.getColumnIndex("owner_name")));
            ((EditText) findViewById(R.id.etOccupantName)).setText(cursor.getString(cursor.getColumnIndex("occupant_name")));
            ((EditText) findViewById(R.id.etOccupantSpouseName)).setText(cursor.getString(cursor.getColumnIndex("occupant_spouse_name")));
            ((EditText) findViewById(R.id.etOccupantAge)).setText(cursor.getString(cursor.getColumnIndex("occupant_age")));
            setSpinnerSelectionByValue(R.id.spinnerOccupantGender, cursor.getString(cursor.getColumnIndex("occupant_gender")));
            ((EditText) findViewById(R.id.etNoOfFamilyMembers)).setText(cursor.getString(cursor.getColumnIndex("no_of_family_members")));
            ((EditText) findViewById(R.id.etContactNo)).setText(cursor.getString(cursor.getColumnIndex("contact_no")));
            ((EditText) findViewById(R.id.etAddress)).setText(cursor.getString(cursor.getColumnIndex("address")));
            ((EditText) findViewById(R.id.etCarpetArea)).setText(cursor.getString(cursor.getColumnIndex("carpet_area")));
            setSpinnerSelectionByValue(R.id.spinnerStructureStatus, cursor.getString(cursor.getColumnIndex("structure_status")));
            setSpinnerSelectionByValue(R.id.spinnerCommercialActivity, cursor.getString(cursor.getColumnIndex("commercial_activity")));
            ((EditText) findViewById(R.id.etCommercialActivityName)).setText(cursor.getString(cursor.getColumnIndex("commercial_activity_name")));
            setSpinnerSelectionByValue(R.id.spinnerAmenityType, cursor.getString(cursor.getColumnIndex("amenity_type")));
            ((EditText) findViewById(R.id.etAmenityName)).setText(cursor.getString(cursor.getColumnIndex("amenity_name")));
            setSpinnerSelectionByValue(R.id.spinnerOccupancyType, cursor.getString(cursor.getColumnIndex("occupancy_type")));

            cursor.close();
        } else {
            Toast.makeText(this, "No survey found for this SUID", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSpinnerSelectionByValue(int spinnerId, String value) {
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        int position = adapter.getPosition(value);
        if (position >= 0) {
            spinner.setSelection(position);
        }
    }

    private void updateSurveyDetails(String suid) {
        // Collect updated values from the UI
        String projectDetails = ((TextView) findViewById(R.id.txtiewProjectDetails)).getText().toString();
        String buildingName = ((EditText) findViewById(R.id.etBuildingName)).getText().toString();
        String structureType = ((TextView) findViewById(R.id.txtviewStructureType)).getText().toString();
        String remarks = ((EditText) findViewById(R.id.etRemarks)).getText().toString();
        String buildingType = ((Spinner) findViewById(R.id.spinnerBuildingType)).getSelectedItem().toString();
        String structureUse = ((Spinner) findViewById(R.id.spinnerStructureUse)).getSelectedItem().toString();
        String RelationshipWithOwner = ((Spinner) findViewById(R.id.spinnerRelationshipWithOwner)).getSelectedItem().toString();
        String IsAuth = ((Spinner) findViewById(R.id.spinnerIsAuthorised)).getSelectedItem().toString();
        String TotalFloors = ((Spinner) findViewById(R.id.spinnerTotalFloors)).getSelectedItem().toString();
        String ownerName = ((EditText) findViewById(R.id.etOwnerName)).getText().toString();
        String occupantName = ((EditText) findViewById(R.id.etOccupantName)).getText().toString();
        String occupantSpouseName = ((EditText) findViewById(R.id.etOccupantSpouseName)).getText().toString();
        String occupantAge = ((EditText) findViewById(R.id.etOccupantAge)).getText().toString();
        String occupantGender = ((Spinner) findViewById(R.id.spinnerOccupantGender)).getSelectedItem().toString();
        String noOfFamilyMembers = ((EditText) findViewById(R.id.etNoOfFamilyMembers)).getText().toString();
        String contactNo = ((EditText) findViewById(R.id.etContactNo)).getText().toString();
        String address = ((EditText) findViewById(R.id.etAddress)).getText().toString();
        String carpetArea = ((EditText) findViewById(R.id.etCarpetArea)).getText().toString();
        String structureStatus = ((Spinner) findViewById(R.id.spinnerStructureStatus)).getSelectedItem().toString();
        String commercialActivity = ((Spinner) findViewById(R.id.spinnerCommercialActivity)).getSelectedItem().toString();
        String commercialActivityName = ((EditText) findViewById(R.id.etCommercialActivityName)).getText().toString();
        String amenityType = ((Spinner) findViewById(R.id.spinnerAmenityType)).getSelectedItem().toString();
        String amenityName = ((EditText) findViewById(R.id.etAmenityName)).getText().toString();
        String occupancyType = ((Spinner) findViewById(R.id.spinnerOccupancyType)).getSelectedItem().toString();

//        // Update the database
//        boolean isUpdated = databaseHelper.updateSurveyDetails(suid, projectDetails, buildingName, remarks, buildingType, structureType,
//                ownerName, occupantName, occupantSpouseName, occupantAge, occupantGender, noOfFamilyMembers, contactNo,
//                address, carpetArea, structureStatus, commercialActivity, commercialActivityName, amenityType, amenityName, occupancyType);
//
//        if (isUpdated) {
//            Toast.makeText(this, "Survey details updated successfully", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Failed to update survey details", Toast.LENGTH_SHORT).show();
//        }
    }
}
