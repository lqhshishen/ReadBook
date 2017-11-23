package com.liqihao.readbook.ReadPage.presenter;

import com.liqihao.readbook.ReadPage.bean.Book;
import com.liqihao.readbook.ReadPage.contract.PageContract;

/**
 * Created by liqihao on 2017/11/23.
 */

public class PagePresenter implements PageContract.Presenter{

    Book book = new Book("chenxizhijian",
            "/storage/emulated/0/Download/晨曦之剑.txt","GB18030");

    @Override
    public Book getBook() {
        return book;
    }
}
