package com.example.mania.myipinfodb.task;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mania.myipinfodb.TextLocationActivity;
import com.example.mania.myipinfodb.task.parser.IpInfoDBParser;
import com.example.mania.myipinfodb.task.parser.IpInfoData;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class IpInfoTask extends AsyncTask<String, String, IpInfoData> {
// google api: AIzaSyC6o6ItOMUFdWfHYorzrM0m95WFJI65S-w
    private static final String key = "AIzaSyC6o6ItOMUFdWfHYorzrM0m95WFJI65S-w";
    private static final String url_prefixed = "https://maps.googleapis.com/maps/api/geocode/xml";
    public static final String TAG = IpInfoTask.class.getName();

    private OkHttpClient okHttpClient;
    private String url;
    private AppCompatActivity parentActivity;
    private ProgressDialog progressDialog;

    public IpInfoTask(AppCompatActivity parent, ProgressDialog progressDialog){
        okHttpClient = new OkHttpClient();
        this.url = "";
        this.parentActivity = parent;
        this.progressDialog = progressDialog;
    }

    @Override
    protected IpInfoData doInBackground(String... strings) {
        for(String address : strings) {
            this.url = url_prefixed + "?address=" + address + "&key=" + key ;
            Log.d(TAG, "URL:" + this.url);
            // PHASE 1: requete http
            try {

                publishProgress("Connexion au serveur");

                Request request = new Request.Builder().url(url).build();
                Response http_response = this.okHttpClient.newCall(request).execute();

                String xmlData = http_response.body().string();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // PHASE 2: parsing
                IpInfoDBParser parser = new IpInfoDBParser(xmlData);
                IpInfoData myData = parser.getData();
                Log.i(TAG, "Lat:" + myData.getLatitude());
                Log.i(TAG, "Long:" + myData.getLongitude());

                publishProgress("Traitement des données");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return myData;
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        this.progressDialog.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(IpInfoData ipInfoData) {
        super.onPostExecute(ipInfoData);
        Intent intent  = new Intent(this.parentActivity,TextLocationActivity.class);
        intent.putExtra("lon", ipInfoData.getLongitude());
        intent.putExtra("lat", ipInfoData.getLatitude());
        progressDialog.dismiss();
        this.parentActivity.startActivity(intent);
    }
}
