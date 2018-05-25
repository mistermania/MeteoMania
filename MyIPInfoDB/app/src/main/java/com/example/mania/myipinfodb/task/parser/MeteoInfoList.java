package com.example.mania.myipinfodb.task.parser;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.mania.myipinfodb.R;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Manyia on 24/05/2018.
 */

public class MeteoInfoList extends ArrayList<MeteoInfoData> implements Parcelable {
    protected MeteoInfoList(Parcel in) {
        this.getFromParcel(in);
    }

    public static final String TAG = MeteoInfoList.class.getName();

    public MeteoInfoList(){}


    public static final Creator<MeteoInfoList> CREATOR = new Creator<MeteoInfoList>() {
        @Override
        public MeteoInfoList createFromParcel(Parcel in) {
            return new MeteoInfoList(in);
        }

        @Override
        public MeteoInfoList[] newArray(int size) {
            return new MeteoInfoList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        int size = this.size();
        parcel.writeInt(size);
        for(int i=0; i < size; i++)
        {
            MeteoInfoData meteoData = this.get(i); //On vient lire chaque objet personne
            parcel.writeString(meteoData.getDate_start());
            parcel.writeString(meteoData.getDate_end());
            parcel.writeString(meteoData.getTemperature());
            parcel.writeString(meteoData.getSymbol());
            parcel.writeString(meteoData.getNuages());
            parcel.writeString(meteoData.getCity());
        }

    }


    private void getFromParcel(Parcel in) {
        // On vide la liste avant tout remplissage
        this.clear();

        //Récupération du nombre d'objet
        int size = in.readInt();

        //On repeuple la liste avec de nouveau objet
        for(int i = 0; i < size; i++)
        {
            MeteoInfoData meteoData = new MeteoInfoData();
            meteoData.setDate_start(in.readString());
            meteoData.setDate_end(in.readString());
            meteoData.setTemperature(in.readString());
            meteoData.setSymbol(in.readString());
            meteoData.setNuages(in.readString());
            meteoData.setCity(in.readString());
            this.add(meteoData);
        }
    }


}
