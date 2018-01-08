package com.liqihao.readbook.module.Classification.bean;

/**
 * Created by liqihao on 2018/1/8.
 */

public class ClassicItemBean {
    /**
     * img:图片URL(暂时设置为int本地图片)
     * bookname：书名
     * author:作者
     * 评价：星星数
     * number:评价数量
     */

    private int img;
    private String bookName;
    private String author;
    private double star;
    private int number;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
