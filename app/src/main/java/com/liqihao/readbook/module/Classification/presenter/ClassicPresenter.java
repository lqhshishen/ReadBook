package com.liqihao.readbook.module.Classification.presenter;

import android.util.Log;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppPresenter;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.Classification.bean.ClassicItemBean;
import com.liqihao.readbook.module.Classification.contract.ClassicContract;
import com.liqihao.readbook.module.Classification.ui.ClassificationActivity;
import com.liqihao.readbook.utils.GetContext;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2018/1/8.
 */

public class ClassicPresenter extends AppPresenter<ClassificationActivity> implements ClassicContract.presenter {

    public List<BookBean> data = new ArrayList<>();

    @Inject
    public ClassicPresenter(ClassificationActivity activity,BookApi api) {
        view = activity;
        this.api = api;
    }

    @Override
    public void getData(String page, String id) {
        if (Integer.parseInt(page) == 1) {
            data.clear();
        }
        if (data.size() > 200) {
            data.clear();
        }
        api.getClassifyBookList(page,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassicItemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassicItemBean classicItemBean) {
                        Log.e("onNext",classicItemBean.getMsg() + classicItemBean.getCode());
                        data.addAll(classicItemBean.getResult());
                        view.onSetAdapter(data);
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
