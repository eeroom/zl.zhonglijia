package com.azeroth.bwl;

import java.security.PublicKey;

public class API {
    public  static class   Code{
        public static String Ok="1";
    }
    public static class ERPWebService{
        public static String  BaseAddress= "https://api.zhongliko.com/webservice/erp_webservice.asmx";
        public static class   Action{
            public  static String USERLOGIN = "UserLogin";//用户登录
            public  static String GetAppStartImg = "GetAppStartImg";//启动图图片
            public  static String getDepartmentAddressList = "getDepartmentAddressList";//获取部门通讯录
        }
    }
    public class UrlTools {

        //18137732813   231423
        public final static String URL_https = "https://api.zhongliko.com/webservice/erp_webservice.asmx";//正式
        public final static String URL_test_https = "https://api-test.zhongliko.com/webservice/erp_webservice.asmx";//测试服务器

        public final static String URL_sign_out = "http://os.zhongliko.com/Kproject/WebService/KProjectWebService.asmx";//签到服务器 外网
        public final static String URL_sign_in = "http://192.168.6.80/kproject/WebService/KProjectWebService.asmx";//签到服务器 内网


        public final static String NAMESPACE = "http://tempuri.org/";// 命名空间"

        public final static String USERLOGIN = "UserLogin";//用户登录
        public final static String GetAppStartImg = "GetAppStartImg";//启动图图片
        public final static String getDepartmentAddressList = "getDepartmentAddressList";//获取部门通讯录

        public final static String CHANGEPASSWORD = "changePassword";//修改密码

        public final static String SETPASSWORD = "setpassword";//密码重置
        public final static String GETWORKORDERLIST = "GetWorkOrderList";//工单列表（我审批的，我发起的）
        public final static String GETWORKORDERLISTBYPAGE = "GetWorkOrderListByPage";//工单列表（我审批的，我发起的）

        public final static String AddTravel = "AddTravel";//添加行程信息：
        public final static String DeleteTravel = "DeleteTravel";//删除出行信息：
        public final static String UpdateTravel = "UpdateTravel";//修改出行信息：
        public final static String AddTrip = "AddTrip";//添加用户行程：

        public final static String CHECKADDFRIEND = "CheckAddfriend";//验证好友是否可以被添加：

        public final static String DeleteTrip = "DeleteTrip";//删除行程信息：

        public final static String GET_COMPANY_NAME = "GET_Company_Name";//根据关键字获取公司名称：

        public final static String GET_TT_TYPE = "GET_TT_TYPE";//获取行程类型：

        public final static String GET_TEACHER = "GET_Teacher";//获取老师信息，用来选择同行老师：
        public final static String GetstaffTripOFFullPictureNew = "GetstaffTripOFFullPictureNew";//获取老师信息，用来选择同行老师：
        //    public final static String GetstaffTripOFFullPicture = "GetstaffTripOFFullPicture";//获取老师信息，用来选择同行老师：
        public final static String GETCOMPANYTRIPOFFULLPICTURE = "GetCompanyTripOFFullPicture";//获取老师信息，用来选择同行老师：

//    public final static String GET_TIME_TYPE = "GET_Time_Type";//获取时间类型：

        public final static String GETAP_TEACHER_TRIP_NOTICE = "GetAP_TEACHER_TRIP_NOTICE";//获取行程公告

        public final static String GetTrip = "GetTrip";//根据老师的UNION_ID获取老师的行程：     --------->我的行程主界面

        public final static String GetTripDetail = "GetTripDetail";//获取行程列表，依属性查询：   --------》根据时间获取行程

        public final static String GetPersonTrip = "GetPersonTrip";//获取行程详情：
        public final static String GetDepartMentMessage = "GetDepartMentMessage";//获取部门信息:：

        public final static String GetTripbyID = "GetTripbyID";//获取行程详情（所有字段）：

        public final static String GetstaffstatusNew = "GetstaffstatusNew";//根据日期获取老师闲忙状态             --------》他人行程
        public final static String GET_Staff = "GET_Staff";//获取公司员工

        public final static String RESETHEADIMAGE = "Resetheadimage";//根据用户ID，Base64码上传头像

        public final static String GETCITY = "getcity";//获取省份相关信息

        public final static String GETPROVINCE = "getprovince";//获取省份相关信息：

        public final static String RESULT_1 = "1";//同行老师退出行程：result
        public final static String RESULT_17 = "17";//同行老师退出行程：result

        public final static String GETMYUSER = "getMyUser";//参数：UNION_ID:用户自己的ID值，type=1（会员），type=2（学员），type=3（公共池）,rowNum:下标，num:每页获取的条数
        public final static String GETALLUSERBYCODE = "getAllUserBycode";//参数：UNION_ID:用户自己的ID值，type=1（会员），type=2（学员），type=3（公共池）,rowNum:下标，num:每页获取的条数

        public final static String GETMYCONTACTSLIST = "getMyContactsList";//根据用户ID，类型，获取用户的好友列表：-------------------------
        public final static String JPushGetJMessageNoReadNum = "JPushGetJMessageNoReadNum";//根据用户的union_ID，获取消息中心未读消息总数
        public final static String GetMydata = "GetMydata";//根据用户ID，类型，获取用户的好友列表：-------------------------

        public final static String GETDATAVERSION = "GetdataVersion";//获取数据的版本号，确定手机字段是否需要更新   --------》版本对对比

        public final static String GETAPPWEBSERVERVERSION = "getAPPWebServiceVersion";//获得正式链接   --------》版本对对比
        public final static String ChenkAPPIsHaveUpdate = "ChenkAPPIsHaveUpdate";//获取系统版本，对比APP是否需要更新了

        public final static String UpdateTrip = "UpdateTrip";//根据行程ID修改行程：

        public final static String ACCEPTAPPLYFRIEND = "acceptApplyfriend";//接受申请         -----------------------------------

        public final static String APPLYADDFRIEND = "applyaddfriend";//申请添加好友：         ------------------------------------
        public final static String deletefriend = "deletefriend";//删除环信通讯录好友（双向删除）：        ------------------------------------

        public final static String GETFRIENDDETAILS = "getfrienddetails";//获取好友的详细信息（也包括添加好友时的）：------------------------

        public final static String QUITTRIP = "QuitTrips";//同行老师退出行程：----------------------------------》

        public final static String GETAPPLYADDFRIENDCOUNT = "GetApplyaddfriendCount";//获取申请添加好友数量：  //好友界面 申请数量

        public final static String GETMYUSERBYCODE = "getMyUserBycode";//我的会员搜索：

        public final static String GETCOMPANYADDRESS = "getCompanyAddressList";//公司通讯录：

        public final static String CHECKCONVERSATION = "CheckConversation";//公司通讯录——与他聊天：

        public final static String GETMYCONSUMERADDRESSLIST = "getMyConsumerAddressList";//公司通讯录——与他聊天：

        public final static String UPLOADUSERADDRESSLIST = "UploadUserAddressList";//公司通讯录——与他聊天：

        public final static String GETUSEROFSUBORDINATE = "getUserOfsubordinate";//获取我的下属

        public final static String GETMYSUBORDINATEUSER = "getMysubordinateUser";//获取我的下属

        public final static String GETZLJNOTICE = "getZLJNotice";//获取中力家通知
        public final static String GETBACKGROUND = "getBackground";//获取首页皮肤背景
        public final static String GETFIRSTARRIVE = "getFirstArrive";//早到光荣榜
        public final static String getRankingsTop3 = "getRankingsTop3";//考勤前三排行

        public final static String getonlinevideoOFzhongliList = "getonlinevideoOFzhongliList";//我的视频

        public final static String GetZLQVsType = "GetZLQVsType";//视频栏目菜单

        public final static String get_hot_sharch_List = "get_hot_sharch_List";//搜索 – 热搜榜单

        public final static String gethotvideo = "gethotvideo";//搜索 – 热门视频

        public final static String addZLQContent = "addZLQContent";//视频评论

        public final static String AddZLQOnlineVideoLog = "AddZLQOnlineVideoLog";//视频评论


        public final static String CHECKUSERBYPHONE = "CheckUserByPhone";//根据手机号验证用户是否存在
        public final static String GETSCHEDULBYUSERID = "GetSchedulByUserID";//验证当天排班，打卡方式
        public final static String ShowWiFiButton = "ShowWiFiButton";//wifi上报按钮是否显示
        public final static String AddWiFiAddress = "AddWiFiAddress";//添加wifi地址
        public final static String signIn_WiFi = "signIn_WiFi";//用户WIFI签到
        public final static String SIGNIN = "signIn";//用户签到
        public final static String SIGNOUT = "signOut";//用户签退
        public final static String signOut_WiFi = "signOut_WiFi";//用户WIFI签退
        public final static String ADDOUTSIDEAPPLY = "AddOutsideApply";//添加外勤
        public final static String AddOutsideApplyV2 = "AddOutsideApplyV2";//添加外勤
        public final static String ADDLEAVE = "AddLeave";//添加请假信息
        public final static String ADDAPPEAL = "AddAppeal";//添加申诉
        public final static String GETTODAYOUTSIDEAPPLY = "GetTodayOutsideApply";//获取当天的外勤信息
        public final static String GETMYLEAVELIST = "GetMyLeaveList";//获取我的请假列表
        public final static String ExamineOutsideApply = "ExamineOutsideApply";//外勤审批
        public final static String EXAMINELEAVE = "ExamineLeave";//请假审批
        public final static String GETUSERLIST = "GetUserList";//获取联系人
        public final static String GETATTENDLISTBYMONTH = "GetAttendListbyMonth";//按月获取考勤单
        public final static String ATTENDDETAIL = "AttendDetail";//考勤详情
        public final static String GETDETAILS = "GetDetails";//获取详情 异常申诉、请假、外勤
        public final static String EXAMINEAPPEAL = "ExamineAppeal";//申诉审批
        public final static String CHECKWAGESCODE = "checkWagesCode";//提交查工资验证码
        public final static String GETWAGESCODE = "getWagesCode";//获取查工资验证码
        public final static String BACKAPPLY = "BackApply";//撤回申请申诉
        public final static String RiBaoGetKey = "RiBaoGetKey";//根据用户的union_ID，获取登录日报系统和渠道管理系统的秘钥
        public final static String AddMessageGood = "AddMessageGood";//添加好消息；

        public final static String ZLSPGETCONTENTTYPE = "ZLSPgetContentType";//据用户的union_ID，获取用户具有的中力视频栏目权限
        public final static String ZLSPGETCONTENT = "ZLSPgetContent";//根据类别，获取所属当前类别的文章
        public final static String ZLSPADDCOMMENT = "ZLSPAddComment";//中力视频添加评论
        public final static String ZLSPCONTENTISCOLLECT = "ZLSPcontentIsCollect";//验证该视频是否已经被收藏
        public final static String ZLSPDELETECOLLECT = "ZLSPDeleteCollect";//中力视频删除已经收藏的内容
        public final static String ZLSPWRITECOLLECT = "ZLSPWriteCollect";//添加收藏
        public final static String ZLSPWRITEREADLOG = "ZLSPWriteReadLog";//记录浏览的内容
        public final static String ZLSPGETHOTWORD = "ZLSPGetHotWord";//获取热门搜索基本数据，获取标签基本数据
        public final static String ZLSPGETCONTENTBYCODE = "ZLSPgetContentByCode";//根据关键字获取对应的中力视频
        public final static String ZLSPGETCONTENTREADED = "ZLSPGetcontentReaded";//获取中力视频中查看过以及收藏过,评论过的视频列表：
        public final static String ZLSPGETCONTENTCOMMENT = "ZLSPGetcontentComment";//获取中力视频评论过的视频


        public final static String DELTASK = "DelTask";//删除任务
        public final static String DELTASKUSER = "DelTaskUser";//删除参与者或领导者
        public final static String UPDATETASKUSER = "UpdateTaskUser";//更改用户状态

        public final static String GETCODE = "GetCode";//获取验证码
        public final static String CHANGEPHONE = "ChangePhone";//更改绑定设备
        public final static String CHECKPHONETOKEN = "CheckPhoneToken";//验证Token是否待审批


        public final static String GETTASKNUMLIST = "getTaskNumList";//获得任务统计(列表用)，
        public final static String GETTASKLIST = "getTaskList";//获得任务列表
        public final static String GETUSERSLIST = "getUserList";//获得用户列表
        public final static String GETDEPARTMENTLIST = "getDepartmentList";//获得部门列表
        public final static String GETUSERLISTBYLEADERID = "getUserListByLeaderID";//获得部门列表
        public final static String GETBASEDATA = "getBaseData";//获得数据字典
        public final static String EDITTASK = "EditTask";//修改任务
        public final static String ADDTASK = "AddTask";//添加任务
        public final static String GETTASKLISTBYPCLIST = "getTaskListByPCList";//获得任务列表
        public final static String GETTASKDETAIL = "getTaskDetail";//获得任务详情
        public final static String getMyTaskListForApp = "getMyTaskListForApp";//获得个人未完成的任务(app首页)，
        public final static String GETTASKDISCUSSDETAIL = "getTaskDiscussDetail";//获得讨论详情
        public final static String ADDTASKDISCUSSWITHIMG = "AddTaskDiscussWithImg";//添加讨论内容(有附件，app专用)：


        public final static String MPGETCARDTYPE = "MPgetCardType";//获取名片夹的类别：
        public final static String MPGETMYCARD = "MPGetMyCard";//获取我的名片
        public final static String MPGETIMGCONTENT = "MPGetImgContent";//根据名片的base64位编码解析出来名片包含的内容
        public final static String MPADDANDRESETCART = "MPAddAndResetCart";//添加和修改名片信息
        public final static String MPGETMYCARDBYCODE = "MPGetMyCardByCode";//根据名片的关键字获取我的名片
        public final static String MPRESETHEADIMAGE = "MPResetHeadImage";//修改名片的头像
        public final static String MPResetType = "MPResetType";//修改名片所属的分组
        public final static String MPdeleteMyCard = "MPdeleteMyCard";//删除我的名片
        public final static String ERPScanQRCode = "ERPScanQRCode";
        public final static String getZLJNoticeType = "getZLJNoticeType";//获取中力家通知类别
        public final static String getzljIndexNotice = "getzljIndexNotice";//获取首页的一个通知
        public final static String getZLJNoticeByType = "getZLJNoticeByType";//根据类别获取通知
        public final static String JPushGetJMessageTypeV2 = "JPushGetJMessageType";//获取消息的类别
        public final static String JPushGetJMessageType = "JPushGetJMessageTypeV2";//获取消息的类别
        public final static String JPushGetJMessageLog = "JPushGetJMessageLogV2";//获取消息列表根据类别
        public final static String JPushGetJMessageLogV2 = "JPushGetJMessageLog";//获取消息列表根据类别
        public final static String UpdateJMessageStatus = "UpdateJMessageStatus";//修改消息是否已读
        public final static String UpdateJMessageStatusByalia = "UpdateJMessageStatusByalia";//根据别名和消息ID更改消息状态（未读->已读）
        public final static String JPushGetJMessageLogIndex = "JPushGetJMessageLogIndexV2";//根据用户的union_ID，消息类别获取通知记录(首页的)
        public final static String JPushGetJMessageLogIndexV2 = "JPushGetJMessageLogIndex";//根据用户的union_ID，消息类别获取通知记录(首页的)
        public final static String EditUserMenu  = "EditUserMenu";//编辑用户首页显示的权限
        public final static String GetUserHomeMenu  = "GetUserHomeMenu";//获取用户首页权限和所有权限接口


        //中力圈
        public final static String GetZLQMessageList = "GetZLQMessageList";//获取中力圈说说列表
        public final static String DeleteZLQMessagePraise = "DeleteZLQMessagePraise";//中力圈取消点赞
        public final static String DeleteZLQMessageContent = "DeleteZLQMessageContent";//中力圈删除评论
        public final static String DeleteZLQMessage = "DeleteZLQMessage";//中力圈删除说说
        public final static String AddZLQMessagePraise = "AddZLQMessagePraise";//中力圈添加说说点赞
        public final static String AddZLQMessageContent = "AddZLQMessageContent";//中力圈添加说说评论：
        public final static String AddZLQMessage = "AddZLQMessage";//中力圈添加说说


        public final static int RESULT_CODE_3 = 3;//关键字公司名称返回码
        public final static int REQUEST_CODE_102 = 102;//跳转到关键字搜索公司名称
        public final static int REQUEST_CODE_103 = 103;//跳转到行程类型界面
        public final static int RESULT_CODE_4 = 4;//关键字公司名称返回码
        public final static int RESULT_CODE_44 = 44;//关键字公司名称返回码

        //老师筛选
        public final static int REQUEST_CODE_105 = 105;
        public final static int RESULT_CODE_5 = 5;

        //老师信息
        public final static int REQUEST_CODE_106 = 106;
        public final static int RESULT_CODE_6 = 6;

        //行程类型
        public final static int REQUEST_CODE_107 = 107;

        public final static int RESULT_CODE_8 = 8;
        public final static int REQUEST_CODE_108 = 108;

        //省份
        public final static int REQUEST_CODE_109 = 109;
        public final static int RESULT_CODE_9 = 9;

        //省份
        public final static int REQUEST_CODE_220 = 220;
        public final static int RESULT_CODE_22 = 22;

        //标识字符
        public final static int NUMBER_1 = 109;
        public final static int NUMBER_2 = 9;

        //同行讲师全选返回添加行程
        public final static int RESULT_CODE_10 = 10;
        //同行讲师单选或多选跳转
        public final static int RESULT_CODE_101 = 101;
        public final static int RESULT_CODE_11 = 11;

        //TeacherDetailsActivity.this,CheckBoxsActivity.class

        public final static int RESULT_CODE_112 = 112;

        public final static int SUATU_113 = 113;
        public final static int SUATU_114 = 114;

        //提示更新状态
        public final static boolean UPDATE_VERSON = false;

        public final static int SelectPicPopupWindow_1 = 1;
        public final static int SelectPicPopupWindow_2 = 2;
        public final static int SelectPicPopupWindow_3 = 3;//保存通讯录

        public final static String NETWORK_ANOMALY = "网络异常";


        public final static String TAB_NUM = "10000";
        public final static String TAB_NUM_10001 = "10001";
        public final static String TAB_NUM_10002 = "10002";

    }
}
