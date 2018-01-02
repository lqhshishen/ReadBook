package com.liqihao.readbook.module.Home.ui;

import android.view.View;

import com.liqihao.readbook.base.BaseFragment;
import com.liqihao.readbook.module.Home.contract.BookCityContract;
import com.liqihao.readbook.module.Home.presenter.BookCityPresenter;
import com.liqihao.readbook.module.Home.presenter.HomePresenter;

/**
 * Created by liqihao on 2017/12/27.
 */

public class BookCityFragment extends BaseFragment<BookCityPresenter> implements BookCityContract.View {

    @Override
    public void setPresenter(BookCityPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new BookCityPresenter();
        }
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void bindView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick() {

    }
}
