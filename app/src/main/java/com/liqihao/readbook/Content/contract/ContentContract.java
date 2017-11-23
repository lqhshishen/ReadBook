package com.liqihao.readbook.Content.contract;

import com.liqihao.readbook.Content.bean.BookmarkBean;
import com.liqihao.readbook.Content.bean.Chapter;

import java.util.List;

/**
 * Created by liqihao on 2017/11/23.
 */

public interface ContentContract {
    void loadChapters();
    int getChapterNumber(int position,List<Chapter> list);

    interface presenter<T> {
        List<BookmarkBean> getBookmarkList();
    }


}
