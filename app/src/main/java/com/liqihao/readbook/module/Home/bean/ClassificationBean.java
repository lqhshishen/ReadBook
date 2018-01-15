package com.liqihao.readbook.module.Home.bean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/5.
 */

public class ClassificationBean {
    private String code;
    private String msg;
    private List<result>result;

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

    public List<result> getResults() {
        return result;
    }

    public void setResults(List<result> results) {
        this.result = results;
    }

    public class result {
        private String id;
        private String classify;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getClassify() {
            return classify;
        }

        public void setClassify(String classify) {
            this.classify = classify;
        }
    }
}
