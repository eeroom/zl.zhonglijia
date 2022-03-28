package com.azeroth.utility;

public class HttpRequestMessage
{
  public String url;
  public Object parameter;
  public String responseCharsetName="UTF-8";
  public Integer requestTimeout=30000;
    public Action2<java.net.HttpURLConnection,HttpRequestMessage> beforSend;
    public HttpRequestMessage(String url){
        this.url=url;
    }

    public HttpRequestMessage(String url, Object parameter){
        this.url=url;
        this.parameter=parameter;
    }

}
