package com.liqihao.readbook.module.Book.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Book.bean.AddBookshelfBean;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Book.presenter.BookDetailPresenter;
import com.liqihao.readbook.module.User.bean.MyBookList;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by liqihao on 2017/12/28.
 */

public interface BookDetailContract {
    interface View extends BaseView<BookDetailPresenter> {
        void onReceiveComment(CommentList commentList);

        void onAddToBookshelf(AddBookshelfBean addBookshelfBean);

        void onReceiveBookList(MyBookList myBookList);

        void sharePopup();

        void shareFriend(SHARE_MEDIA share_media);
    }

    interface Presenter {
        void getComment(String id,String page);

        void AddBookshelf(String id,String auth);

        void getMyBookList(String auth, String page);
    }
}
