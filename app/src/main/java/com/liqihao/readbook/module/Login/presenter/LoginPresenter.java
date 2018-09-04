package com.liqihao.readbook.module.Login.presenter;

import android.util.Log;
import android.view.View;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppPresenter;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Login.bean.MobileLoginBean;
import com.liqihao.readbook.module.Login.bean.UMLoginBean;
import com.liqihao.readbook.module.Login.contract.LoginContract;
import com.liqihao.readbook.module.Login.ui.LoginActivity;
import com.liqihao.readbook.module.ReadPage.bean.Book;
import com.liqihao.readbook.utils.GetContext;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.GET;

/**
 * Created by liqihao on 2018/1/18.
 */

public class LoginPresenter extends AppPresenter<LoginActivity> implements LoginContract.presenter {

    @Inject
    public LoginPresenter(LoginActivity activity,BookApi bookApi) {
        view = activity;
        api = bookApi;
    }

    @Override
    public void doLogin(String mobile,String pass) {
        api.login(mobile,pass)
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

    @Override
    public void getToken(Map<String, String> data, final View view1) {
        api.UMLogin(data.get("screen_name"),data.get("openid"),data.get("province")
                        ,data.get("gender"),data.get("iconurl"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UMLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UMLoginBean umLoginBean) {
                        view.onUMLogin(umLoginBean,view1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LoginPresenterOnError",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
