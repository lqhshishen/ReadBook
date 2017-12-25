package com.liqihao.readbook.Content.presenter;

import android.util.Log;

import com.liqihao.readbook.Content.Fragment.Content;
import com.liqihao.readbook.Content.bean.BookmarkBean;
import com.liqihao.readbook.Content.contract.ContentContract;
import com.liqihao.readbook.ReadPage.View.PageFactory;
import com.liqihao.readbook.Util.GetContext;
import com.liqihao.readbook.base.BasePresenter;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/11/23.
 */

public class ContentPresenter extends BasePresenter<ContentContract.Content> implements ContentContract.presenter {
    List<BookmarkBean> data = new ArrayList<>();
    PageFactory mPageFactory;
    Content content;

    @Override
    public List getBookmarkList() {
        LitePal.initialize(GetContext.getContext());
        data = DataSupport.findAll(BookmarkBean.class);
//        DataSupport.deleteAll(BookmarkBean.class);
        return data;
    }

    @Override
    public void setBookMark() {
        mPageFactory = PageFactory.getInstance();
    }

    @Override
    public void attachView(ContentContract.Content view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
