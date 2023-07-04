package com.example.songlistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Start version with 1
    // increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 3;
    // Filename of the database
    private static final String DATABASE_NAME = "SongList.db";

    private static final String TABLE_SONGLIST = "Songs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Title = "Song";
    private static final String COLUMN_Name = "Singer";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_RATE = "rate";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONGLIST +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_Title + " TEXT,"
                + COLUMN_Name + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_RATE + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGLIST);
        // Create table(s) again
        onCreate(db);

    }

    public void insertTask(String Song,String Singer, String date, String rate){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_Title, Song);
        // store the column name as key and description as value
        values.put(COLUMN_Name, Singer);
        // Store the column name as key and the date as value
        values.put(COLUMN_DATE, date);
        // Store the column name as key and the date as value
        values.put(COLUMN_RATE, rate);
        // Insert the row into the TABLE_SONGLIST
        db.insert(TABLE_SONGLIST, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_Title,COLUMN_Name, COLUMN_DATE,COLUMN_RATE};
        Cursor cursor = db.query(TABLE_SONGLIST, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String name = cursor.getString(2);
                String date = cursor.getString(3);
                String rate = cursor.getString(4);
                Task obj = new Task(id, title, name, date,rate);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }


}
