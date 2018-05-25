package com.example.mania.myipinfodb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mania.myipinfodb.R;
import com.example.mania.myipinfodb.task.parser.MeteoInfoList;

public class DayActivity extends AppCompatActivity {

    private static String TAG = DayActivity.class.getName();
    private MeteoInfoList listMeteoInfoData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        CoordinatorLayout layout = findViewById(R.id.backgroundDay);
        layout.setBackgroundResource(R.drawable.android_background);

        Bundle b = getIntent().getExtras();
        Intent intent = getIntent();

        if (b != null) {
            Log.d(TAG, "b n'est pas null");
            listMeteoInfoData = b.getParcelable("data");
        }
        if (listMeteoInfoData != null) {

            TextView temp1 = findViewById(R.id.temp1);
            temp1.setText(listMeteoInfoData.get(0).getDate_start().substring(11,16) + " - " + listMeteoInfoData.get(0).getTemperature() + "°C");

            ImageView ciel1 = findViewById(R.id.ciel1);
            ciel1.setImageResource(listMeteoInfoData.get(0).getImage());

            if(listMeteoInfoData.size() >= 2){
                TextView temp2 = findViewById(R.id.temp2);
                temp2.setText(listMeteoInfoData.get(1).getDate_start().substring(11,16) + " - " + listMeteoInfoData.get(1).getTemperature() + "°C");

                ImageView ciel2 = findViewById(R.id.ciel2);
                ciel2.setImageResource(listMeteoInfoData.get(1).getImage());
            }

            if (listMeteoInfoData.size() >= 3){
                TextView temp3 = findViewById(R.id.temp3);
                temp3.setText(listMeteoInfoData.get(2).getDate_start().substring(11,16) + " - " + listMeteoInfoData.get(2).getTemperature() + "°C");

                ImageView ciel3 = findViewById(R.id.ciel3);
                ciel3.setImageResource(listMeteoInfoData.get(2).getImage());
            }

            if (listMeteoInfoData.size() >= 4){
                TextView temp4 = findViewById(R.id.temp4);
                temp4.setText(listMeteoInfoData.get(3).getDate_start().substring(11,16) + " - " + listMeteoInfoData.get(3).getTemperature() + "°C");

                ImageView ciel4 = findViewById(R.id.ciel4);
                ciel4.setImageResource(listMeteoInfoData.get(3).getImage());
            }

            if (listMeteoInfoData.size() >= 5){
                TextView temp5 = findViewById(R.id.temp5);
                temp5.setText(listMeteoInfoData.get(4).getDate_start().substring(11,16) + " - " + listMeteoInfoData.get(4).getTemperature() + "°C");

                ImageView ciel5 = findViewById(R.id.ciel5);
                ciel5.setImageResource(listMeteoInfoData.get(4).getImage());
            }

            if (listMeteoInfoData.size() >= 6){
                TextView temp6 = findViewById(R.id.temp6);
                temp6.setText(listMeteoInfoData.get(5).getDate_start().substring(11,16) + " - " + listMeteoInfoData.get(5).getTemperature() + "°C");

                ImageView ciel6 = findViewById(R.id.ciel6);
                ciel6.setImageResource(listMeteoInfoData.get(5).getImage());
            }

            if (listMeteoInfoData.size() >= 7){
                TextView temp7 = findViewById(R.id.temp7);
                temp7.setText(listMeteoInfoData.get(6).getDate_start().substring(11,16) + " - " + listMeteoInfoData.get(6).getTemperature() + "°C");

                ImageView ciel7 = findViewById(R.id.ciel7);
                ciel7.setImageResource(listMeteoInfoData.get(6).getImage());
            }
            if (listMeteoInfoData.size() >= 8){
                TextView temp8 = findViewById(R.id.temp8);
                temp8.setText(listMeteoInfoData.get(7).getDate_start().substring(11,16) + " - " + listMeteoInfoData.get(7).getTemperature() + "°C");

                ImageView ciel8 = findViewById(R.id.ciel8);
                ciel8.setImageResource(listMeteoInfoData.get(7).getImage());
            }





        }



    }

}
