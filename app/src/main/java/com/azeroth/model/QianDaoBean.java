package com.azeroth.model;

import java.util.List;

public class QianDaoBean {
    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * TimeDay : 2018-12-12
         * SignInTiem : 07:59
         * SignOutTime :
         * TrueName : 胡克华
         * headimage : http://usercenter.zhongliko.com/headimage/20162508102538test-2d401550-41e6-4317-bb0aa.png
         * DepName : 知识集群
         */

        private String TimeDay;
        private String SignInTiem;
        private String SignOutTime;
        private String TrueName;
        private String headimage;
        private String DepName;

        public String getTimeDay() {
            return TimeDay;
        }

        public void setTimeDay(String TimeDay) {
            this.TimeDay = TimeDay;
        }

        public String getSignInTiem() {
            return SignInTiem;
        }

        public void setSignInTiem(String SignInTiem) {
            this.SignInTiem = SignInTiem;
        }

        public String getSignOutTime() {
            return SignOutTime;
        }

        public void setSignOutTime(String SignOutTime) {
            this.SignOutTime = SignOutTime;
        }

        public String getTrueName() {
            return TrueName;
        }

        public void setTrueName(String TrueName) {
            this.TrueName = TrueName;
        }

        public String getHeadimage() {
            return headimage;
        }

        public void setHeadimage(String headimage) {
            this.headimage = headimage;
        }

        public String getDepName() {
            return DepName;
        }

        public void setDepName(String DepName) {
            this.DepName = DepName;
        }
    }
}
