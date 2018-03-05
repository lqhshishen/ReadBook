package com.liqihao.readbook.module.Home.presenter;

import android.content.Context;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Home.bean.HotSearchBean;
import com.liqihao.readbook.module.Home.contract.SearchContract;
import com.liqihao.readbook.module.Home.ui.SearchActivity;
import com.liqihao.readbook.module.SearchDetail.bean.SearchBookBean;
import com.liqihao.readbook.utils.GetContext;
import com.liqihao.readbook.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/12/28.
 */

public class SearchPresenter extends BasePresenter<SearchActivity> implements SearchContract.Presenter {

    @Override
    public List<String> getHistory() {
        List<String>historys = new ArrayList<>();
        SharedPreferencesUtil.init(App.AppContext,"searchHistory", Context.MODE_PRIVATE);
        String history = SharedPreferencesUtil.getInstance()
                .getString("searchHistory", null);
        String[]all;
        if (history != null) {
            all = history.split(";");
        } else
            return null;
        /**
         * 分割从sharedpreferences中得到的数据
         */
        if (all.length > 0) {
            /**
             * a[0]为null...
             */
            for (int i = 0;i < all.length;i ++) {
                if (all[i] != null && all[i].length() > 0 && i < 10 && !Objects.equals(all[i], "null")) {
                    historys.add(all[i]);
                }
            }
        }
        return historys;
    }

    @Override
    public void getHotSearch() {
        BookApi.getInstance(App.AppContext)
                .hotSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotSearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotSearchBean hotSearchBean) {
                        view.onGetHotSearch(hotSearchBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public String getHistoryString() {
        return SharedPreferencesUtil.getInstance().getString("searchHistory",null);
    }
}
