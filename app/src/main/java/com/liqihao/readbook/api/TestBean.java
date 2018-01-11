package com.liqihao.readbook.api;

import java.util.List;

/**
 * Created by liqihao on 2018/1/10.
 */

public class TestBean {
    private String code;
    private String msg;
    private List<result> results;

    public List<result> getResults() {
        return results;
    }

    public void setResults(List<result> results) {
        this.results = results;
    }

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

    public class result {
        String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
