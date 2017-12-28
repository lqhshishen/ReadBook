package com.liqihao.readbook.module.User.ui;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.User.contract.MyBookListContract;
import com.liqihao.readbook.module.User.presenter.MyBookListPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public class MyBookListActivity extends BaseActivity<MyBookListPresenter> implements MyBookListContract.View {

    @Override
    public void setPresenter(MyBookListPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new MyBookListPresenter();
        }
    }

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
        return R.layout.activity_mybooklist;
    }
}
