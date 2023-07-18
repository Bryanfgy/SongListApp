package com.example.songlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;


public class ShowList extends AppCompatActivity {
    Button Fivestars;
    ListView lv;
    ArrayList<Task> al;
    ArrayAdapter<Task> aa;
    CustomAdapter adapter;

    ArrayList task;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlist);

        Fivestars = findViewById(R.id.Fivestars);
        lv = findViewById(R.id.List);

        al = new ArrayList<>();
//        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
        adapter =  new CustomAdapter(this,R.layout.custom_row,al);
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
