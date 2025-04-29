package com.example.tumc_survey;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplaySurveyActivity extends AppCompatActivity {

    // Declare all TextViews
    private TextView txtviewProjectNo, txtviewSUID, txtviewLatitude, txtviewLongitude,
            txtviewStructureNo, txtviewSubStructureNo, txtviewFloorNo, txtviewTotalNoOfFloor,
            txtviewRoomNo, txtviewURPName, txtviewBuildingType, txtviewBuildingName, txtviewIsAuthorised,
            txtviewStructureUse, txtviewStructureType, txtviewCommercialActivityType, txtviewCommercialActivityName,
            txtviewAmenityType, txtviewAmenityName, txtviewOccupancyType, txtviewOwnerName, txtviewOccupantName,
            txtviewOccupantSpouseName, txtviewOccupantGender, txtviewOccupantAge, txtviewNoOfFamilyMembers,
            txtviewRelationshipWithOwner, txtviewRelationshipWithOwnerOthers, txtviewContactNo, txtviewAddress,
            txtviewCarpetArea, txtviewStructureStatus, txtviewRemark;
    private Button btneditsurvey , btnadddocs ,btndisplaydocs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_survey);

        // Initialize all TextViews
        initializeTextViews();

        // Get data from Intent
        Intent intent = getIntent();
        String suid = intent.getStringExtra("suid");

        if (suid == null || suid.isEmpty()) {
            Toast.makeText(this, "Invalid SUID provided!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Query the database for survey details by SUID
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getSurveyDetailsBySUID(suid);

        if (cursor != null && cursor.moveToFirst()) {
            // Populate data into TextViews
            populateData(cursor);
            cursor.close();
        } else {
            Toast.makeText(this, "No survey details found for SUID: " + suid, Toast.LENGTH_SHORT).show();
        }
        btneditsurvey = findViewById(R.id.btn_EditSurveyDetails);
        btnadddocs= findViewById(R.id.btnAddDocument);
        btndisplaydocs= findViewById(R.id.btnDisplayDocs);
        btneditsurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editSurveyIntent = new Intent(DisplaySurveyActivity.this, EditSurvey.class);

                // Put extras
                editSurveyIntent.putExtra("suid", suid);

                // Start the activity
                startActivity(editSurveyIntent);
            }

        });
        btnadddocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplaySurveyActivity.this, AddDocs.class);


                startActivity(intent);
            }

        });
        btndisplaydocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplaySurveyActivity.this, DisplayDocs.class);


                startActivity(intent);
            }

        });
    }

    private void initializeTextViews() {
        txtviewProjectNo = findViewById(R.id.txtiewProjectDetails);
        txtviewSUID = findViewById(R.id.txtviewSUID);
        txtviewLatitude = findViewById(R.id.txtviewLatitude);
        txtviewLongitude = findViewById(R.id.txtviewLongitude);
        txtviewStructureNo = findViewById(R.id.txtviewStructureNo);
        txtviewSubStructureNo = findViewById(R.id.txtviewSubStructureNo);
        txtviewFloorNo = findViewById(R.id.txtviewFloorNo);
        txtviewTotalNoOfFloor = findViewById(R.id.txtviewTotalNoOfFloor);
        txtviewRoomNo = findViewById(R.id.txtviewRoomNo);
        txtviewURPName = findViewById(R.id.txtviewURPName);
        txtviewBuildingType = findViewById(R.id.txtviewBuildingType);
        txtviewBuildingName = findViewById(R.id.txtviewBuildingName);
        txtviewIsAuthorised = findViewById(R.id.txtviewIsAuthorised);
        txtviewStructureUse = findViewById(R.id.txtviewStructureUse);
        txtviewStructureType = findViewById(R.id.txtviewStructureType);
        txtviewCommercialActivityType = findViewById(R.id.txtviewCommercialActivityType);
        txtviewCommercialActivityName = findViewById(R.id.txtviewCommercialActivityName);
        txtviewAmenityType = findViewById(R.id.txtviewAmenityType);
        txtviewAmenityName = findViewById(R.id.txtviewAmenityName);
        txtviewOccupancyType = findViewById(R.id.txtviewOccupationType);
        txtviewOwnerName = findViewById(R.id.txtviewOwnerName);
        txtviewOccupantName = findViewById(R.id.txtviewOccupantName);
        txtviewOccupantSpouseName = findViewById(R.id.txtviewOccupantSpouseName);
        txtviewOccupantGender = findViewById(R.id.txtviewOccupantGender);
        txtviewOccupantAge = findViewById(R.id.txtviewOccupantAge);
        txtviewNoOfFamilyMembers = findViewById(R.id.txtviewOccupantNoOfFamilyMembers);
        txtviewRelationshipWithOwner = findViewById(R.id.txtviewRelationshipWithOwner);
        txtviewRelationshipWithOwnerOthers = findViewById(R.id.txtviewRelationshipWithOwnerOthers);
        txtviewContactNo = findViewById(R.id.txtviewContactNo);
        txtviewAddress = findViewById(R.id.txtviewAddress);
        txtviewCarpetArea = findViewById(R.id.txtviewCarpetArea);
        txtviewStructureStatus = findViewById(R.id.txtviewStructureStatus);
        txtviewRemark = findViewById(R.id.txtviewRemark);
    }

    private void populateData(Cursor cursor) {
        txtviewProjectNo.setText(cursor.getString(cursor.getColumnIndex("project_name")));
        txtviewSUID.setText(cursor.getString(cursor.getColumnIndex("suid")));
        txtviewLatitude.setText(cursor.getString(cursor.getColumnIndex("latitude")));
        txtviewLongitude.setText(cursor.getString(cursor.getColumnIndex("longitude")));
        txtviewStructureNo.setText(cursor.getString(cursor.getColumnIndex("structure_no")));
        txtviewSubStructureNo.setText(cursor.getString(cursor.getColumnIndex("sub_structure_no")));
        txtviewFloorNo.setText(cursor.getString(cursor.getColumnIndex("floor_no")));
        txtviewTotalNoOfFloor.setText(cursor.getString(cursor.getColumnIndex("total_floors")));
        txtviewRoomNo.setText(cursor.getString(cursor.getColumnIndex("room_no")));
        txtviewURPName.setText(cursor.getString(cursor.getColumnIndex("urp_name")));
        txtviewBuildingType.setText(cursor.getString(cursor.getColumnIndex("building_type")));
        txtviewBuildingName.setText(cursor.getString(cursor.getColumnIndex("building_name")));
        txtviewIsAuthorised.setText(cursor.getString(cursor.getColumnIndex("is_authorised")));
        txtviewStructureUse.setText(cursor.getString(cursor.getColumnIndex("structure_use")));
        txtviewStructureType.setText(cursor.getString(cursor.getColumnIndex("structure_type")));
        txtviewCommercialActivityType.setText(cursor.getString(cursor.getColumnIndex("commercial_activity")));
        txtviewCommercialActivityName.setText(cursor.getString(cursor.getColumnIndex("commercial_activity_name")));
        txtviewAmenityType.setText(cursor.getString(cursor.getColumnIndex("amenity_type")));
        txtviewAmenityName.setText(cursor.getString(cursor.getColumnIndex("amenity_name")));
        txtviewOccupancyType.setText(cursor.getString(cursor.getColumnIndex("occupancy_type")));
        txtviewOwnerName.setText(cursor.getString(cursor.getColumnIndex("owner_name")));
        txtviewOccupantName.setText(cursor.getString(cursor.getColumnIndex("occupant_name")));
        txtviewOccupantSpouseName.setText(cursor.getString(cursor.getColumnIndex("occupant_spouse_name")));
        txtviewOccupantGender.setText(cursor.getString(cursor.getColumnIndex("occupant_gender")));
        txtviewOccupantAge.setText(cursor.getString(cursor.getColumnIndex("occupant_age")));
        txtviewNoOfFamilyMembers.setText(cursor.getString(cursor.getColumnIndex("no_of_family_members")));
        txtviewRelationshipWithOwner.setText(cursor.getString(cursor.getColumnIndex("relationship_with_owner")));
        txtviewRelationshipWithOwnerOthers.setText(cursor.getString(cursor.getColumnIndex("other_relationship_with_owner")));
        txtviewContactNo.setText(cursor.getString(cursor.getColumnIndex("contact_no")));
        txtviewAddress.setText(cursor.getString(cursor.getColumnIndex("address")));
        txtviewCarpetArea.setText(cursor.getString(cursor.getColumnIndex("carpet_area")));
        txtviewStructureStatus.setText(cursor.getString(cursor.getColumnIndex("structure_status")));
        txtviewRemark.setText(cursor.getString(cursor.getColumnIndex("remarks")));
    }
}
