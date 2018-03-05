package com.liqihao.readbook.module.Book.presenter;

import android.util.Log;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Book.contract.WholeContentContract;
import com.liqihao.readbook.module.Book.ui.WholeContentActivity;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;
import com.liqihao.readbook.utils.GetContext;
import com.liqihao.readbook.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/12/28.
 */

public class WholeContentPresenter extends BasePresenter<WholeContentActivity> implements WholeContentContract.presenter{


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
                        Log.e("testChapter", chapter.getResult().get(0).getChapter_name());
                        view.onGetChapter(chapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
