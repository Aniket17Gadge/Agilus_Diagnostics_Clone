package com.example.agilusdiagnosticsclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.*;
import android.view.View;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LocationScreen extends AppCompatActivity implements AdapterView.OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_screen);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        String[] locations = getResources().getStringArray(R.array.location);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,locations);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // fetch the user selected value
        String item = parent.getItemAtPosition(position).toString();

        // create Toast with user selected value
        Toast.makeText(LocationScreen.this, "Selected Item is: \t" + item, Toast.LENGTH_LONG).show();

        // set user selected value to the TextView
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        intent.putExtra("key", item);
        startActivity(intent);
    }



    }
