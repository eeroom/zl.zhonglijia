package com.azeroth.model;

import java.io.Serializable;
import java.util.List;

public class TaskListBean implements Serializable {

    /**
     * message : 查询成功
     * data : [{"ID":"547","CreateUserID":"171","ReceptUserID":"171","Type":"2","Name":"协调任务有附件","Detail":"测试","Status":"2","EventLevels":"2","OrderLevels":"2","IsRepeat":"0","RepeatEveryDay":"0","RepeatWeek":"0","RepeatMonth":"0","IsCall":"0","CallTime":"","CreateTime":"2017-06-28 18:38","StartTime":"2017-06-28 18:37","EndTime":"2017-07-08 18:37","Target":"","Require":"","Result":"","IsDelete":"0","CreateDate":"06-28","StartDate":"06-28","EndDate":"07-08","CreateName":"夏卫超","ReceiveName":"夏卫超","statusName":"进行中","statusColor":"#67C596","EventName":"重要不紧急","EventColor":"#FF9933","TypeName":"协调任务","rownum":"1"}]
     * UserList : [{"UserID":"174","UserName":"郑少博","HeadImage":"https://erp.zhongliko.com/uploadfile/touxiang.jpg","TaskID":"547","Details":"郑少博的工作内容","ID":"9"},{"UserID":"171","UserName":"夏卫超","HeadImage":"https://api.zhongliko.com/headimage/20175028095014test-0649d08e-e69d-4daf-9d3da.png","TaskID":"547","Details":"夏卫超的工作内容","ID":"10"}]
     * LeaderList : [{"UserID":"30","UserName":"洪雪鹏","HeadImage":"https://api.zhongliko.com/headimage/20174612094625test-a803d344-d3615220080255a.png","TaskID":"547","Details":"adsf","ID":"13"}]
     * DiscussList : [{"UserID":"2","UserName":"李建辉","HeadImage":"https://api.zhongliko.com/headimage/20163431083425test-a803d344-d3613922283006a.png","TaskID":"547","CreateTime":"2017-06-28 00:00","ID":"4","Contents":"adsf","rownum":"1"}]
     */

    private String message;
    private List<DataBean> data;
    private List<UserListBean> UserList;
    private List<LeaderListBean> LeaderList;
    private List<DiscussListBean> DiscussList;
    private List<TaskAttachBean> TaskAttach;

    public List<TaskAttachBean> getTaskAttach() {
        return TaskAttach;
    }

    public void setTaskAttach(List<TaskAttachBean> taskAttach) {
        TaskAttach = taskAttach;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<UserListBean> getUserList() {
        return UserList;
    }

    public void setUserList(List<UserListBean> UserList) {
        this.UserList = UserList;
    }

    public List<LeaderListBean> getLeaderList() {
        return LeaderList;
    }

    public void setLeaderList(List<LeaderListBean> LeaderList) {
        this.LeaderList = LeaderList;
    }

    public List<DiscussListBean> getDiscussList() {
        return DiscussList;
    }

    public void setDiscussList(List<DiscussListBean> DiscussList) {
        this.DiscussList = DiscussList;
    }

    public static class DataBean implements Serializable {
        /**
         * ID : 547
         * CreateUserID : 171
         * ReceptUserID : 171
         * Type : 2
         * Name : 协调任务有附件
         * Detail : 测试
         * Status : 2
         * EventLevels : 2
         * OrderLevels : 2
         * IsRepeat : 0
         * RepeatEveryDay : 0
         * RepeatWeek : 0
         * RepeatMonth : 0
         * IsCall : 0
         * CallTime :
         * CreateTime : 2017-06-28 18:38
         * StartTime : 2017-06-28 18:37
         * EndTime : 2017-07-08 18:37
         * Target :
         * Require :
         * Result :
         * IsDelete : 0
         * CreateDate : 06-28
         * StartDate : 06-28
         * EndDate : 07-08
         * CreateName : 夏卫超
         * ReceiveName : 夏卫超
         * statusName : 进行中
         * statusColor : #67C596
         * EventName : 重要不紧急
         * EventColor : #FF9933
         * TypeName : 协调任务
         * rownum : 1
         */

        private String ID;
        private String CreateUserID;
        private String ReceptUserID;
        private String Type;
        private String Name;
        private String Detail;
        private String Status;
        private String EventLevels;
        private String OrderLevels;
        private String IsRepeat;
        private String RepeatEveryDay;
        private String RepeatWeek;
        private String RepeatMonth;
        private String IsCall;
        private String CallTime;
        private String CreateTime;
        private String StartTime;
        private String EndTime;
        private String Target;
        private String Require;
        private String Result;
        private String IsDelete;
        private String CreateDate;
        private String StartDate;
        private String EndDate;
        private String CreateName;
        private String ReceiveName;
        private String statusName;
        private String statusColor;
        private String EventName;
        private String EventColor;
        private String TypeName;
        private String Count;
        private String rownum;

        public String getCount() {
            return Count;
        }

        public void setCount(String count) {
            Count = count;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(String CreateUserID) {
            this.CreateUserID = CreateUserID;
        }

        public String getReceptUserID() {
            return ReceptUserID;
        }

        public void setReceptUserID(String ReceptUserID) {
            this.ReceptUserID = ReceptUserID;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getDetail() {
            return Detail;
        }

        public void setDetail(String Detail) {
            this.Detail = Detail;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getEventLevels() {
            return EventLevels;
        }

        public void setEventLevels(String EventLevels) {
            this.EventLevels = EventLevels;
        }

        public String getOrderLevels() {
            return OrderLevels;
        }

        public void setOrderLevels(String OrderLevels) {
            this.OrderLevels = OrderLevels;
        }

        public String getIsRepeat() {
            return IsRepeat;
        }

        public void setIsRepeat(String IsRepeat) {
            this.IsRepeat = IsRepeat;
        }

        public String getRepeatEveryDay() {
            return RepeatEveryDay;
        }

        public void setRepeatEveryDay(String RepeatEveryDay) {
            this.RepeatEveryDay = RepeatEveryDay;
        }

        public String getRepeatWeek() {
            return RepeatWeek;
        }

        public void setRepeatWeek(String RepeatWeek) {
            this.RepeatWeek = RepeatWeek;
        }

        public String getRepeatMonth() {
            return RepeatMonth;
        }

        public void setRepeatMonth(String RepeatMonth) {
            this.RepeatMonth = RepeatMonth;
        }

        public String getIsCall() {
            return IsCall;
        }

        public void setIsCall(String IsCall) {
            this.IsCall = IsCall;
        }

        public String getCallTime() {
            return CallTime;
        }

        public void setCallTime(String CallTime) {
            this.CallTime = CallTime;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getTarget() {
            return Target;
        }

        public void setTarget(String Target) {
            this.Target = Target;
        }

        public String getRequire() {
            return Require;
        }

        public void setRequire(String Require) {
            this.Require = Require;
        }

        public String getResult() {
            return Result;
        }

        public void setResult(String Result) {
            this.Result = Result;
        }

        public String getIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(String IsDelete) {
            this.IsDelete = IsDelete;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getStartDate() {
            return StartDate;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public String getCreateName() {
            return CreateName;
        }

        public void setCreateName(String CreateName) {
            this.CreateName = CreateName;
        }

        public String getReceiveName() {
            return ReceiveName;
        }

        public void setReceiveName(String ReceiveName) {
            this.ReceiveName = ReceiveName;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getStatusColor() {
            return statusColor;
        }

        public void setStatusColor(String statusColor) {
            this.statusColor = statusColor;
        }

        public String getEventName() {
            return EventName;
        }

        public void setEventName(String EventName) {
            this.EventName = EventName;
        }

        public String getEventColor() {
            return EventColor;
        }

        public void setEventColor(String EventColor) {
            this.EventColor = EventColor;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public String getRownum() {
            return rownum;
        }

        public void setRownum(String rownum) {
            this.rownum = rownum;
        }
    }

    public static class UserListBean implements Serializable {
        /**
         * UserID : 174
         * UserName : 郑少博
         * HeadImage : https://erp.zhongliko.com/uploadfile/touxiang.jpg
         * TaskID : 547
         * Details : 郑少博的工作内容
         * ID : 9
         */

        private String UserID;
        private String UserName;
        private String HeadImage;
        private String TaskID;
        private String Details;
        private String ID;
        private String UserType;
        private String TaskUserStatusName;
        private String Status;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getTaskUserStatusName() {
            return TaskUserStatusName;
        }

        public void setTaskUserStatusName(String taskUserStatusName) {
            TaskUserStatusName = taskUserStatusName;
        }

        public String getUserType() {
            return UserType;
        }

        public void setUserType(String userType) {
            UserType = userType;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getHeadImage() {
            return HeadImage;
        }

        public void setHeadImage(String HeadImage) {
            this.HeadImage = HeadImage;
        }

        public String getTaskID() {
            return TaskID;
        }

        public void setTaskID(String TaskID) {
            this.TaskID = TaskID;
        }

        public String getDetails() {
            return Details;
        }

        public void setDetails(String Details) {
            this.Details = Details;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }

    public static class LeaderListBean implements Serializable {
        /**
         * UserID : 30
         * UserName : 洪雪鹏
         * HeadImage : https://api.zhongliko.com/headimage/20174612094625test-a803d344-d3615220080255a.png
         * TaskID : 547
         * Details : adsf
         * ID : 13
         */

        private String UserID;
        private String UserName;
        private String HeadImage;
        private String TaskID;
        private String Details;
        private String ID;
        private String UserType;
        private String TaskUserStatusName;
        private String Status;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getTaskUserStatusName() {
            return TaskUserStatusName;
        }

        public String getUserType() {
            return UserType;
        }

        public void setUserType(String userType) {
            UserType = userType;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getHeadImage() {
            return HeadImage;
        }

        public void setHeadImage(String HeadImage) {
            this.HeadImage = HeadImage;
        }

        public String getTaskID() {
            return TaskID;
        }

        public void setTaskID(String TaskID) {
            this.TaskID = TaskID;
        }

        public String getDetails() {
            return Details;
        }

        public void setDetails(String Details) {
            this.Details = Details;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }

    public class TaskAttachBean implements Serializable {

        /**
         * ID : 2
         * TaskID : 535
         * CreateUserID : 171
         * CreateTime : 2017/6/29 14:16:25
         * FileName : bug.txt
         * FileAddress : https://erp.zhongliko.com//Task/Attachment/131431905370818614.txt
         * FileExtend : .txt
         */

        private String ID;
        private String TaskID;
        private String CreateUserID;
        private String CreateTime;
        private String FileName;
        private String FileAddress;
        private String FileExtend;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getTaskID() {
            return TaskID;
        }

        public void setTaskID(String TaskID) {
            this.TaskID = TaskID;
        }

        public String getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(String CreateUserID) {
            this.CreateUserID = CreateUserID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getFileAddress() {
            return FileAddress;
        }

        public void setFileAddress(String FileAddress) {
            this.FileAddress = FileAddress;
        }

        public String getFileExtend() {
            return FileExtend;
        }

        public void setFileExtend(String FileExtend) {
            this.FileExtend = FileExtend;
        }
    }

    public static class DiscussListBean implements Serializable {
        /**
         * UserID : 2
         * UserName : 李建辉
         * HeadImage : https://api.zhongliko.com/headimage/20163431083425test-a803d344-d3613922283006a.png
         * TaskID : 547
         * CreateTime : 2017-06-28 00:00
         * ID : 4
         * Contents : adsf
         * rownum : 1
         */

        private String UserID;
        private String UserName;
        private String HeadImage;
        private String TaskID;
        private String CreateTime;
        private String ID;
        private String Contents;
        private String rownum;

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getHeadImage() {
            return HeadImage;
        }

        public void setHeadImage(String HeadImage) {
            this.HeadImage = HeadImage;
        }

        public String getTaskID() {
            return TaskID;
        }

        public void setTaskID(String TaskID) {
            this.TaskID = TaskID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getContents() {
            return Contents;
        }

        public void setContents(String Contents) {
            this.Contents = Contents;
        }

        public String getRownum() {
            return rownum;
        }

        public void setRownum(String rownum) {
            this.rownum = rownum;
        }
    }
}
