package com.azeroth.bwl;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestMessage
{
    private String url;
    private HashMap<String, String> parameter=new HashMap<String, String>();
    String responseCharsetName="UTF-8";
    Integer requestTimeout=3000;

    public HttpRequestMessage(String url){
        this.url=url;
    }

    public HttpRequestMessage(String url, HashMap<String, String> parameter){
        this.url=url;
        this.parameter=parameter;
    }

    public void SetParameter(String name,String value){
        this.parameter.put(name,value);
    }

    public String getUrl(){
        return  this.url;
    }

    public HashMap<String,String> getParameter(){
        return  this.parameter;
    }
}
