package com.liqihao.readbook.module.ReadPage.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.liqihao.readbook.MainActivity;
import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppPresenter;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;
import com.liqihao.readbook.module.ReadPage.bean.ChapterDetailBean;
import com.liqihao.readbook.module.ReadPage.contract.PageContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/11/23.
 */

public class PagePresenter extends AppPresenter<MainActivity> implements PageContract.Presenter{


    @Inject
    public PagePresenter(MainActivity activity,BookApi bookApi) {
        api = bookApi;
        view = activity;
    }

    @Override
    public void getChapter(String bookId) {
        api.getChapterList(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Chapter>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                        view.showWaitDialog("正在加载");
                    }

                    @Override
                    public void onNext(Chapter chapter) {
                        view.onGetChapterList(chapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,e.toString());
                    }

                    @Override
                    public void onComplete() {
                        view.hideWaitDialog();
                        view.disMissState();
                    }
                });
    }

    @Override
    public void getChapterDetail(String bookId, final String chapterId) {
        api.getChapterDetail(bookId,chapterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChapterDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showWaitDialog("正在加载");
                    }

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onNext(ChapterDetailBean chapterDetailBean) {
                        if (chapterDetailBean.getResult() == null)
                            view.onGetNullChapter(chapterDetailBean);
                        else
                            view.showChapterRead(chapterDetailBean,chapterId);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        view.hideWaitDialog();
                        view.disMissState();
                    }
                });
    }

}
