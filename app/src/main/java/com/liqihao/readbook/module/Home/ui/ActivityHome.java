package com.liqihao.readbook.module.Home.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Home.contract.HomeContract;
import com.liqihao.readbook.module.Home.presenter.HomePresenter;

public class ActivityHome extends BaseActivity<HomePresenter> implements HomeContract.View {

    @Override
    public void bindView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void setPresenter(HomePresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new HomePresenter();
        }
    }
}
