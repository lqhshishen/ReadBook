package com.liqihao.readbook.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liqihao on 2017/12/25.
 */

public abstract class BaseActivity<P extends BasePresenter>extends AppCompatActivity implements BaseView<P> {
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setPresenter(presenter);
        bindView();
        initData();
        onClick();
    }

    public abstract void bindView();

    public abstract void initData();

    public abstract void onClick();

    public abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
        }
    }
}
