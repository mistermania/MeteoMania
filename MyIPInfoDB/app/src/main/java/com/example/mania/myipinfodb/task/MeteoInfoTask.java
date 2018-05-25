package com.example.mania.myipinfodb.task;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mania.myipinfodb.MeteoActivity;
import com.example.mania.myipinfodb.TextLocationActivity;
import com.example.mania.myipinfodb.task.parser.IpInfoDBParser;
import com.example.mania.myipinfodb.task.parser.IpInfoData;
import com.example.mania.myipinfodb.task.parser.MeteoInfoData;
import com.example.mania.myipinfodb.task.parser.MeteoInfoList;
import com.example.mania.myipinfodb.task.parser.MeteoInfoParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Manyia on 23/05/2018.
 */

public class MeteoInfoTask extends AsyncTask<String, String, ArrayList<MeteoInfoData>> {
    // typical requete: http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&APPID=1388c12f740afe35bd1848fae1b38ae4&units=metric&mode=xml
    private static final String key_meteo = "1388c12f740afe35bd1848fae1b38ae4";
    private static final String key_geocode= "AIzaSyC6o6ItOMUFdWfHYorzrM0m95WFJI65S-w";
    private static final String url_prefixed_meteo = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String url_prefixe_geocode= "https://maps.googleapis.com/maps/api/geocode/xml";

    public static final String TAG = IpInfoTask.class.getName();

    private OkHttpClient okHttpClient;
    private String url_geocode;
    private String url_meteo;
    private AppCompatActivity parentActivity;
    private ProgressDialog progressDialog;

    public MeteoInfoTask(AppCompatActivity parent, ProgressDialog progressDialog){
        okHttpClient = new OkHttpClient();
        this.url_meteo = "";
        this.url_geocode= "";
        this.parentActivity = parent;
        this.progressDialog = progressDialog;
    }

    @Override
    protected ArrayList<MeteoInfoData> doInBackground(String... strings) {

        IpInfoData geocode = null;
        this.url_geocode = url_prefixe_geocode + "?address=" + strings[0] + "&key=" + key_geocode;
        MeteoInfoList dataMeteo;
        Log.d(TAG, "Requete URL api google: " + this.url_geocode);
        publishProgress("Requete vers le serveur geocode");
        try {

            Request request = new Request.Builder().url(url_geocode).build();
            Response http_response = this.okHttpClient.newCall(request).execute();
            Log.i(TAG, "Url geocode:" + url_geocode);

            String xmlData = http_response.body().string();

            // PHASE 2: parsing des données GPS
            Log.d(TAG,"Parsing des données");
            publishProgress("Traitement des données geographiques");

            IpInfoDBParser parser = new IpInfoDBParser(xmlData);
            geocode = parser.getData();
            Log.i(TAG, "Lat:" + geocode.getLatitude());
            Log.i(TAG, "Long:" + geocode.getLongitude());

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

            this.url_meteo = url_prefixed_meteo + "?lat=" + geocode.getLatitude() + "&lon=" + geocode.getLongitude() +  "&APPID=" + key_meteo+ "&units=metric" + "&mode=xml";
            Log.d(TAG, "URL:" + this.url_meteo);
            // PHASE 1: requete http meteo
            try {

                publishProgress("Connexion au serveur");

                Request request = new Request.Builder().url(url_meteo).build();
                Response http_response = this.okHttpClient.newCall(request).execute();

                String xmlData = http_response.body().string();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // PHASE 2: parsing
                MeteoInfoParser parser = new MeteoInfoParser(xmlData);
                dataMeteo = parser.getData();

                return dataMeteo;

              /*  publishProgress("Traitement des données");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/


            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        this.progressDialog.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<MeteoInfoData> myData) {
        super.onPostExecute(myData);
        Intent intent  = new Intent(this.parentActivity,MeteoActivity.class);
        intent.putExtra("data", myData);
        progressDialog.dismiss();
        this.parentActivity.startActivity(intent);
    }
}
