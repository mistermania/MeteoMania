package com.example.mania.myipinfodb.task.parser;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.mania.myipinfodb.R;


public class MeteoInfoData implements Parcelable{
    private String date_start;
    private String date_end;
    private String symbol;
    private String temperature;
    private String nuages;
    private String city;
    public static final String TAG = MeteoInfoData.class.getName();

    public MeteoInfoData(){
        date_start = new String();
        date_end = new String();
        symbol = new String();
        temperature = new String();
        nuages = new String();
        city = new String();
    }

    protected MeteoInfoData(Parcel in) {
        date_start = in.readString();
        date_end = in.readString();
        symbol = in.readString();
        temperature = in.readString();
        nuages = in.readString();
        city = in.readString();
    }

    public static final Creator<MeteoInfoData> CREATOR = new Creator<MeteoInfoData>() {
        @Override
        public MeteoInfoData createFromParcel(Parcel in) {
            return new MeteoInfoData(in);
        }

        @Override
        public MeteoInfoData[] newArray(int size) {
            return new MeteoInfoData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date_start);
        parcel.writeString(date_end);
        parcel.writeString(symbol);
        parcel.writeString(temperature);
        parcel.writeString(nuages);
        parcel.writeString(city);
    }


    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getNuages() {
        return nuages;
    }

    public void setNuages(String nuages) {
        this.nuages = nuages;
    }

    public String getCity() { return city;}

    public void setCity(String city) {this.city = city;}

    public int getHeure(){
        String heure = this.getDate_start().substring(11,13);
        return Integer.parseInt(heure);
    }

    public int getImage(){
        int image;
        boolean night = false;
        int heure = this.getHeure();
        String symbol = this.getSymbol();
        if (heure < 8 || heure > 20){
            night = true;
        }

        switch (symbol){
            case "clear sky":
                if (night){
                    image =  R.drawable.clear_sky_night;
                }
                else {
                    image = R.drawable.clear_sky_day;
                }
                break;
            case "few clouds":
                if (night){
                    image =  R.drawable.few_clouds_night;
                }
                else {
                    image = R.drawable.few_clouds_day;
                }
                break;
            case "scattered clouds":
                if (night){
                    image =  R.drawable.scattered_clouds_night;
                }
                else {
                    image = R.drawable.scattered_clouds_day;
                }
                break;
            case "broken clouds":
                image = R.drawable.broken_clouds;
                break;
            case "shower rain":
                image = R.drawable.shower_rain;
                break;
            case "light intensity shower rain":
                image = R.drawable.shower_rain;
                break;
            case "heavy intensity shower rain":
                image = R.drawable.shower_rain;
                break;
            case "ragged shower rain":
                image = R.drawable.shower_rain;
                break;
            case "light intensity drizzle":
                image = R.drawable.shower_rain;
                break;
            case "drizzle":
                image = R.drawable.shower_rain;
                break;
            case "heavy intensity drizzle":
                image = R.drawable.shower_rain;
                break;
            case "light intensity drizzle rain":
                image = R.drawable.shower_rain;
                break;
            case "drizzle rain":
                image = R.drawable.shower_rain;
                break;
            case "heavy intensity drizzle rain":
                image = R.drawable.shower_rain;
                break;
            case "shower rain and drizzle":
                image = R.drawable.shower_rain;
                break;
            case "heavy shower rain and drizzle":
                image = R.drawable.shower_rain;
                break;
            case "shower drizzle":
                image = R.drawable.shower_rain;
                break;
            case "rain":
                if (night){
                    image =  R.drawable.rain_night;
                }
                else {
                    image = R.drawable.rain_day;
                }
                break;
            case "light rain":
                if (night){
                    image =  R.drawable.rain_night;
                }
                else {
                    image = R.drawable.rain_day;
                }
                break;
            case "heavy intensity rain":
                if (night){
                    image =  R.drawable.rain_night;
                }
                else {
                    image = R.drawable.rain_day;
                }
                break;
            case "very heavy rain":
                if (night){
                    image =  R.drawable.rain_night;
                }
                else {
                    image = R.drawable.rain_day;
                }
                break;
            case "extreme rain":
                if (night){
                    image =  R.drawable.rain_night;
                }
                else {
                    image = R.drawable.rain_day;
                }
                break;
            case "thunderstorm":
                image = R.drawable.thunderstorm;
                break;
            case "thunderstorm with light rain":
                image = R.drawable.thunderstorm;
                break;
            case "thunderstorm with rain":
                image = R.drawable.thunderstorm;
                break;
            case "thunderstorm with heavy rain":
                image = R.drawable.thunderstorm;
                break;
            case "light thunderstorm":
                image = R.drawable.thunderstorm;
                break;
            case "heavy thunderstorm":
                image = R.drawable.thunderstorm;
                break;
            case "ragged thunderstorm":
                image = R.drawable.thunderstorm;
                break;
            case "thunderstorm with light drizzle":
                image = R.drawable.thunderstorm;
                break;
            case "thunderstorm with drizzle":
                image = R.drawable.thunderstorm;
                break;
            case "thunderstorm with heavy drizzle":
                image = R.drawable.thunderstorm;
                break;
            case "freezing rain":
                image = R.drawable.freezing_rain;
                break;
            case "snow":
                image = R.drawable.snow;
                break;
            case "mist":
                image = R.drawable.mist;
                break;
            case "overcast clouds":
                image = R.drawable.overcast_clouds;
                break;
            default:
                Log.d(TAG,symbol);
                image = R.drawable.clear_sky_day;
                break;
        }
        return image;
    }
}
