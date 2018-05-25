package com.example.mania.myipinfodb.task.parser;

import android.os.Parcel;
import android.os.Parcelable;


public class IpInfoData implements Parcelable {

    private String address;
    private String latitude;
    private String longitude;
    private String countryCode;
    private String countryName;
    private String regionName;
    private String cityName;
    private String zipCode;
    private String timeZone;

    public IpInfoData() {
        address = new String();
        latitude = new String();
        longitude = new String();
        countryCode = new String();
        countryName = new String();
        regionName = new String();
        cityName = new String();
        zipCode = new String();
        timeZone = new String();
    }

    protected IpInfoData(Parcel in) {
        address = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        countryCode = in.readString();
        countryName = in.readString();
        regionName = in.readString();
        cityName = in.readString();
        zipCode = in.readString();
        timeZone = in.readString();
    }

    public static final Creator<IpInfoData> CREATOR = new Creator<IpInfoData>() {
        @Override
        public IpInfoData createFromParcel(Parcel in) {
            return new IpInfoData(in);
        }

        @Override
        public IpInfoData[] newArray(int size) {
            return new IpInfoData[size];
        }
    };

    public String getIpAdresse() {
        return address;
    }

    public void setIpAdresse(String ipAdresse) {
        this.address = ipAdresse;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(countryCode);
        dest.writeString(countryName);
        dest.writeString(regionName);
        dest.writeString(cityName);
        dest.writeString(zipCode);
        dest.writeString(timeZone);
    }
}
