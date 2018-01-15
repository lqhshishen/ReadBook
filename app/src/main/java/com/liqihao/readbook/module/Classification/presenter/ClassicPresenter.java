package com.liqihao.readbook.module.Classification.presenter;

import android.util.Log;

import com.liqihao.readbook.R;
import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Classification.bean.ClassicItemBean;
import com.liqihao.readbook.module.Classification.contract.ClassicContract;
import com.liqihao.readbook.module.Classification.ui.ClassificationActivity;
import com.liqihao.readbook.utils.GetContext;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2018/1/8.
 */

public class ClassicPresenter extends BasePresenter<ClassificationActivity> implements ClassicContract.presenter {

    public List<ClassicItemBean.ResultBean> data;

    @Override
    public void getData(String page, String id) {
        BookApi.getInstance(GetContext.getContext())
                .getClassifyBookList(page,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassicItemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassicItemBean classicItemBean) {
                        Log.e("onNext",classicItemBean.getMsg() + classicItemBean.getCode());
                        view.onSetAdapter(classicItemBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
