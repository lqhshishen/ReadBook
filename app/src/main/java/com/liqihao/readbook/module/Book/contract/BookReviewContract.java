package com.liqihao.readbook.module.Book.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Book.presenter.BookReviewPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public interface BookReviewContract {
    interface view extends BaseView<BookReviewPresenter> {
        void onReceiveComment(CommentList commentList);

        void onLoadMore(CommentList commentList);
    }

    interface presenter {
        void getComment(String id, String page);
    }
}
