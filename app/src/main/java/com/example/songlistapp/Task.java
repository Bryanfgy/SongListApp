package com.example.songlistapp;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String SongTitle;
    private String Artist;
    private String date;
    private int Rate;

    public Task(int id, String title,String artist, String date, int rate) {
        this.id = id;
        this.SongTitle = title;
        this.Artist  = artist;
        this.date = date;
        this.Rate = rate;
    }

    public int getId() { return id; }

    public String getSongTitle() { return SongTitle; }
    public void setSongTitle(String SongTitle){ this.SongTitle = SongTitle;}
    public String getArtist() {return  Artist;}
    public void setArtist(String Artist){ this.Artist = Artist;}
    public String getDate() { return date;}
    public void setDate(String date){ this.date = date;}
    public int getRate() {return  Rate;}
    public void setRate(int Rate){ this.Rate = Rate;}
    @NonNull
    @Override
    public String toString() {
        return SongTitle + "\n" + Artist + "-" + date + "\n" + Rate;
    }


}
