package com.liqihao.readbook.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.liqihao.readbook.ReadPage.Book;

/**
 * Created by liqihao on 2017/11/13.
 */

public class SPHelper {
    private SharedPreferences config =
            GetContext.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
    private SharedPreferences.Editor configEditor = config.edit();
    private SharedPreferences bookmark =
            GetContext.getContext().getSharedPreferences("bookmark",Context.MODE_PRIVATE);
    private SharedPreferences.Editor bookmarkEditor = bookmark.edit();
    private static SPHelper instance;


    private SPHelper(){}

    public static SPHelper getInstance(){
        if(instance == null) {
            synchronized (SPHelper.class) {
                if (instance == null) {
                    instance = new SPHelper();
                }
            }
        }
        return instance;
    }

    public int getFontSize() {
        return config.getInt("font_size",35);
    }
    public void setFontSize(int size) {
        configEditor.putInt("font_size",size).apply();
    }
    public void setBookmarkStart(String bookName,int position){
        bookmarkEditor.putInt(bookName+"start",position).apply();
    }
    public int getBookmarkStart(String bookName){
        return bookmark.getInt(bookName+"start",0);
    }
    public void setBookmarkEnd(String bookName,int position){
        bookmarkEditor.putInt(bookName+"end",position).apply();
    }
    public int getBookmarkEnd(String bookName){
        return bookmark.getInt(bookName+"end",0);
    }
    public void clearAllBookMarkData(){
        bookmarkEditor.clear().apply();
    }
    public void deleteBookMark(String bookName){
        bookmarkEditor.remove(bookName).apply();
    }
    public String getBookEncoding(Book book){
        return config.getString(book.getPath(),"");
    }
    public void setBookEncoding(Book book,String encoding){
        configEditor.putString(book.getPath(),encoding).apply();
    }



}
