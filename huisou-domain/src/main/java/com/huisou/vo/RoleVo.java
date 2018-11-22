package com.huisou.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * author： xueyuan
 * date  ： 2017-07-18 上午11:32
 */
public class RoleVo implements Serializable {

    private Integer roleid;
    private String rolename;
    private Integer rolestatus;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getRolestatus() {
        return rolestatus;
    }

    public void setRolestatus(Integer rolestatus) {
        this.rolestatus = rolestatus;
    }


    public static class RoleParam implements Serializable {
        private Integer roleid;
        private String rolename;
        private Map<String, Object> auths;
        private String begintime;
        private String endtime;


        public Integer getRoleid() {
            return roleid;
        }

        public void setRoleid(Integer roleid) {
            this.roleid = roleid;
        }

        public String getRolename() {
            return rolename;
        }

        public void setRolename(String rolename) {
            this.rolename = rolename;
        }

        public Map<String, Object> getAuths() {
            return auths;
        }

        public void setAuths(Map<String, Object> auths) {
            this.auths = auths;
        }

        public String getBegintime() {
            return begintime;
        }

        public void setBegintime(String begintime) {
            this.begintime = begintime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }
    }
}
