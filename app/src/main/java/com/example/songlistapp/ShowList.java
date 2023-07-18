package com.example.songlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;


public class ShowList extends AppCompatActivity {
    Button Fivestars, returnbtn;
    ListView lv;
    ArrayList<Task> al;
    ArrayList<String> yearArray;
    ArrayList<String> SpinnerList;
    Spinner datespin;
    CustomAdapter adapter;
    ArrayAdapter<String> yearAdapter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlist);

        String order =" ASC";
        DatabaseHelper db = new DatabaseHelper(ShowList.this);
        yearArray = db.populateSpinner();
        SpinnerList = new ArrayList<>();
        SpinnerList.add("Select year to filter");
        for (String year : yearArray) {
            SpinnerList.add(String.valueOf(year));
        }
        yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, SpinnerList);
        yearAdapter.notifyDataSetChanged();
        db.close();

        datespin = findViewById(R.id.dateSpinner);
        Fivestars = findViewById(R.id.Fivestars);
        returnbtn = findViewById(R.id.returnBtn);
        lv = findViewById(R.id.List);
        al = new ArrayList<>();
        adapter =  new CustomAdapter(this,R.layout.custom_row,al);


        datespin.setAdapter(yearAdapter);
        //database setup
        lv.setAdapter(adapter);

            DatabaseHelper dbh = new DatabaseHelper(ShowList.this);
            al.clear();
            al.addAll(dbh.getTasks());
            adapter.notifyDataSetChanged();
            dbh.close();

        adapter.notifyDataSetChanged();

        

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task data = al.get(position);
                Intent i = new Intent(ShowList.this,
                       EditActvity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        //btn 5 star
        //on button click, get the array of 5 5 star from db, repopulate arraylist
        Fivestars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbh = new DatabaseHelper(ShowList.this);
                al.clear();
                al.addAll(dbh.getTasksByStars("5"));
                adapter.notifyDataSetChanged();
            }
        });

        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHelper dbh = new DatabaseHelper(ShowList.this);
        al.clear();
        al.addAll(dbh.getTasks());
        adapter.notifyDataSetChanged();
        dbh.close();
    }


}
