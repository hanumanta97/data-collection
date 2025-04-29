package com.example.tumc_survey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    // Database name and version
    private static final String DATABASE_NAME = "survey_data.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_PROJECTLIST = "projectlist";
    private static final String TABLE_SURVEY_DETAILS = "survey_details";

    // Columns for project list table
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PROJECT_NAME = "project_name";
    private static final String COLUMN_CREATOR_NAME = "creator_name";
    private static final String COLUMN_RTO = "rto";
    private static final String COLUMN_SECTOR = "sector";
    private static final String COLUMN_URP = "urp";
    private static final String COLUMN_URC = "urc";
    private static final String COLUMN_URS = "urs";
    private static final String COLUMN_BLOCK = "block";

    // Columns for survey details table
    private static final String COLUMN_STRUCTURE_TYPE = "structure_type";
    private static final String COLUMN_FLOOR_NO = "floor_no";
    private static final String COLUMN_TOTAL_FLOORS = "total_floors";
    private static final String COLUMN_BUILDING_TYPE = "building_type";
    private static final String COLUMN_IS_AUTHORISED = "is_authorised";
    private static final String COLUMN_STRUCTURE_USE = "structure_use";
    private static final String COLUMN_COMMERCIAL_ACTIVITY_TYPE = "commercial_activity";
    private static final String COLUMN_AMENITY_TYPE = "amenity_type";
    private static final String COLUMN_OCCUPANCY_TYPE = "occupancy_type";
    private static final String COLUMN_OCCUPANT_GENDER = "occupant_gender";
    private static final String COLUMN_RELATIONSHIP_WITH_OWNER = "relationship_with_owner";
    private static final String COLUMN_STRUCTURE_STATUS = "structure_status";
    private static final String COLUMN_STRUCTURE_NO = "structure_no";
    private static final String COLUMN_SUID = "suid";
    private static final String COLUMN_URP_NAME = "urp_name";
    private static final String COLUMN_SUB_STRUCTURE_NO = "sub_structure_no";
    private static final String COLUMN_ROOM_NO = "room_no";
    private static final String COLUMN_BUILDING_NAME = "building_name";
    private static final String COLUMN_COMMERCIAL_ACTIVITY_NAME = "commercial_activity_name";
    private static final String COLUMN_AMENITY_NAME = "amenity_name";
    private static final String COLUMN_OWNER_NAME = "owner_name";
    private static final String COLUMN_OCCUPANT_NAME = "occupant_name";
    private static final String COLUMN_OCCUPANT_SPOUSE_NAME = "occupant_spouse_name";
    private static final String COLUMN_OCCUPANT_AGE = "occupant_age";
    private static final String COLUMN_NO_OF_FAMILY_MEMBERS = "no_of_family_members";
    private static final String COLUMN_OTHER_RELATIONSHIP_WITH_OWNER = "other_relationship_with_owner";
    private static final String COLUMN_CONTACT_NO = "contact_no";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CARPET_AREA = "carpet_area";
    private static final String COLUMN_TAX_RECEIPT = "tax_receipt";
    private static final String COLUMN_PROPERTY_CARD = "property_card";
    private static final String COLUMN_ELECTRICITY_BILL = "electricity_bill";
    private static final String COLUMN_WATER_BILL = "water_bill";
    private static final String COLUMN_PROPERTY_CARD_1 = "property_card_1";
    private static final String COLUMN_EXTRACT_7_12 = "extract_7_12";
    private static final String COLUMN_GUMASTA_LICENSE = "gumasta_license";
    private static final String COLUMN_INCORPORATION_CERTIFICATE = "incorporation_certificate";
    private static final String COLUMN_REGISTRATION_CERTIFICATE = "registration_certificate";
    private static final String COLUMN_OWNER_RATION_CARD = "owner_ration_card";
    private static final String COLUMN_OWNER_VOTER_ID = "owner_voter_id";
    private static final String COLUMN_OWNER_PASSPORT = "owner_passport";
    private static final String COLUMN_OWNER_DRIVING_LICENSE = "owner_driving_license";
    private static final String COLUMN_OCCUPANT_AADHAR_CARD = "occupant_aadhar_card";
    private static final String COLUMN_OCCUPANT_PAN_CARD = "occupant_pan_card";
    private static final String COLUMN_OCCUPANT_RATION_CARD = "occupant_ration_card";
    private static final String COLUMN_OCCUPANT_VOTER_ID = "occupant_voter_id";
    private static final String COLUMN_OCCUPANT_PASSPORT = "occupant_passport";
    private static final String COLUMN_OCCUPANT_DRIVING_LICENSE = "occupant_driving_license";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";
    private static final String COLUMN_FINGER_PRINT_RIGHT = "finger_print_right";
    private static final String COLUMN_FINGER_PRINT_LEFT = "finger_print_left";

    // SQL query to create the project list table
    private static final String CREATE_TABLE_PROJECTLIST = "CREATE TABLE " + TABLE_PROJECTLIST + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PROJECT_NAME + " TEXT, "
            + COLUMN_CREATOR_NAME + " TEXT, "
            + COLUMN_RTO + " TEXT, "
            + COLUMN_SECTOR + " TEXT, "
            + COLUMN_URP + " TEXT, "
            + COLUMN_URC + " TEXT, "
            + COLUMN_URS + " TEXT, "
            + COLUMN_BLOCK + " TEXT);";

    // SQL query to create the survey details table
    private static final String CREATE_TABLE_SURVEY_DETAILS = "CREATE TABLE " + TABLE_SURVEY_DETAILS + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PROJECT_NAME + " TEXT, "
            + COLUMN_CREATOR_NAME + " TEXT, "
            + COLUMN_RTO + " TEXT, "
            + COLUMN_SECTOR + " TEXT, "
            + COLUMN_URP + " TEXT, "
            + COLUMN_URC + " TEXT, "
            + COLUMN_URS + " TEXT, "
            + COLUMN_BLOCK + " TEXT,"
            + COLUMN_STRUCTURE_NO + " TEXT, "
            + COLUMN_SUB_STRUCTURE_NO + " TEXT, "
            + COLUMN_SUID + " TEXT, "
            + COLUMN_URP_NAME + " TEXT, "
            + COLUMN_BUILDING_TYPE + " TEXT, "
            + COLUMN_BUILDING_NAME + " TEXT, "
            + COLUMN_IS_AUTHORISED + " TEXT, "
            + COLUMN_FLOOR_NO + " TEXT, "
            + COLUMN_TOTAL_FLOORS + " TEXT, "
            + COLUMN_STRUCTURE_USE + " TEXT, "
            + COLUMN_STRUCTURE_TYPE + " TEXT, "
            + COLUMN_COMMERCIAL_ACTIVITY_TYPE + " TEXT, "
            + COLUMN_COMMERCIAL_ACTIVITY_NAME + " TEXT, "
            + COLUMN_AMENITY_TYPE + " TEXT, "
            + COLUMN_AMENITY_NAME + " TEXT, "
            + COLUMN_OCCUPANCY_TYPE + " TEXT, "
            + COLUMN_OWNER_NAME + " TEXT, "
            + COLUMN_OCCUPANT_NAME + " TEXT, "
            + COLUMN_OCCUPANT_SPOUSE_NAME + " TEXT, "
            + COLUMN_OCCUPANT_GENDER + " TEXT, "
            + COLUMN_OCCUPANT_AGE + " TEXT, "
            + COLUMN_RELATIONSHIP_WITH_OWNER + " TEXT, "
            + COLUMN_ROOM_NO + " TEXT, "
            + COLUMN_NO_OF_FAMILY_MEMBERS + " TEXT, "
            + COLUMN_OTHER_RELATIONSHIP_WITH_OWNER + " TEXT, "
            + COLUMN_CONTACT_NO + " TEXT, "
            + COLUMN_CARPET_AREA + " REAL, "
            + COLUMN_ADDRESS + " TEXT, "
            + COLUMN_TAX_RECEIPT + " TEXT, "
            + COLUMN_PROPERTY_CARD + " TEXT, "
            + COLUMN_ELECTRICITY_BILL + " TEXT, "
            + COLUMN_WATER_BILL + " TEXT, "
            + COLUMN_PROPERTY_CARD_1 + " TEXT, "
            + COLUMN_EXTRACT_7_12 + " TEXT, "
            + COLUMN_GUMASTA_LICENSE + " TEXT, "
            + COLUMN_INCORPORATION_CERTIFICATE + " TEXT, "
            + COLUMN_REGISTRATION_CERTIFICATE + " TEXT, "
            + COLUMN_OWNER_RATION_CARD + " TEXT, "
            + COLUMN_OWNER_VOTER_ID + " TEXT, "
            + COLUMN_OWNER_PASSPORT + " TEXT, "
            + COLUMN_OWNER_DRIVING_LICENSE + " TEXT, "
            + COLUMN_OCCUPANT_AADHAR_CARD + " TEXT, "
            + COLUMN_OCCUPANT_PAN_CARD + " TEXT, "
            + COLUMN_OCCUPANT_RATION_CARD + " TEXT, "
            + COLUMN_OCCUPANT_VOTER_ID + " TEXT, "
            + COLUMN_OCCUPANT_PASSPORT + " TEXT, "
            + COLUMN_OCCUPANT_DRIVING_LICENSE + " TEXT, "
            + COLUMN_STRUCTURE_STATUS + " TEXT, "
            + COLUMN_REMARKS + " TEXT, "
            + COLUMN_LATITUDE + " TEXT, "
            + COLUMN_LONGITUDE + " TEXT, "
            + COLUMN_FINGER_PRINT_RIGHT + " TEXT, "
            + COLUMN_FINGER_PRINT_LEFT + " TEXT); ";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PROJECTLIST);
        db.execSQL(CREATE_TABLE_SURVEY_DETAILS);
    }

    // Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SURVEY_DETAILS);
        onCreate(db);
    }

    // Insert a new project
    public long insertProject(String projectName, String creatorName, String rto, String sector,
                              String urp, String urc, String urs, String block) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PROJECT_NAME, projectName);
        values.put(COLUMN_CREATOR_NAME, creatorName);
        values.put(COLUMN_RTO, rto);
        values.put(COLUMN_SECTOR, sector);
        values.put(COLUMN_URP, urp);
        values.put(COLUMN_URC, urc);
        values.put(COLUMN_URS, urs);
        values.put(COLUMN_BLOCK, block);

        long result = db.insert(TABLE_PROJECTLIST, null, values);
        db.close();
        return result;
    }

    // Get a project by its ID
    public Cursor getProjectById(long projectId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PROJECTLIST + " WHERE " + COLUMN_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(projectId)});
    }

    // Get all projects
    public Cursor getAllProjects() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PROJECTLIST;
        return db.rawQuery(query, null);
    }

    // Insert survey details
    public long insertSurveyDetails(String projectName, String creatorName, String rto, String sector,
                                    String urp, String urc, String urs, String block, String structureNo,
                                    String subStructureNo, String suid, String urpName, String buildingType,
                                    String buildingName, String isAuthorised, String floorNo, String totalFloors,
                                    String structureUse, String structureType, String commercialActivityType,
                                    String commercialActivityName, String amenityType, String amenityName,
                                    String occupancyType, String ownerName, String occupantName, String occupantSpouseName,
                                    String occupantGender, String occupantAge, String relationshipWithOwner, String roomNo,
                                    String noOfFamilyMembers, String otherRelationshipWithOwner, String contactNo,
                                    float carpetArea, String address,  String structureStatus, String remarks,
                                    String latitude, String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PROJECT_NAME, projectName);
        values.put(COLUMN_CREATOR_NAME, creatorName);
        values.put(COLUMN_RTO, rto);
        values.put(COLUMN_SECTOR, sector);
        values.put(COLUMN_URP, urp);
        values.put(COLUMN_URC, urc);
        values.put(COLUMN_URS, urs);
        values.put(COLUMN_BLOCK, block);
        values.put(COLUMN_STRUCTURE_NO, structureNo);
        values.put(COLUMN_SUB_STRUCTURE_NO, subStructureNo);
        values.put(COLUMN_SUID, suid);
        values.put(COLUMN_URP_NAME, urpName);
        values.put(COLUMN_BUILDING_TYPE, buildingType);
        values.put(COLUMN_BUILDING_NAME, buildingName);
        values.put(COLUMN_IS_AUTHORISED, isAuthorised);
        values.put(COLUMN_FLOOR_NO, floorNo);
        values.put(COLUMN_TOTAL_FLOORS, totalFloors);
        values.put(COLUMN_STRUCTURE_USE, structureUse);
        values.put(COLUMN_STRUCTURE_TYPE, structureType);
        values.put(COLUMN_COMMERCIAL_ACTIVITY_TYPE, commercialActivityType);
        values.put(COLUMN_COMMERCIAL_ACTIVITY_NAME, commercialActivityName);
        values.put(COLUMN_AMENITY_TYPE, amenityType);
        values.put(COLUMN_AMENITY_NAME, amenityName);
        values.put(COLUMN_OCCUPANCY_TYPE, occupancyType);
        values.put(COLUMN_OWNER_NAME, ownerName);
        values.put(COLUMN_OCCUPANT_NAME, occupantName);
        values.put(COLUMN_OCCUPANT_SPOUSE_NAME, occupantSpouseName);
        values.put(COLUMN_OCCUPANT_GENDER, occupantGender);
        values.put(COLUMN_OCCUPANT_AGE, occupantAge);
        values.put(COLUMN_RELATIONSHIP_WITH_OWNER, relationshipWithOwner);
        values.put(COLUMN_ROOM_NO, roomNo);
        values.put(COLUMN_NO_OF_FAMILY_MEMBERS, noOfFamilyMembers);
        values.put(COLUMN_OTHER_RELATIONSHIP_WITH_OWNER, otherRelationshipWithOwner);
        values.put(COLUMN_CONTACT_NO, contactNo);
        values.put(COLUMN_CARPET_AREA, carpetArea);
        values.put(COLUMN_ADDRESS, address);
//        values.put(COLUMN_TAX_RECEIPT, taxReceipt);
//        values.put(COLUMN_PROPERTY_CARD, propertyCard);
//        values.put(COLUMN_ELECTRICITY_BILL, electricityBill);
//        values.put(COLUMN_WATER_BILL, waterBill);
//        values.put(COLUMN_PROPERTY_CARD_1, propertyCard1);
//        values.put(COLUMN_EXTRACT_7_12, extract712);
//        values.put(COLUMN_GUMASTA_LICENSE, gumastaLicense);
//        values.put(COLUMN_INCORPORATION_CERTIFICATE, incorporationCertificate);
//        values.put(COLUMN_REGISTRATION_CERTIFICATE, registrationCertificate);
//        values.put(COLUMN_OWNER_RATION_CARD, ownerRationCard);
//        values.put(COLUMN_OWNER_VOTER_ID, ownerVoterId);
//        values.put(COLUMN_OWNER_PASSPORT, ownerPassport);
//        values.put(COLUMN_OWNER_DRIVING_LICENSE, ownerDrivingLicense);
//        values.put(COLUMN_OCCUPANT_AADHAR_CARD, occupantAadharCard);
//        values.put(COLUMN_OCCUPANT_PAN_CARD, occupantPanCard);
//        values.put(COLUMN_OCCUPANT_RATION_CARD, occupantRationCard);
//        values.put(COLUMN_OCCUPANT_VOTER_ID, occupantVoterId);
//        values.put(COLUMN_OCCUPANT_PASSPORT, occupantPassport);
//        values.put(COLUMN_OCCUPANT_DRIVING_LICENSE, occupantDrivingLicense);
        values.put(COLUMN_STRUCTURE_STATUS, structureStatus);
        values.put(COLUMN_REMARKS, remarks);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
//        values.put(COLUMN_FINGER_PRINT_RIGHT, fingerPrintRight);
//        values.put(COLUMN_FINGER_PRINT_LEFT, fingerPrintLeft);

        long result = db.insert(TABLE_SURVEY_DETAILS, null, values);
        db.close();
        return result;
    }


    // Get all survey details
    public Cursor getAllSurveyDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SURVEY_DETAILS, null);
    }
    public Cursor getSurveyDetailsBySUID(String suid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_SURVEY_DETAILS + " WHERE " + COLUMN_SUID + " = ?";
        return db.rawQuery(query, new String[]{suid});
    }
    public boolean updateSurveyDetails(
            String suid, String projectDetails, String buildingName, String buildingType, String structureType,
            String ownerName, String occupantName, String contactNo, String carpetArea, String address,
            String latitude, String longitude, String remarks, String isAuthorised, String urp,
            String commercialActivityName, String amenityName, String occupantSpouseName, String noOfFamilyMembers,
            String roomNo, String occupantAge, String otherRelationshipWithOwner, String structureUse,
            String amenityType, String occupantGender, String relationshipWithOwner, String structureStatus,
            String occupancyType) {

        SQLiteDatabase db = this.getWritableDatabase();

        // Prepare values for update
        ContentValues values = new ContentValues();
//        values.put(COLUMN_PROJECT_DETAILS, projectDetails); // Added the missing 'project_details' column
        values.put(COLUMN_BUILDING_NAME, buildingName);
        values.put(COLUMN_BUILDING_TYPE, buildingType);
        values.put(COLUMN_STRUCTURE_TYPE, structureType);
        values.put(COLUMN_OWNER_NAME, ownerName);
        values.put(COLUMN_OCCUPANT_NAME, occupantName);
        values.put(COLUMN_CONTACT_NO, contactNo);
        values.put(COLUMN_CARPET_AREA, carpetArea);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        values.put(COLUMN_REMARKS, remarks);
        values.put(COLUMN_IS_AUTHORISED, isAuthorised);
        values.put(COLUMN_URP, urp); // Added the missing 'urp' column
        values.put(COLUMN_COMMERCIAL_ACTIVITY_NAME, commercialActivityName);
        values.put(COLUMN_AMENITY_NAME, amenityName);
        values.put(COLUMN_OCCUPANT_SPOUSE_NAME, occupantSpouseName);
        values.put(COLUMN_NO_OF_FAMILY_MEMBERS, noOfFamilyMembers);
        values.put(COLUMN_ROOM_NO, roomNo);
        values.put(COLUMN_OCCUPANT_AGE, occupantAge); // Added missing 'occupant_age' column
        values.put(COLUMN_OTHER_RELATIONSHIP_WITH_OWNER, otherRelationshipWithOwner); // Added missing 'other_relationship_with_owner' column
        values.put(COLUMN_STRUCTURE_USE, structureUse);
        values.put(COLUMN_AMENITY_TYPE, amenityType);
        values.put(COLUMN_OCCUPANT_GENDER, occupantGender);
        values.put(COLUMN_RELATIONSHIP_WITH_OWNER, relationshipWithOwner);
        values.put(COLUMN_STRUCTURE_STATUS, structureStatus);
        values.put(COLUMN_OCCUPANCY_TYPE, occupancyType); // Added the missing 'occupancy_type' column

        // Update the row matching the given SUID
        int rowsUpdated = db.update(TABLE_SURVEY_DETAILS, values, COLUMN_SUID + " = ?", new String[]{suid});
        db.close();

        return rowsUpdated > 0; // Return true if at least one row was updated
    }


}
