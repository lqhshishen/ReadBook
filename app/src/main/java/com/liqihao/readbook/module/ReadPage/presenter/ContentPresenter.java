package com.liqihao.readbook.module.ReadPage.presenter;

import android.util.Log;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.module.ReadPage.bean.BookmarkBean;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;
import com.liqihao.readbook.module.ReadPage.contract.ContentContract;
import com.liqihao.readbook.module.ReadPage.View.PageFactory;
import com.liqihao.readbook.utils.GetContext;
import com.liqihao.readbook.base.BasePresenter;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/11/23.
 */

public class ContentPresenter extends BasePresenter<ContentContract.Content> implements ContentContract.presenter {
    List<BookmarkBean> data = new ArrayList<>();
    PageFactory mPageFactory;

    @Override
    public void getChapterList(String id) {
        BookApi.getInstance(App.AppContext)
                .getChapterList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Chapter>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Chapter chapter) {
                        view.onShow(chapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public List getBookmarkList() {
        LitePal.initialize(App.AppContext);
        data = DataSupport.findAll(BookmarkBean.class);
        Log.e("testBookMark",view.getClass().toString());
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
