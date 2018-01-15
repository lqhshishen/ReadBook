package com.liqihao.readbook.module.Book.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.liqihao.readbook.MainActivity;
import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Book.contract.BookDetailContract;
import com.liqihao.readbook.module.Book.presenter.BookDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liqihao on 2017/12/28.
 */

public class BookDetailActivity extends BaseActivity<BookDetailPresenter> implements BookDetailContract.View {
    @BindView(R.id.bookDetail_read)
    ImageView bookDetailRead;

    @Override
    public void setPresenter(BookDetailPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new BookDetailPresenter();
        }
    }

    @Override
    public void bindView() {
        ButterKnife.bind(this);
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

    @OnClick(R.id.bookDetail_read)
    public void onViewClicked() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
