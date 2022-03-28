package com.azeroth.utility;

public class API {
    public static class ERP{
        public static String BassAdrees="https://api.zhongliko.com/webservice/erp_webservice.asmx";
        public static class  Action{
            public final static String USERLOGIN = "UserLogin";//用户登录
            public static String JPushGetJMessageTypeV2="JPushGetJMessageTypeV2";
            public  static String JPushGetJMessageLogIndex="JPushGetJMessageLogIndexV2";

            public final static String GetUserHomeMenu  = "GetUserHomeMenu";//获取用户首页权限和所有权限接口
            public final static String GETCOMPANYTRIPOFFULLPICTURE = "GetCompanyTripOFFullPicture";//获取老师信息，用来选择同行老师：
        }
    }

    public static class ERPTask{
        public static String BassAdress="https://erp.zhongliko.com/Task/TaskService.asmx";
        public static  class  Action{
            public static  String getMyTaskListForApp="getMyTaskListForApp";

        }
    }

    public static  class KQ{
        public static String BassAdress="http://os.zhongliko.com/Kproject/WebService/KProjectWebService.asmx";
        public static class  Action{
            public static String getRankingsTop3 = "getRankingsTop3";//考勤前三排行
            public static String GETTODAYOUTSIDEAPPLY = "GetTodayOutsideApply";//获取当天的外勤信息
            public final static String GETFIRSTARRIVE = "getFirstArrive";//早到光荣榜
            public final static String GETSCHEDULBYUSERID = "GetSchedulByUserID";//验证当天排班，打卡方式
            public final static String SIGNIN = "signIn";//用户签到
            public final static String signIn_WiFi = "signIn_WiFi";//用户WIFI签到
            public final static String signOut_WiFi = "signOut_WiFi";//用户WIFI签退
            public final static String CHECKUSERBYPHONE = "CheckUserByPhone";//根据手机号验证用户是否存在

        }
    }
}
