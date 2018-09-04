package com.liqihao.readbook.base;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class AppPresenter<T extends BaseView> {

    protected T view;

    protected BookApi api;

    public String TAG = getClass().getName();

    private CompositeDisposable mCompositeSubscription;//rxjava 存放观察者

    protected void addSubscription(Disposable disposable){//添加观察者
        if (mCompositeSubscription == null){
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(disposable);
    }

    public void unSubscription(){//解除订阅
        if (mCompositeSubscription != null){
            mCompositeSubscription.dispose();
        }
    }

    public void onDetach(){
        unSubscription();
        this.view = null;
    }
}
