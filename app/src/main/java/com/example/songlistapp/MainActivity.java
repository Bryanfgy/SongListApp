package com.example.songlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert,btnGetdata;
    RadioGroup rating;
    EditText songTitle, nameArtist, date;
    ArrayList<String> ArrayList;
    ArrayList<Task> al;
    ArrayAdapter<String> aAdapter;
    ArrayAdapter<Task> aa;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetdata = findViewById(R.id.btnShow);
        rating = findViewById(R.id.Rating);
        songTitle = findViewById(R.id.etTitle);
        nameArtist = findViewById(R.id.etSinger);
        date = findViewById(R.id.etDate);
        lv = findViewById(R.id.List);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String songT = songTitle.getText().toString();
                String artistN = nameArtist.getText().toString();
                String releaseD = date.getText().toString();
                String Rating = "";
                int ID = rating.getCheckedRadioButtonId();
                if(ID == R.id.rb1){
                    Rating = "1";
                }
                if(ID == R.id.rb2){
                    Rating = "2";
                }
                if(ID == R.id.rb3){
                    Rating = "3";
                }
                if(ID == R.id.rb4){
                    Rating = "4";
                }
                if(ID == R.id.rb5){
                    Rating = "5";
                }

                // Create the DBHelper object, passing in the
                // activity's Context
                DatabaseHelper db = new DatabaseHelper(MainActivity.this);

                // Insert a task
                db.insertTask(songT,artistN,releaseD,Rating);

            }
        });

        btnGetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
//                java.util.ArrayList<Task> data = db.getTasks();
//                db.close();
//                String test = "";
//                for(int i = 0; i < data.size(); i++){
//                    test += data.get(i) + "\n\n";
//                }
//                ArrayList.add(test+"\n");
//                aAdapter.notifyDataSetChanged();
//                al.clear();
//                al.addAll(db.getTasks());
//                aa.notifyDataSetChanged();

                Intent i = new Intent(MainActivity.this,
                        ShowList.class);
                i.putExtra("Database", db.getTasks() );
                startActivity(i);


            }
        });



    }
}