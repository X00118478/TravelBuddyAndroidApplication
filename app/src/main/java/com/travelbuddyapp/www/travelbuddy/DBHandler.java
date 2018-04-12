package com.travelbuddyapp.www.travelbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexMcQuade on 21/01/2018.
 */

public class DBHandler extends SQLiteOpenHelper {

  //Database Version
  private static final int DATABASE_VERSION = 1;
  //Database Name
  private static final String DATABASE_NAME = "travelBuddy";
  //Contacts table name
  private static final String TABLE_FILL_UP_ENTRY = "fillUpEntry";
  //Fill up entry column names
  private static final String ID = "id";
  private static final String COST_PER_LITRE = "costPerLitre";
  private static final String VOLUME_OF_LITRES = "volumeOfLitres";
  private static final String ODEMETER_READING = "odemeterReading";

  public DBHandler(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);

  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    //Add columns into the new table and declare data type
    String CREATE_ENTRY_TABLE = "CREATE TABLE" + TABLE_FILL_UP_ENTRY + "(" + ID + "INTEGER PRIMARY KEY," + COST_PER_LITRE + "DOUBLE,"
      + VOLUME_OF_LITRES + "DOUBLE," + ODEMETER_READING + "DOUBLE" + ")";
    //Creates the columns and Table
    db.execSQL(CREATE_ENTRY_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //Drop table if they exist
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILL_UP_ENTRY);
    //Tús nua lena Tablai
    onCreate(db);
  }

  //Insert a new Entry using below method
  public void addEntry(FillUpData fillUpData) {
    //Open DB writable Connection
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    //Get the entered value and enter it into the COST_PER_LITRE
    values.put(COST_PER_LITRE, fillUpData.getCostPerLitre());
    //Get the entered value and enter it into the VOLUME_OF_LITRES
    values.put(VOLUME_OF_LITRES, fillUpData.getVolumeOfLitres());
    //Get the entered value and enter it into the ODEMETER_READING
    values.put(ODEMETER_READING, fillUpData.getOdometerReading());
    //Insert a Row
    db.insert(TABLE_FILL_UP_ENTRY, null, values);
    //Close the DB Connection
    db.close();
  }

  //Read the Data use the PK as a parameter
  public FillUpData getFillUpData(int id) {
    //Open DB Readable Connection
    SQLiteDatabase db = this.getReadableDatabase();
    //Obtain the Data and set it in the variable called cursor
    Cursor cursor = db.query(TABLE_FILL_UP_ENTRY, new String[]{ID, COST_PER_LITRE, VOLUME_OF_LITRES, ODEMETER_READING}, ID + "=?",
      new String[]{String.valueOf(id)}, null, null, null, null);
    //If the Cursor is empty then move it to the first result
    if (cursor != null) {
      cursor.moveToFirst();
    }
    FillUpData entry = new FillUpData(Integer.parseInt(cursor.getString(0)),
      cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3));
    //return the entry we used
    return entry;
  }

  //Get all of the Entries
  public List<FillUpData> getEveryEntry() {
    List<FillUpData> entryList = new ArrayList<FillUpData>();
    //Obtain all entries using a SELECT ALL query
    //SELECT * FROM = TABLE_FILL_UP_ENTRY
    String selectAllQuery = "Select * FROM " + TABLE_FILL_UP_ENTRY;
    SQLiteDatabase db = this.getWritableDatabase();
    //Runs the provided SQL and returns a Cursor on the result set.
    Cursor cursor = db.rawQuery(selectAllQuery, null);
    //Loop through the rows in the DB and copying them to our new List.
    if (cursor.moveToFirst()) {
      do {
        FillUpData fillUpData = new FillUpData();
        //Get data @ index 0 -3
        fillUpData.setId(Integer.parseInt(cursor.getString(0)));
        fillUpData.setCostPerLitre(cursor.getDouble(1));
        fillUpData.setVolumeOfLitres(cursor.getDouble(2));
        fillUpData.setOdometerReading(cursor.getDouble(3));

        // Add the entries to the List
        entryList.add(fillUpData);
      }
      while (cursor.moveToNext());
    }
    //Return our new List
    return entryList;
  }

  //Get the total entry records (Count)
  public int getEntryCount() {
    String entryCountQuery = "SELECT * FROM " + TABLE_FILL_UP_ENTRY;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(entryCountQuery, null);
    cursor.close();
    return cursor.getCount();
  }

  //Time to allow an Update on the Entry in case the user made an error
  //UPDATE
  public int updateFillUpData(FillUpData fillUpData) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues updateEntry = new ContentValues();
    updateEntry.put(COST_PER_LITRE, fillUpData.getCostPerLitre());
    updateEntry.put(VOLUME_OF_LITRES, fillUpData.getVolumeOfLitres());
    updateEntry.put(ODEMETER_READING, fillUpData.getOdometerReading());

    //Update to ROW and return
    return db.update(TABLE_FILL_UP_ENTRY, updateEntry, ID + " =?",
      new String[]{String.valueOf(fillUpData.getId())});
  }

  //Delete an Entry
  public void deleteEntry(FillUpData fillUpData) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_FILL_UP_ENTRY, ID + " =?",
      new String[]{String.valueOf(fillUpData.getId())});
    db.close();
  }

}




