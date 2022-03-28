package com.azeroth.model;

public class JpushNoticeTypeBean {
    private String ID;
    private String Name;
    private String IconUrl;
    private String NoReadNum;
    private String isReadNum;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }

    public String getNoReadNum() {
        return NoReadNum;
    }

    public void setNoReadNum(String noReadNum) {
        NoReadNum = noReadNum;
    }

    public String getIsReadNum() {
        return isReadNum;
    }

    public void setIsReadNum(String isReadNum) {
        this.isReadNum = isReadNum;
    }
}
