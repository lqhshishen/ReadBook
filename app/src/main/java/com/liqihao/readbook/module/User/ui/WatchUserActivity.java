package com.liqihao.readbook.module.User.ui;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Book.contract.WholeContentContract;
import com.liqihao.readbook.module.User.contract.WatchUserContract;
import com.liqihao.readbook.module.User.presenter.WatchUserPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public class WatchUserActivity extends BaseActivity<WatchUserPresenter>implements WatchUserContract.View {
    @Override
    public void setPresenter(WatchUserPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new WatchUserPresenter();
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
        return R.layout.activity_watchuser;
    }
}
