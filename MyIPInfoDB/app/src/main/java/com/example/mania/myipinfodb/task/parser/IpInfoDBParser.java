package com.example.mania.myipinfodb.task.parser;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;


public class IpInfoDBParser {

    public  static final String TAG = IpInfoDBParser.class.getName();
    private IpInfoData data;
    private String xmldata;
    private XmlPullParserFactory factory;
    public enum Element {Null, Other, address, CountryCode, CountryName, RegionName, CityName, ZipCode, TimeZone, Latitude, Longitude}

    public IpInfoDBParser(String xmldata) throws XmlPullParserException{
        this.xmldata = xmldata;
        factory = XmlPullParserFactory.newInstance();
        data = null;
    }

    private IpInfoData parse() {
        IpInfoData result = new IpInfoData();
        try{
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader(this.xmldata));

            int eventType = xpp.getEventType();

            Element lastElement = Element.Null;

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if(xpp.getName().equals("lat")) {
                            lastElement = Element.Latitude;
                        }else if (xpp.getName().equals("lng")) {
                            lastElement = Element.Longitude;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(lastElement.equals(Element.Latitude)) {
                            result.setLatitude(xpp.getText());
                        }else if (lastElement.equals(Element.Longitude)) {
                            result.setLongitude(xpp.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        lastElement = Element.Null;
                        break;
                }
                eventType = xpp.next();
            }
        }catch (XmlPullParserException | IOException e) {
            Log.e(TAG,"error while parsing ", e);
        }
        return  result;
    }

    public IpInfoData getData(){
        if(data ==null){
           data = parse();
        }
        return data;
    }
}
