package com.liqihao.readbook.ReadPage.contract;

import com.liqihao.readbook.ReadPage.bean.Book;

/**
 * Created by liqihao on 2017/11/23.
 */

public interface PageContract {
    boolean isShowMenu();
    void disMissState();
    void showState();

    void onDestory();

    interface Presenter<T> {
        Book getBook();
    }
}

