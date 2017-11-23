package com.liqihao.readbook.Content.presenter;

import com.liqihao.readbook.Content.bean.BookmarkBean;
import com.liqihao.readbook.Content.contract.ContentContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/11/23.
 */

public class ContentPresenter implements ContentContract.presenter {

    BookmarkBean bookmarkBean = new BookmarkBean
            ("第一章 xxxx","11-22 14:49","大叔大婶大所大所多","14");
    List<BookmarkBean> data = new ArrayList<>();

    @Override
    public List getBookmarkList() {
        data.add(bookmarkBean);
        return data;
    }

}
