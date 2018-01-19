package com.liqihao.readbook.module.Login.presenter;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Login.bean.MobileLoginBean;
import com.liqihao.readbook.module.Login.contract.LoginContract;
import com.liqihao.readbook.module.Login.ui.LoginActivity;
import com.liqihao.readbook.module.ReadPage.bean.Book;
import com.liqihao.readbook.utils.GetContext;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2018/1/18.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.presenter {

    @Override
    public void doLogin(String mobile,String pass) {
        BookApi.getInstance(GetContext.getContext())
                .login(mobile,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MobileLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MobileLoginBean mobileLoginBean) {
                        view.onLogin(mobileLoginBean);
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
