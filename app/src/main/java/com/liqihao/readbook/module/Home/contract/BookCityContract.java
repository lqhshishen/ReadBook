package com.liqihao.readbook.module.Home.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Home.presenter.BookCityPresenter;

/**
 * Created by liqihao on 2017/12/27.
 */

public interface BookCityContract{
    interface View extends BaseView<BookCityPresenter> {

    }
    interface Presenter {
        void getBanner();


        void getBean();
    }
}
