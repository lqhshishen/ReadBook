package com.liqihao.readbook.module.User.ui;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.User.contract.MyRenewsContract;
import com.liqihao.readbook.module.User.presenter.MyRenewsPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public class MyRenewsActivity extends BaseActivity<MyRenewsPresenter> implements MyRenewsContract.View{

    @Override
    public void setPresenter(MyRenewsPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new MyRenewsPresenter();
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
        return R.layout.activity_myrenews;
    }
}
