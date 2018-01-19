package com.liqihao.readbook.module.Login.bean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/18.
 */

public class SendCodeBean {
    private String code;
    private String success;
    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public class ResultBean {
        String vcode;

        public String getVcode() {
            return vcode;
        }

        public void setVcode(String vcode) {
            this.vcode = vcode;
        }
    }
}
