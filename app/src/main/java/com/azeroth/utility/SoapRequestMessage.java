package com.azeroth.utility;

import org.ksoap2.SoapEnvelope;

import java.util.HashMap;

public class SoapRequestMessage {
    public String url;
    public HashMap<String, String> parameter=new HashMap<String, String>();
    public String responseCharsetName="UTF-8";
    public Integer requestTimeout=3000;
    public String namesp= "http://tempuri.org/";
    public String action;
    public int soapVersion=SoapEnvelope.VER11;
    public SoapRequestMessage(String url){
        this.url=url;
    }

    public SoapRequestMessage(String url, HashMap<String, String> parameter){
        this(url);
        this.parameter=parameter;

    }
}
