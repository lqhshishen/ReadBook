package com.liqihao.readbook.module.ReadPage.contract;

import com.liqihao.readbook.module.ReadPage.bean.BookmarkBean;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;
import com.liqihao.readbook.module.ReadPage.presenter.ContentPresenter;
import com.liqihao.readbook.base.BaseView;

import java.util.List;

/**
 * Created by liqihao on 2017/11/23.
 */

public interface ContentContract {

    interface Content extends BaseView<ContentPresenter> {
//        加载章节
        void loadChapters();

//        点击目录的时候
        void clickContent();

//        点击书签的时候
        void clickBookmark();

        void onShow(Chapter chapter);
    }

    interface presenter {
        void getChapterList(String id);

        List<BookmarkBean> getBookmarkList();

        void setBookMark();
    }
}
