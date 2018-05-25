package com.example.mania.myipinfodb.task.parser;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;


public class MeteoInfoParser {
    public  static final String TAG = MeteoInfoParser.class.getName();
    private String xmldata;
    private XmlPullParserFactory factory;
    public enum Element {Null, time, symbol, temperature, nuages, name}
    private MeteoInfoData tempo;
    private MeteoInfoList data;
    private String city;

    public MeteoInfoList getData(){
        if(data.size() == 0){
            data = parse();
        }
        return data;
    }

    private ArrayList result = new ArrayList();

    public MeteoInfoParser(String xmldata) throws XmlPullParserException {
        this.xmldata = xmldata;
        factory = XmlPullParserFactory.newInstance();
        result = null;
        data = new MeteoInfoList();
    }

    private MeteoInfoList parse(){
        MeteoInfoList dataList = new MeteoInfoList();

        try{
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader(this.xmldata));

            int eventType = xpp.getEventType();

            Element lastElement = Element.Null;

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(TAG,"Lancement du Parsing sur le XML");
                        break;
                    case XmlPullParser.START_TAG:
                        if(xpp.getName().equals("time")){
                            lastElement = Element.time;
                            tempo = new MeteoInfoData();
                            tempo.setCity(city);
                            tempo.setDate_start(xpp.getAttributeValue(0));
                            tempo.setDate_end(xpp.getAttributeValue(1));
                        }
                        else if(xpp.getName().equals("symbol")) {
                            lastElement = Element.symbol;
                            tempo.setSymbol(xpp.getAttributeValue(1));
                        }else if (xpp.getName().equals("temperature")) {
                            lastElement = Element.temperature;
                            tempo.setTemperature(xpp.getAttributeValue(1));
                        }else if (xpp.getName().equals("clouds")){
                            lastElement = Element.nuages;
                            tempo.setNuages(xpp.getAttributeValue(0));
                        }else if (xpp.getName().equals("name")){
                            city = xpp.getText();
                            lastElement = Element.name;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(lastElement.equals(Element.name)){
                            city = (xpp.getText());
                            int indice = 0;
                            for (MeteoInfoData datas : dataList){
                                dataList.get(indice).setCity(city);
                                indice++;
                            }
                            lastElement = Element.Null;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        lastElement = Element.Null;
                        if (xpp.getName().equals("time")) {
                            dataList.add(tempo);
                            tempo = null;
                        }
                        break;
                }
                eventType = xpp.next();
            }
            Log.d(TAG,"Fin du parsing");
            return dataList;


        } catch (XmlPullParserException e) {
            Log.d(TAG,"Erreur dans le parsing");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(TAG,"Erreur dans le parsing");
            e.printStackTrace();
        }
        return dataList;
    }


}
