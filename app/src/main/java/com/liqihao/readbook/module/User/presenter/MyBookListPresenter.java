package com.liqihao.readbook.module.User.presenter;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.User.bean.MyBookList;
import com.liqihao.readbook.module.User.contract.MyBookListContract;
import com.liqihao.readbook.module.User.ui.MyBookListActivity;
import com.liqihao.readbook.utils.GetContext;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/12/28.
 */

public class MyBookListPresenter extends BasePresenter<MyBookListActivity> implements MyBookListContract.Presenter {
    @Override
    public void getBookList(String auth, String page) {
        BookApi.getInstance(GetContext.getContext())
                .getMyBookList(auth, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBookList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyBookList myBookList) {
                        view.onGetBookList(myBookList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
