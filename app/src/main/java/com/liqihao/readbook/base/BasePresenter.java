package com.liqihao.readbook.base;


import android.util.Log;

/**
 * Created by liqihao on 2017/12/25.
 */

public abstract class BasePresenter<V extends BaseView> {
    protected V view;

    public void attachView(V view) {
        this.view    = view;
        Log.e("test",view.toString());
    }

    public void detachView() {
        this.view = null;
    }
}
