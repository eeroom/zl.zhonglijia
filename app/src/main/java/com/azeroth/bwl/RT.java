package com.azeroth.bwl;


public class RT<T>
{
    public int code;
    public String msg;
    public T data;
    public Object extension;

    public enum RTCode
    {
        Ok,
        Error,
        NoAuthentication,
        NoAuthorization,
    }

}


