package com.liqihao.readbook.module.Book.ui;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Book.contract.WholeContentContract;
import com.liqihao.readbook.module.Book.presenter.WholeContentPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public class WholeContentActivity extends BaseActivity<WholeContentPresenter> implements WholeContentContract.view {
    @Override
    public void setPresenter(WholeContentPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new WholeContentPresenter();
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
        return R.layout.activity_wholecontent;
    }
}
