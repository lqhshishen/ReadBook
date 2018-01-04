package com.liqihao.readbook.module.Home.bean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/4.
 */

public class BookCityBean {
    /**
     *title:标题
     */
    private String title;

    private List<BookCityBookBean> result;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BookCityBookBean> getResult() {
        return result;
    }

    public void setResult(List<BookCityBookBean> result) {
        this.result = result;
    }

    public static class BookCityBookBean {
        /**
         * img:图片URL(暂时设置为int本地图片)
         * bookname：书名
         * bookCode:书本代码
         * price: 价格
         */
        private int img;
        private String bookname;
        private String bookCode;
        private String price;

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }

        public String getBookCode() {
            return bookCode;
        }

        public void setBookCode(String bookCode) {
            this.bookCode = bookCode;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
