package com.example.songlistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomAdapter extends ArrayAdapter {
    Context parentContext;
    int Layout_id;
    ArrayList<Task> ObjectsList;

    public CustomAdapter(Context context, int resource, ArrayList<Task> objects) {
        super(context, resource, objects);
        parentContext = context;
        Layout_id = resource;
        ObjectsList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) parentContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(Layout_id, parent, false);

        TextView textViewHeader = rowView.findViewById(R.id.tvHeader);
        TextView textViewBody = rowView.findViewById(R.id.tvBody);
        TextView textViewDate = rowView.findViewById(R.id.tvYear);
        TextView textViewRating = rowView.findViewById(R.id.tvRating);

        Task currentVersion = ObjectsList.get(position);

        textViewHeader.setText(currentVersion.getSongTitle());
        textViewBody.setText(currentVersion.getArtist());
        textViewDate.setText(currentVersion.getDate());
        textViewRating.setText(String.valueOf(currentVersion.getRate()));

        return rowView;
    }
}
