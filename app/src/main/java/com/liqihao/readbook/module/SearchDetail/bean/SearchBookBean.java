package com.liqihao.readbook.module.SearchDetail.bean;

import com.liqihao.readbook.module.Book.bean.BookBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liqihao on 2018/1/26.
 */

public class SearchBookBean implements Serializable {
    private String code;
    private String msg;
    private List<BookBean>result;

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

    public List<BookBean> getResult() {
        return result;
    }

    public void setResult(List<BookBean> result) {
        this.result = result;
    }
}
