package com.example.songlistapp;

import android.content.Intent;
import android.os.Bundle;
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

    Task array;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlist);

        Fivestars = findViewById(R.id.Fivestars);
        lv = findViewById(R.id.List);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        Intent i = getIntent();
        array = (Task) i.getSerializableExtra("Database");
        // Ask how to transfer information between arrays
//        al.addAll();
//        aa.notifyDataSetChanged();

//        lv.setOnClickListener(new AdapterView.OnItemClickListener());

    }
}
