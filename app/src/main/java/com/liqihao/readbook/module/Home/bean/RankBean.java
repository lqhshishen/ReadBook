package com.liqihao.readbook.module.Home.bean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/5.
 */

public class RankBean {
    String RankName;

    List<RankBookBean> data;

    public String getRankName() {
        return RankName;
    }

    public void setRankName(String rankName) {
        RankName = rankName;
    }

    public List<RankBookBean> getData() {
        return data;
    }

    public void setData(List<RankBookBean> data) {
        this.data = data;
    }

    public class RankBookBean {
        int Bookimg;
        String bookName;
        String bookauthor;

        public int getBookimg() {
            return Bookimg;
        }

        public void setBookimg(int bookimg) {
            Bookimg = bookimg;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getBookauthor() {
            return bookauthor;
        }

        public void setBookauthor(String bookauthor) {
            this.bookauthor = bookauthor;
        }
    }

}
