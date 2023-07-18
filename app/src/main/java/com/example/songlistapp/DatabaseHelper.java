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
    private static final int DATABASE_VER = 4;
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
                + COLUMN_DATE + " INTEGER,"
                + COLUMN_Title + " TEXT, "
                + COLUMN_Name + " TEXT,"
                + COLUMN_RATE + " INTEGER)";
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

    public void insertTask(String title, String singer ,String year, String stars){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the title as value
        values.put(COLUMN_Title, title);
        // Store the column name as key and the date as value
        values.put(COLUMN_Name, singer);
        values.put(COLUMN_DATE, year);

        //add in other values

        values.put(COLUMN_RATE, stars);

        // Insert the row into the TABLE_TASK
        db.insert(TABLE_SONGLIST, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> songs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_Title, COLUMN_Name, COLUMN_DATE, COLUMN_RATE};
        Cursor cursor = db.query(TABLE_SONGLIST, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                String year = cursor.getString(3);
                int stars = cursor.getInt(4);
                Task obj = new Task(id, title, singer, year, stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Task> getTasksByStars(String starInput) {
        ArrayList<Task> songs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_Title, COLUMN_Name, COLUMN_DATE, COLUMN_RATE};
        String condition = COLUMN_RATE + " Like ?";
        String[] args = {"%"+starInput+"%"};
        Cursor cursor = db.query(TABLE_SONGLIST, columns, condition, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                String year = cursor.getString(3);
                int stars = cursor.getInt(4);
                Task obj = new Task(id, title, singer, year, stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONGLIST, condition, args);
        db.close();
        return result;
    }

    public int updateSong(Task data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_Title, data.getSongTitle());
        values.put(COLUMN_RATE, data.getRate());
        values.put(COLUMN_DATE, data.getDate());
        values.put(COLUMN_Name, data.getArtist());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONGLIST, values, condition, args);
        db.close();
        return result;
    }

    public ArrayList<String> populateSpinner(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns ={COLUMN_DATE};
        String sort = COLUMN_DATE+" ASC";
        Cursor cursor = db.query(true, TABLE_SONGLIST, columns, null, null, null, null, sort, null);
        ArrayList<String> yearList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String year = cursor.getString(0);
                yearList.add(year);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return yearList;
    }

}
