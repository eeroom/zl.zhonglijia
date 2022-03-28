package com.azeroth.model;

public class JMessageBean {
    //        "ID": "17938",--通过ID更新阅读状态
//        "IsRead": "0",--等于0表示未读 需要更新阅读状态 1表示已读，不用调更新状态接口
//        "NewTime": "3小时前",--显示
//        "JMessageTypeID": "2",--通知类型id，如果等于1，会判断Resource（1.考勤 2.申诉 3外勤）值，再根据ObjectID请求对应详情页（考勤，申诉，外勤）等于2，3，8，9，10，直接用ObjectID请求对应详情页，等于其它值，直接webview打开Resource
//        "HeadImg": "http://usercenter.zhongliko.com/headimage/20efe8991bfb438ea5ab29fbc94edd2a.png",--显示的头像，没有值的话显示默认头像
//        "Title": "您收到[赵泽辉]发布的任务：测试",--显示
//        "RemarkMessage": "",--如果通知类型为好消息，此处会有值，为好消息正文
//        "ObjectID": "876",--对象id
//        "Resource": ""-可能用于系统考勤（1，2，3）和其它类型（URL）

    private String ID;
    private String IsRead;
    private String NewTime;
    private String JMessageTypeID;
    private String HeadImg;
    private String Title;
    private String RemarkMessage;
    private String ObjectID;
    private String Resource;
    private String Types;

    public String getTypes() {
        return Types;
    }

    public void setTypes(String types) {
        Types = types;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIsRead() {
        return IsRead;
    }

    public void setIsRead(String isRead) {
        IsRead = isRead;
    }

    public String getNewTime() {
        return NewTime;
    }

    public void setNewTime(String newTime) {
        NewTime = newTime;
    }

    public String getJMessageTypeID() {
        return JMessageTypeID;
    }

    public void setJMessageTypeID(String JMessageTypeID) {
        this.JMessageTypeID = JMessageTypeID;
    }

    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String headImg) {
        HeadImg = headImg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRemarkMessage() {
        return RemarkMessage;
    }

    public void setRemarkMessage(String remarkMessage) {
        RemarkMessage = remarkMessage;
    }

    public String getObjectID() {
        return ObjectID;
    }

    public void setObjectID(String objectID) {
        ObjectID = objectID;
    }

    public String getResource() {
        return Resource;
    }

    public void setResource(String resource) {
        Resource = resource;
    }
}
