package com.example.songlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditActvity extends AppCompatActivity {

    //create the UI
    TextView editId;
    EditText updateTitle, updateSinger,updateYear;
    RadioGroup editRating;
    Button btnUpdate, btnDelete, btnReturn;
    Task data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_actvity);

        //link UI
        editId = findViewById(R.id.IdEdit);
        updateTitle = findViewById(R.id.updateTitle);
        updateSinger = findViewById(R.id.updateSinger);
        updateYear = findViewById(R.id.updateYear);
        editRating = findViewById(R.id.editRating);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnReturn = findViewById(R.id.btnReturn);

        Intent i = getIntent();
        data = (Task) i.getSerializableExtra("data");

        editId.setText(" ID: "+data.getId()+"\n");
        updateTitle.setText(data.getSongTitle());
        updateSinger.setText(data.getArtist());
        updateYear.setText(data.getDate());

        if(data.getRate() == 5 ){
            editRating.check(R.id.ub5);
        } else if (data.getRate() == 4) {
            editRating.check(R.id.ub4);
        } else if (data.getRate() == 3) {
            editRating.check(R.id.ub3);
        } else if (data.getRate() == 2) {
            editRating.check(R.id.ub2);
        } else if (data.getRate() == 1) {
            editRating.check(R.id.ub1);
        }


        //btn method

        //update -> take in your fields, send to db method
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ID = editRating.getCheckedRadioButtonId();
                if(ID == R.id.ub1){

                    data.setRate(1);
                }
                if(ID == R.id.ub2){

                    data.setRate(2);
                }
                if(ID == R.id.ub3){

                    data.setRate(3);
                }
                if(ID == R.id.ub4){

                    data.setRate(4);
                }
                if(ID == R.id.ub5){

                    data.setRate(5);
                }

                DatabaseHelper dbh = new DatabaseHelper(EditActvity.this);
                data.setSongTitle(updateTitle.getText().toString());
                data.setArtist(updateSinger.getText().toString());
                data.setDate(updateYear.getText().toString());
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        //delete -> refer to crud
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbh = new DatabaseHelper(EditActvity.this);
                dbh.deleteSong(data.getId());
                finish();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditActvity.this, ShowList.class);
                startActivity(i);
            }
        });
    }
}