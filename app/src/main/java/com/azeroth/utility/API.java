package com.azeroth.utility;

public class API {
    public static class ERP{
        public static String BassAdrees="https://api.zhongliko.com/webservice/erp_webservice.asmx";
        public static class  Action{
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
        }
    }
}
