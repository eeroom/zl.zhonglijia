package com.azeroth.bwl;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestMessage
{
    String url;
    Object parameter;
    String responseCharsetName="UTF-8";
    Integer requestTimeout=3000;

    public HttpRequestMessage(String url){
        this.url=url;
    }

    public HttpRequestMessage(String url, Object parameter){
        this.url=url;
        this.parameter=parameter;
    }

}
