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
}
