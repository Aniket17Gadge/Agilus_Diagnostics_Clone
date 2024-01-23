package com.example.agilusdiagnosticsclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import android.content.*;

public class HomeScreen extends AppCompatActivity {
    TextView receiver_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        receiver_location = findViewById(R.id.text3);
        // create the get Intent object
        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String item = intent.getStringExtra("key");
        // display the string into textView
        receiver_location.setText(item);

    }
}