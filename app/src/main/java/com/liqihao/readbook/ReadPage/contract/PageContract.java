package com.liqihao.readbook.ReadPage.contract;

import com.liqihao.readbook.ReadPage.bean.Book;

import java.util.List;

/**
 * Created by liqihao on 2017/11/23.
 */

public interface PageContract {
    boolean isShowMenu();
    void disMissState();
    void showState();
    void checkBookmark();

    void onDestory();

    interface Presenter<T> {
        Book getBook();

        void saveBookmark(String head,String body,String time,int position);

        List<Integer> getMark();
    }
}

