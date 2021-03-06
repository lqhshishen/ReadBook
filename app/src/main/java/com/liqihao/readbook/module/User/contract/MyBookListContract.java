package com.liqihao.readbook.module.User.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.User.bean.MyBookList;
import com.liqihao.readbook.module.User.presenter.MyBookListPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public interface MyBookListContract {
    interface View extends BaseView<MyBookListPresenter> {
        void onGetBookList(MyBookList myBookList);
    }
    interface Presenter {
        void getBookList(String auth,String page);
    }
}
