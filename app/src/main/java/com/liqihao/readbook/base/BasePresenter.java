package com.liqihao.readbook.base;


import android.util.Log;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.api.BookApiService;
import com.liqihao.readbook.app.App;

import javax.inject.Inject;


/**
 * Created by liqihao on 2017/12/25.
 */

public abstract class BasePresenter<V extends BaseView> {

    public BookApiService BAS = BookApi.getInstance(App.AppContext).getService();

    protected V view;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }


}
