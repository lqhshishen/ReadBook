package com.liqihao.readbook.module.Login.bean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/12.
 */

public class MobileReg {
    private String code;
    private String msg;
    private List<resultBean>result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<resultBean> getResult() {
        return result;
    }

    public void setResult(List<resultBean> result) {
        this.result = result;
    }

    public class resultBean {
        String auth;
        String mobile;

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
