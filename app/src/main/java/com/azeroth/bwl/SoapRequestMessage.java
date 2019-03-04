package com.azeroth.bwl;

import org.ksoap2.SoapEnvelope;

import java.util.HashMap;

public class SoapRequestMessage    {
     String url;
    HashMap<String, String> parameter=new HashMap<String, String>();
    String responseCharsetName="UTF-8";
    Integer requestTimeout=3000;
    String namesp= "http://tempuri.org/";
    String action;
    int soapVersion=SoapEnvelope.VER11;
    public SoapRequestMessage(String url){
        this.url=url;
    }

    public SoapRequestMessage(String url, HashMap<String, String> parameter){
        this(url);
        this.parameter=parameter;

    }
}
