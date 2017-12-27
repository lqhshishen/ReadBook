package com.liqihao.readbook.module.ReadPage.contract;

import com.liqihao.readbook.module.ReadPage.bean.Book;
import com.liqihao.readbook.module.ReadPage.presenter.PagePresenter;
import com.liqihao.readbook.base.BaseView;

import java.util.List;

/**
 * Created by liqihao on 2017/11/23.
 */

public interface PageContract {
    interface MainView extends BaseView<PagePresenter> {

        boolean isShowMenu();

        void disMissState();

        void showState();

        void checkBookmark();

    }
        interface Presenter {

            Book getBook();

            void saveBookmark(String head,String body,String time,int position);

            List<Integer> getMark();
    }
}

