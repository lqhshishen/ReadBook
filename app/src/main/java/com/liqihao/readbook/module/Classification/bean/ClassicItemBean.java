package com.liqihao.readbook.module.Classification.bean;

import com.liqihao.readbook.module.Book.bean.BookBean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/8.
 */

public class ClassicItemBean {
    /**
     *  "code": "0",
     "msg": "success",
     "result": [
     {
     "id": "57",
     "bookname": "都市逍遥客",
     "url": "http://www.ybdu.com/xiaoshuo/2/2602/",
     "classify": "都市小说",
     "classify_id": "3",
     "wordcount": "",
     "status": "1",
     "author": "随缘·珍重",
     "brief": "小孩实在是没有想到，这个没人敢招惹的家伙，居然会这么好说话，声音不但低了下来，也越发地颤抖起来。\r\n　　“飞哥，我是想问问你，我怎么才能、怎么才能不让那些城管没收我的三轮呢？” 平淡生活的强者，但他总是要遇到不平淡的事。",
     "icon": "15112504952602s.jpg",
     "updatetime": "2010-08-27 08:52",
     "dateline": "1511250495"
     }
     */


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
