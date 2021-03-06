package com.liqihao.readbook.module.Login.presenter;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppPresenter;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Login.bean.MobileReg;
import com.liqihao.readbook.module.Login.bean.SendCodeBean;
import com.liqihao.readbook.module.Login.contract.RegisterContract;
import com.liqihao.readbook.module.Login.ui.RegisterActivity;
import com.liqihao.readbook.utils.GetContext;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/12/28.
 */

public class RegisterPresenter extends AppPresenter<RegisterActivity> implements RegisterContract.presenter {

    @Inject
    public RegisterPresenter(RegisterActivity activity,BookApi api) {
        view = activity;
        this.api = api;
    }




    @Override
    public void getCode(String mobile) {
        api.senCode(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SendCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendCodeBean sendCodeBean) {
                        view.onGetMsg(sendCodeBean);
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
    public void doRegister(String mobile, String pass, String vcode) {
        api.mobileRegist(mobile,pass,vcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MobileReg>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MobileReg mobileReg) {
                        view.onRegister(mobileReg);
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
