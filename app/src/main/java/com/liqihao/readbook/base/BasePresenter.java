package com.liqihao.readbook.base;

import android.view.View;

/**
 * Created by liqihao on 2017/12/25.
 */

public abstract class BasePresenter<V extends BaseView> {
    protected V baseView;

    public void attachView(V view) {
        this.baseView = view;
    }

    public void detachView() {
        this.baseView = null;
    }

}
