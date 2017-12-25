package com.liqihao.readbook.Content.contract;

import com.liqihao.readbook.Content.bean.BookmarkBean;
import com.liqihao.readbook.Content.bean.Chapter;
import com.liqihao.readbook.Content.presenter.ContentPresenter;
import com.liqihao.readbook.base.BaseView;

import java.util.List;

/**
 * Created by liqihao on 2017/11/23.
 */

public interface ContentContract {

    interface Content extends BaseView<ContentPresenter> {
//        加载章节
        void loadChapters();

//        获取章节数
        int getChapterNumber(int position,List<Chapter> list);

//        点击目录的时候
        void clickContent();

//        点击书签的时候
        void clickBookmark();
    }

    interface presenter {
        List<BookmarkBean> getBookmarkList();

        void setBookMark();
    }
}
