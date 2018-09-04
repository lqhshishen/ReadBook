package com.liqihao.readbook.module.ReadPage.presenter;

import android.util.Log;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppPresenter;
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

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/11/23.
 */

public class ContentPresenter extends AppPresenter<ContentContract.Content> implements ContentContract.presenter {


    @Inject
    public ContentPresenter(ContentContract.Content content,BookApi api) {
        view = content;
        this.api = api;
    }

    List<BookmarkBean> data = new ArrayList<>();
    PageFactory mPageFactory;

    @Override
    public void getChapterList(String id) {
        api.getChapterList(id)
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

}
