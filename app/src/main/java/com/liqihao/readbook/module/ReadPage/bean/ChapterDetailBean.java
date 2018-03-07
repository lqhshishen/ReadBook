package com.liqihao.readbook.module.ReadPage.bean;

import java.io.Serializable;

/**
 * Created by liqihao on 2018/3/1.
 */

public class ChapterDetailBean implements Serializable  {
    private String code;

    private String msg;

    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
