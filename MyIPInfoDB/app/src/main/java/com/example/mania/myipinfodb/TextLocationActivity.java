package com.example.mania.myipinfodb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mania.myipinfodb.R;

public class TextLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_location);

        Intent thisIntent = getIntent();
        String lon = thisIntent.getExtras().getString("lon");
        String lat = thisIntent.getExtras().getString("lat");
        TextView latText = (TextView) findViewById(R.id.latitude);
        latText.setText(lat);
        TextView lonText = (TextView) findViewById(R.id.longitude);
        lonText.setText(lon);
    }
}
