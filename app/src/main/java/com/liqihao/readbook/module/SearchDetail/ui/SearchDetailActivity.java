package com.liqihao.readbook.module.SearchDetail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.AppActivity;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.SearchDetail.adapter.SearchDetailAdapter;
import com.liqihao.readbook.module.SearchDetail.bean.SearchBookBean;
import com.liqihao.readbook.module.SearchDetail.contract.SearchDetailContract;
import com.liqihao.readbook.module.SearchDetail.presenter.SearchDetailPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchDetailActivity extends AppActivity<SearchDetailPresenter> implements SearchDetailContract.view {

    @BindView(R.id.searchDetail_recycle)
    RecyclerView mSearchDetailRecycle;
    @BindView(R.id.searchDetail_progress)
    ProgressBar mSearchDetailProgress;
    @BindView(R.id.searchDetail_noData)
    TextView mSearchDetailNoData;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void bindView() {
        ButterKnife.bind(this);
        initToolBar(mToolbar,true,"搜索结果");
        mSearchDetailRecycle.setVisibility(View.GONE);
        ButterKnife.bind(this);
        mSearchDetailRecycle.setLayoutManager(new LinearLayoutManager(this));
    }

    List<BookBean> data = new ArrayList<>();

    SearchDetailAdapter adapter;
    String key;

    @Override
    public void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        key = intent.getStringExtra("list");
        assert bundle != null;
        presenter.search(key);
        adapter = new SearchDetailAdapter(data, this);
        mSearchDetailRecycle.setAdapter(adapter);
    }


    @Override
    public void onClick() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_search_detail;
    }


    @Override
    public void onSearch(SearchBookBean searchBookBean) {
        data = searchBookBean.getResult();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void finishSearch() {
        mSearchDetailProgress.setVisibility(View.GONE);
        if (data == null || data.size() == 0) {
            mSearchDetailProgress.setVisibility(View.VISIBLE);
        } else
            mSearchDetailProgress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
