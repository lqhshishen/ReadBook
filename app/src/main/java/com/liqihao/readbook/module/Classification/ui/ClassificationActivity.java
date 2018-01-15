package com.liqihao.readbook.module.Classification.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Classification.adapter.ClassicItemAdapter;
import com.liqihao.readbook.module.Classification.bean.ClassicItemBean;
import com.liqihao.readbook.module.Classification.contract.ClassicContract;
import com.liqihao.readbook.module.Classification.presenter.ClassicPresenter;

import java.util.List;

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
    @BindView(R.id.classify_recycle)
    RecyclerView classicRecycle;
    @BindView(R.id.back_btn)
    ImageView backBtn;

    PopupWindow mPopupWindow;


    @Override
    public void bindView() {
        View contentView = LayoutInflater.from(ClassificationActivity.this)
                .inflate(R.layout.pop_classify, null);
        mPopupWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT
                , ActionBar.LayoutParams.WRAP_CONTENT, true);
        ButterKnife.bind(this);
    }

    ClassicItemAdapter adapter;

    @Override
    public void initData() {
        textView.setText(R.string.book);
        classicRecycle.setLayoutManager(new LinearLayoutManager(ClassificationActivity.this));
        Intent intent = getIntent();
        String id = intent.getStringExtra("ClassId");
        Log.e("checkClassId", id);
        presenter.getData("1", id);
//        head.add(String.valueOf(R.string.fantasy));
//        head.add(String.valueOf(R.string.martialArts));
//        head.add(String.valueOf(R.string.cityBook));
//        head.add(String.valueOf(R.string.romance));
//        head.add(String.valueOf(R.string.throughBook));
//        head.add(String.valueOf(R.string.onlineGame));
//        head.add(String.valueOf(R.string.suspense));
//        head.add(String.valueOf(R.string.scienceFiction));
//        head.add(String.valueOf(R.string.others));
//        switch (Integer.parseInt(id)){
//            case 1:
//                classicHeaditem1.setTextColor(getResources().getColor(R.color.colorBlack));
//                break;
//            case 2:
//                classicHeaditem2.setTextColor(getResources().getColor(R.color.colorBlack));
//                break;
//            case 3:
//                classicHeaditem3.setTextColor(getResources().getColor(R.color.colorBlack));
//                break;
//            case 4:
//                classicHeaditem4.setTextColor(getResources().getColor(R.color.colorBlack));
//                break;
//            case 5:
//                classicHeaditem5.setTextColor(getResources().getColor(R.color.colorBlack));
//                break;
//            case 6:
//                classicHeaditem6.setTextColor(getResources().getColor(R.color.colorBlack));
//                break;
//        }

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

    @OnClick({R.id.classic_headitem1, R.id.classic_headitem2, R.id.classic_headitem3, R.id.classic_headitem4, R.id.classic_headitem5, R.id.classic_headitem6, R.id.classic_headitem7, R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.classic_headitem1:
                resetColor();
                presenter.getData("1", "1");
                classicHeaditem1.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem2:
                resetColor();
                presenter.getData("1", "2");
                classicHeaditem2.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem3:
                resetColor();
                presenter.getData("1", "3");
                classicHeaditem3.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem4:
                resetColor();
                presenter.getData("1", "4");
                classicHeaditem4.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem5:
                resetColor();
                presenter.getData("1", "5");
                classicHeaditem5.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.classic_headitem6:
                resetColor();
                presenter.getData("1", "6");
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

    @Override
    public void setHead() {

    }

    List<String> head;


    @Override
    public void onSetAdapter(List<ClassicItemBean.ResultBean> resultBeans) {
        if (adapter != null) {
            adapter.clearData();
        }
        adapter = new ClassicItemAdapter(getApplicationContext(), resultBeans);
        classicRecycle.setAdapter(adapter);
    }


}
