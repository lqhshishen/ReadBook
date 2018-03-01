package com.liqihao.readbook.module.Book.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Book.contract.WholeContentContract;
import com.liqihao.readbook.module.Book.presenter.WholeContentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liqihao on 2017/12/28.
 */

public class WholeContentActivity extends BaseActivity<WholeContentPresenter> implements WholeContentContract.view {


    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.wholecontent_bookimg)
    ImageView wholecontentBookimg;
    @BindView(R.id.wholecontent_bookname)
    TextView wholecontentBookname;
    @BindView(R.id.wholecontent_bookauthor)
    TextView wholecontentBookauthor;
    @BindView(R.id.wholecontent_bookchapter)
    TextView wholecontentBookchapter;
    @BindView(R.id.wholecontent_rv)
    RecyclerView wholecontentRv;

    @Override
    public void setPresenter(WholeContentPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new WholeContentPresenter();
        }
    }

    @Override
    public void bindView() {
        ButterKnife.bind(this);
        textView.setText("完整目录");
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
