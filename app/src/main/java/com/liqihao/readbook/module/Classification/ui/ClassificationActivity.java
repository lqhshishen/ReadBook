package com.liqihao.readbook.module.Classification.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Classification.adapter.ClassicItemAdapter;
import com.liqihao.readbook.module.Classification.contract.ClassicContract;
import com.liqihao.readbook.module.Classification.presenter.ClassicPresenter;
import com.liqihao.readbook.module.Home.adapter.ClassificationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassificationActivity extends BaseActivity<ClassicPresenter> implements ClassicContract.view {


    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.classic_headitem1)
    TextView classicHeaditem1;
    @BindView(R.id.classic_headitem2)
    TextView classicHeaditem2;
    @BindView(R.id.classic_headitem3)
    TextView classicHeaditem3;
    @BindView(R.id.classic_headitem4)
    TextView classicHeaditem4;
    @BindView(R.id.classic_headitem5)
    TextView classicHeaditem5;
    @BindView(R.id.classic_headitem6)
    TextView classicHeaditem6;
    @BindView(R.id.classic_headitem7)
    ImageView classicHeaditem7;
    @BindView(R.id.classic_recycle)
    RecyclerView classicRecycle;
    @BindView(R.id.back_btn)
    ImageView backBtn;

    @Override
    public void bindView() {
        ButterKnife.bind(this);
        textView.setText(R.string.book);
        presenter.getBean();
    }

    ClassicItemAdapter adapter;
    @Override
    public void initData() {
        adapter = new ClassicItemAdapter(ClassificationActivity.this,presenter.data);
        Log.e("classic",presenter.data.get(0).getAuthor());
        classicRecycle.setLayoutManager(new LinearLayoutManager(ClassificationActivity.this));
        classicRecycle.setAdapter(adapter);
    }

    @Override
    public void onClick() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_classification;
    }

    @Override
    public void setPresenter(ClassicPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new ClassicPresenter();
        }
    }

    @OnClick({ R.id.classic_headitem1, R.id.classic_headitem2, R.id.classic_headitem3, R.id.classic_headitem4, R.id.classic_headitem5, R.id.classic_headitem6, R.id.classic_headitem7,R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.classic_headitem1:
                resetColor();
                classicHeaditem1.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem2:
                resetColor();
                classicHeaditem2.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem3:
                resetColor();
                classicHeaditem3.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem4:
                resetColor();
                classicHeaditem4.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem5:
                resetColor();
                classicHeaditem5.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem6:
                resetColor();
                classicHeaditem6.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem7:
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }

    @Override
    public void resetColor() {
        classicHeaditem1.setTextColor(getResources().getColor(R.color.classicText));
        classicHeaditem2.setTextColor(getResources().getColor(R.color.classicText));
        classicHeaditem3.setTextColor(getResources().getColor(R.color.classicText));
        classicHeaditem4.setTextColor(getResources().getColor(R.color.classicText));
        classicHeaditem5.setTextColor(getResources().getColor(R.color.classicText));
        classicHeaditem6.setTextColor(getResources().getColor(R.color.classicText));
    }
}
