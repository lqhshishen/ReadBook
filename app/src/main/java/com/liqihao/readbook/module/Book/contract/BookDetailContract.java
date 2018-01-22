package com.liqihao.readbook.module.Book.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Book.presenter.BookDetailPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public interface BookDetailContract {
    interface View extends BaseView<BookDetailPresenter> {
        void onReceiveComment(CommentList commentList);
    }

    interface Presenter {
        void getComment(String id,String page);
    }
}
