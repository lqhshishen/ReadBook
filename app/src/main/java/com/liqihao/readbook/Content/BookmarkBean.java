package com.liqihao.readbook.Content;

/**
 * Created by liqihao on 2017/11/22.
 */

public class BookmarkBean {
    String bookmarkTitle;
    String bookmarkTime;
    String bookmarkText;
    String bookmarkpage;
    public BookmarkBean (String title,String time, String text,String page) {
        bookmarkTitle = title;
        bookmarkTime = time;
        bookmarkText = text;
        bookmarkpage = page;
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

    public String getBookmarkpage() {
        return bookmarkpage;
    }

    public void setBookmarkpage(String bookmarkpage) {
        this.bookmarkpage = bookmarkpage;
    }
}
