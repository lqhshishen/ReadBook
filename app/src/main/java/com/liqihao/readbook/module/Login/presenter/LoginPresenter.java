package com.liqihao.readbook.module.Login.presenter;

import android.util.Log;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.api.TestBean;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Login.contract.LoginContract;
import com.liqihao.readbook.module.Login.ui.LoginActivity;
import com.liqihao.readbook.module.ReadPage.bean.Book;
import com.liqihao.readbook.utils.GetContext;

import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/12/28.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.presenter {
    BookApi bookApi = new BookApi(GetContext.getContext());

    @Override
    public void Test() {
        BookApi.getInstance(GetContext.getContext())
                .getMsg("13218021383","0","1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("onSubscribe","开始订阅");
                    }

                    @Override
                    public void onNext(TestBean testBean) {
                        Log.e("onNext",testBean.getMsg());
                        Log.e("onNext,",testBean.getCode());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError",e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete","Complete");
                    }
                });
    }
}
