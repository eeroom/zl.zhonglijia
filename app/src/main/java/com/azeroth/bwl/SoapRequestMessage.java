package com.azeroth.bwl;

import org.ksoap2.SoapEnvelope;

import java.util.HashMap;

public class SoapRequestMessage extends  HttpRequestMessage {
    String namesp= "http://tempuri.org/";
    String action;
    int soapVersion=SoapEnvelope.VER11;
    public SoapRequestMessage(String url){
        super(url);
    }

    public SoapRequestMessage(String url, HashMap<String, String> parameter){
        super(url,parameter);
    }

    public void setAction(String action)
    {
        this.action=action;
    }
}
