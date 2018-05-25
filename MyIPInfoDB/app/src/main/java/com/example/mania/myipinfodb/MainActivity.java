package com.example.mania.myipinfodb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.mania.myipinfodb.R;
import com.example.mania.myipinfodb.task.IpInfoTask;
import com.example.mania.myipinfodb.task.MeteoInfoTask;
import com.example.mania.myipinfodb.task.parser.IpInfoDBParser;
import com.example.mania.myipinfodb.task.parser.IpInfoData;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getName();
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout layout = findViewById(R.id.background1);
        layout.setBackgroundResource(R.drawable.android_background);
    }

    /**
     * Methode lancer lors du click sur le bouton
     * @param view
     */
    public void connect(View view){
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Preparing...");
        progressDialog.show();

        Log.d(TAG, "Lancement de l'Async Task Méteo");

        EditText et_address = findViewById(R.id.address);
        String str_address= et_address.getText().toString();
        Log.i(TAG,"address:"+ str_address);



       // IpInfoTask ipInfoTask = new IpInfoTask(this, progressDialog);
        // ipInfoTask.execute(str_address);

        MeteoInfoTask meteoInfoTask = new MeteoInfoTask(this,progressDialog);
        meteoInfoTask.execute(str_address);
    }
}
