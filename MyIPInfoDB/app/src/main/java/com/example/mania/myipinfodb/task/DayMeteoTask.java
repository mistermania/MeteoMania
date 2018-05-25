package com.example.mania.myipinfodb.task;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mania.myipinfodb.DayActivity;
import com.example.mania.myipinfodb.task.parser.MeteoInfoData;
import com.example.mania.myipinfodb.task.parser.MeteoInfoList;

import java.util.ArrayList;

/**
 * Created by Manyia on 25/05/2018.
 */

public class DayMeteoTask extends AsyncTask<MeteoInfoList, MeteoInfoList, MeteoInfoList> {
    private AppCompatActivity parentActivity;
    private ProgressDialog progressDialog;
    public static final String TAG = IpInfoTask.class.getName();

    public DayMeteoTask(AppCompatActivity parent, ProgressDialog progressDialog){
        this.parentActivity = parent;
        this.progressDialog = progressDialog;
    }

    @Override
    protected MeteoInfoList doInBackground(MeteoInfoList... meteoInfoLists) {

        Log.d(TAG, "Création d'un nouveau jour");
        MeteoInfoList Jour = new MeteoInfoList();

        for(MeteoInfoData meteoInfoData : meteoInfoLists[0]){
            Jour.add(meteoInfoData);
        }

        return Jour;
    }

    protected void onProgressUpdate(String... values) {
        this.progressDialog.setMessage(values[0]);
    }

    protected void onPostExecute(ArrayList<MeteoInfoData> myData) {
        super.onPostExecute((MeteoInfoList) myData);
        Intent intent  = new Intent(this.parentActivity,DayActivity.class);
        intent.putExtra("data", myData);
        progressDialog.dismiss();
        this.parentActivity.startActivity(intent);
    }
}
