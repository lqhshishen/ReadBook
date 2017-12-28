package com.liqihao.readbook.module.ReadPage.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by liqihao on 2017/11/22.
 */

public class BookmarkBean extends DataSupport{
    private String bookmarkTitle;
    private String bookmarkTime;
    private String bookmarkText;
//    private String bookmarkpage;
    private int bookmarkbyteposition;
    public BookmarkBean (){
    }
    public BookmarkBean (String title,String time, String text,int position) {
        bookmarkTitle = title;
        bookmarkTime = time;
        bookmarkText = text;
//        bookmarkpage = page;
        bookmarkbyteposition = position;
    }

    public int getBookmarkbyteposition() {
        return bookmarkbyteposition;
    }

    public void setBookmarkbyteposition(int bookmarkbyteposition) {
        this.bookmarkbyteposition = bookmarkbyteposition;
    }

    public String getBookmarkTitle() {
        return bookmarkTitle;
    }

    public void setBookmarkTitle(String bookmarkTitle) {
        this.bookmarkTitle = bookmarkTitle;
    }

    public String getBookmarkTime() {
        return bookmarkTime;
    }

    public void setBookmarkTime(String bookmarkTime) {
        this.bookmarkTime = bookmarkTime;
    }

    public String getBookmarkText() {
        return bookmarkText;
    }

    public void setBookmarkText(String bookmarkText) {
        this.bookmarkText = bookmarkText;
    }

//    public String getBookmarkpage() {
//        return bookmarkpage;
//    }
//
//    public void setBookmarkpage(String bookmarkpage) {
//        this.bookmarkpage = bookmarkpage;
//    }
}
