package com.liqihao.readbook.module.User.bean;

import com.liqihao.readbook.module.Book.bean.BookBean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/19.
 */

public class MyBookList {
    private String code;
    private String success;
    private List<ResultBean>result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public class ResultBean {
        String id;
        String nid;
        String hadread;
        String uid;
        String dateline;
        BookBean book;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getHadread() {
            return hadread;
        }

        public void setHadread(String hadread) {
            this.hadread = hadread;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public BookBean getBook() {
            return book;
        }

        public void setBook(BookBean book) {
            this.book = book;
        }
    }
}
