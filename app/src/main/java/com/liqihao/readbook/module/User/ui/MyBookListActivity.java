package com.liqihao.readbook.module.User.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Book.adapter.BookDetailAdapter;
import com.liqihao.readbook.module.User.adapter.MyBookListAdapter;
import com.liqihao.readbook.module.User.bean.MyBookList;
import com.liqihao.readbook.module.User.contract.MyBookListContract;
import com.liqihao.readbook.module.User.presenter.MyBookListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liqihao on 2017/12/28.
 */

public class MyBookListActivity extends BaseActivity<MyBookListPresenter> implements MyBookListContract.View {

    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.bookList_recycle)
    RecyclerView bookListRecycle;

    @Override
    public void setPresenter(MyBookListPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new MyBookListPresenter();
        }
    }

    @Override
    public void bindView() {
        ButterKnife.bind(this);
        textView.setText(R.string.myBookList);
    }

    @Override
    public void initData() {
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        bookListRecycle.setLayoutManager(layoutManager);
        presenter.getBookList(App.token,"1");

    }

    @Override
    public void onClick() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_mybooklist;
    }

    @OnClick({R.id.back_btn, R.id.textView, R.id.bookList_recycle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.textView:
                break;
            case R.id.bookList_recycle:
                break;
        }
    }
    MyBookListAdapter adapter;
    List<String>bookId = new ArrayList<>();
    @Override
    public void onGetBookList(MyBookList myBookList) {
        adapter = new MyBookListAdapter(this,myBookList.getResult());
        bookListRecycle.setAdapter(adapter);
    }
}
