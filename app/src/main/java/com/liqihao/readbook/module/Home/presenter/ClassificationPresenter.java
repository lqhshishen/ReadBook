package com.liqihao.readbook.module.Home.presenter;

import android.util.Log;

import com.liqihao.readbook.R;
import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Home.bean.ClassificationBean;
import com.liqihao.readbook.module.Home.contract.ClassificationContract;
import com.liqihao.readbook.module.Home.ui.ClassificationFragment;
import com.liqihao.readbook.utils.GetContext;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/12/27.
 */

public class ClassificationPresenter extends BasePresenter<ClassificationFragment> implements ClassificationContract.Presenter {

    public List<ClassificationBean.result> data;

    public final int a = 0;
    @Override
    public void bindData() {
        data = new ArrayList<>();
        BookApi.getInstance(App.AppContext)
                .getList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassificationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassificationBean classificationBean) {
                        view.doSetAdapter(classificationBean.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
