package com.example.mania.myipinfodb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mania.myipinfodb.R;
import com.example.mania.myipinfodb.task.parser.MeteoInfoData;
import com.example.mania.myipinfodb.task.parser.MeteoInfoList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MeteoActivity extends AppCompatActivity {
    private static String TAG = MeteoActivity.class.getName();
    private MeteoInfoList listMeteoInfoData;
    private String testjour4,testjour3,testjour2,sdt1t,sdt2t,sdt3t;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);

        Bundle b = getIntent().getExtras();

        if (b != null) {
            listMeteoInfoData = b.getParcelable("data");
        }
        if (listMeteoInfoData != null) {
            Log.i(TAG, "Arrivé des données  Nombre de data Météo : " + listMeteoInfoData.size());
            if (listMeteoInfoData.size() != 0) {
                //init Date
                Date d = new Date();
                SimpleDateFormat f = new SimpleDateFormat("EEEE d MMMM");
                String sf = f.format(d);

                SimpleDateFormat formatdt = new SimpleDateFormat("EEEE d");
                SimpleDateFormat formatDay = new SimpleDateFormat("d");
                Date dt1 = new Date();
                Date dt2 = new Date();
                Date dt3 = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(dt1);
                c.add(Calendar.DATE,1);
                dt1 = c.getTime();
                c.add(Calendar.DATE,1);
                dt2 = c.getTime();
                c.add(Calendar.DATE,1);
                dt3 = c.getTime();
                sdt1t = formatdt.format(dt1);
                sdt2t = formatdt.format(dt2);
                sdt3t = formatdt.format(dt3);
                testjour2 = formatDay.format(dt1);
                testjour3 = formatDay.format(dt2);
                testjour4 = formatDay.format(dt3);


                SimpleDateFormat ti = new SimpleDateFormat("d");



                Log.d(TAG,"City: "+ listMeteoInfoData.get(2).getCity());

                //Ville
                TextView city = findViewById(R.id.City);
                city.setText("Weather on " + listMeteoInfoData.get(1).getCity());
                //Time
                TextView timeNow = findViewById(R.id.timeNow);
                timeNow.setText(sf);




                //Placement des températures
                TextView temperature1 = findViewById(R.id.temperature1);
                temperature1.setText("Time: "+listMeteoInfoData.get(1).getDate_start().substring(11,16) +" - Temperature: "+ listMeteoInfoData.get(1).getTemperature() + "°C");

                TextView temperature2 = findViewById(R.id.temperature2);
                temperature2.setText("Time: "+listMeteoInfoData.get(2).getDate_start().substring(11,16) +" - Temperature: "+ listMeteoInfoData.get(2).getTemperature() + "°C");

                TextView temperature3 = findViewById(R.id.temperature3);
                temperature3.setText("Time: "+listMeteoInfoData.get(3).getDate_start().substring(11,16) +" - Temperature: "+ listMeteoInfoData.get(3).getTemperature() + "°C");

                //Placement des images

                ImageView symbol1 = findViewById(R.id.symbol1);
                symbol1.setImageResource(listMeteoInfoData.get(1).getImage());

                ImageView symbol2 = findViewById(R.id.symbol2);
                symbol2.setImageResource(listMeteoInfoData.get(2).getImage());

                ImageView symbol3 = findViewById(R.id.symbol3);
                symbol3.setImageResource(listMeteoInfoData.get(3).getImage());

                //Bountons
                Button button1 = findViewById(R.id.jour2);
                button1.setText(sdt1t);
                Button button2 = findViewById(R.id.jour3);
                button2.setText(sdt2t);
                Button button3 = findViewById(R.id.jour4);
                button3.setText(sdt3t);

                CoordinatorLayout layout = findViewById(R.id.background);
                layout.setBackgroundResource(R.drawable.android_background);



            }
        }

//Préparation des listes pour les 3 prochains jours:
    }

    public void connectDay2(View view){
        MeteoInfoList meteoInfoListDay2 = new MeteoInfoList();
        for(MeteoInfoData meteoInfoData : listMeteoInfoData){
            if(meteoInfoData.getDate_start().substring(8,10).equals(testjour2)){
                meteoInfoListDay2.add(meteoInfoData);
            }
        }
        Log.d(TAG, "Taille meteoinfolistday2: " + meteoInfoListDay2.size());
        Intent intent  = new Intent(this,DayActivity.class);
        intent.putExtra("data", (Parcelable) meteoInfoListDay2);
        this.startActivity(intent);

    }

    public void connectDay3(View view){
        MeteoInfoList meteoInfoListDay3 = new MeteoInfoList();
        for(MeteoInfoData meteoInfoData : listMeteoInfoData){
            if(meteoInfoData.getDate_start().substring(8,10).equals(testjour3)){
                meteoInfoListDay3.add(meteoInfoData);
            }
        }
        Intent intent  = new Intent(this,DayActivity.class);
        intent.putExtra("data", (Parcelable) meteoInfoListDay3);
        this.startActivity(intent);

    }

    public void connectDay4(View view){
        MeteoInfoList meteoInfoListDay4 = new MeteoInfoList();
        for(MeteoInfoData meteoInfoData : listMeteoInfoData){
            if(meteoInfoData.getDate_start().substring(8,10).equals(testjour4)){
                meteoInfoListDay4.add(meteoInfoData);
            }
        }
        Intent intent  = new Intent(this,DayActivity.class);
        intent.putExtra("data", (Parcelable) meteoInfoListDay4);
        this.startActivity(intent);
    }


}
