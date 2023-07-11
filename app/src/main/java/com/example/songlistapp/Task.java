package com.example.songlistapp;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String SongTitle;
    private String Artist;
    private String date;
    private String Rate;

    public Task(int id, String title,String artist, String date, String rate) {
        this.id = id;
        this.SongTitle = title;
        this.Artist  = artist;
        this.date = date;
        this.Rate = rate;
    }

    public int getId() { return id; }

    public String getSongTitle() { return SongTitle; }

    public String getArtist() {return  Artist;}

    public String getDate() { return date;}

    public String getRate() {return  Rate;}

    @NonNull
    @Override
    public String toString() {
        return id + "\n" + SongTitle + "\n" + Artist + "\n" + date + "\n" + Rate;
    }


}
