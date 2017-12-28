package com.liqihao.readbook.module.Home.ui;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Home.contract.SearchContract;
import com.liqihao.readbook.module.Home.presenter.SearchPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements  SearchContract.View {
    @Override
    public void setPresenter(SearchPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new SearchPresenter();
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
        return R.layout.activity_search;
    }
}
