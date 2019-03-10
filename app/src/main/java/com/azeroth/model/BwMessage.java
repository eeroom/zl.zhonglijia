package com.azeroth.model;

import java.util.ArrayList;

public class BwMessage {
    public ArrayList<String> platform;
    public Audience audience;
    public Notification notification;
    public String message;
    public String sms_message;
    public String options;
    public String cid;

    public static class Audience{
        public ArrayList<String> alias;
    }

    public static class Notification{
        public String alert;
        public String title;
        public String builder_id;
        public String large_icon;
        public String intent;
        public Object extras;
    }

}
