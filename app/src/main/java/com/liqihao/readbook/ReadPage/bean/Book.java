package com.liqihao.readbook.ReadPage.bean;

import java.io.Serializable;

/**
 * Created by liqihao on 2017/11/13.
 */

public class Book implements Serializable{
    private String name;
    private String path;
    private String encoding;
    public Book(){}
    public Book(String name,String path) {
        this.name = name;
        this.path = path;
    }
    public Book(String name,String path,String encoding) {
        this.name = name;
        this.path = path;
        this.encoding = encoding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
