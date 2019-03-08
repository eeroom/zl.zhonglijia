package com.azeroth.model;

import java.util.List;

public class UserAllBean {
    private String GroupName;
    private List<PrivilegeBean> Privilege;

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public List<PrivilegeBean> getPrivilege() {
        return Privilege;
    }

    public void setPrivilege(List<PrivilegeBean> Privilege) {
        this.Privilege = Privilege;
    }

    public static class PrivilegeBean {
        /**
         * ID : 7
         * NAME : 点赞
         * URL : https://app.zhongliko.com/ShareVoting.html?ID=test-a803d344-d3613430380816
         * ICON :
         * IS_NATIVE : 0
         */

        private String ID;
        private String NAME;
        private String URL;
        private String ICON;
        private String IS_NATIVE;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public String getICON() {
            return ICON;
        }

        public void setICON(String ICON) {
            this.ICON = ICON;
        }

        public String getIS_NATIVE() {
            return IS_NATIVE;
        }

        public void setIS_NATIVE(String IS_NATIVE) {
            this.IS_NATIVE = IS_NATIVE;
        }
    }
}
