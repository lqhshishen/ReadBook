package com.liqihao.readbook.module.Book.ui;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Book.contract.BookDetailContract;
import com.liqihao.readbook.module.Book.presenter.BookDetailPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public class BookDetailActivity extends BaseActivity<BookDetailPresenter> implements BookDetailContract.View {
    @Override
    public void setPresenter(BookDetailPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new BookDetailPresenter();
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
        return R.layout.activity_bookdetail;
    }
}
