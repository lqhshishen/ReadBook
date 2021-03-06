package com.liqihao.readbook.module.Classification.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liqihao.readbook.R;
import com.liqihao.readbook.base.AppActivity;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.Classification.adapter.ClassicItemAdapter;
import com.liqihao.readbook.module.Classification.contract.ClassicContract;
import com.liqihao.readbook.module.Classification.presenter.ClassicPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassificationActivity extends AppActivity<ClassicPresenter> implements ClassicContract.view {

    @BindView(R.id.classify_recycle)
    XRecyclerView classicRecycle;

    @BindView(R.id.tab_classify)
    TabLayout tabClassify;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    public void bindView() {
        View contentView = LayoutInflater.from(ClassificationActivity.this)
                .inflate(R.layout.pop_classify, null);
        ButterKnife.bind(this);

    }

    ClassicItemAdapter adapter;
    int page = 1;

    @Override
    public void initData() {
        initToolBar(mToolbar,true,"小说");
        classicRecycle.setLayoutManager(new LinearLayoutManager(ClassificationActivity.this));
        classicRecycle.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        Intent intent = getIntent();
        String id = intent.getStringExtra("ClassId");
//        Log.e("checkClassId", id);
        if (tabClassify.getTabAt(Integer.parseInt(id) - 1) != null) {
            tabClassify.getTabAt(Integer.parseInt(id) - 1).select();
        }
        presenter.getData("1", id);
    }

    @Override
    public void onClick() {
        classicRecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        classicRecycle.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        presenter.getData(String.valueOf(page), String.valueOf(tabClassify.getSelectedTabPosition() + 1));
                        classicRecycle.loadMoreComplete();
                    }
                }, 2000);

            }
        });
        tabClassify.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != tabClassify.getSelectedTabPosition()) {
                    page = 1;
                    classicRecycle.smoothScrollToPosition(1);
                }
//                Log.i("onTabSelected","onTabSelected:"+tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        presenter.getData("1", "1");
                        break;
                    case 1:
                        presenter.getData("1", "2");
                        break;
                    case 2:
                        presenter.getData("1", "3");
                        break;
                    case 3:
                        presenter.getData("1", "4");
                        break;
                    case 4:
                        presenter.getData("1", "5");
                        break;
                    case 5:
                        presenter.getData("1", "6");
                        break;
                    case 6:
                        presenter.getData("1", "7");
                        break;
                    case 7:
                        presenter.getData("1", "8");
                        break;
                    case 8:
                        presenter.getData("1", "9");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("onTabReselected", "tabChange");
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_classification;
    }


    @Override
    public void recomputeT10ffset1(int index) {
//        int a;
//        String str = "";
//        for (int i = 0; i < index;i ++) {
//            str += "玄幻";
//        }
//        a = str.length() * 14 + index * 12;
//        if (tabClassify.getTabAt(index)!= null)
//            tabClassify.getTabAt(index).select();
//        final int width = (int)(a * this.getResources().getDisplayMetrics().density);
//        Log.e("width", String.valueOf(width));
//        classicRecycle.post(new Runnable() {
//            @Override
//            public void run() {
//                classicRecycle.smoothScrollBy(width,0);
//            }
//        });
    }

    @Override
    public void onSetAdapter(List<BookBean> resultBeans) {
        if (adapter != null) {

            adapter.upDateList(resultBeans);
        }
        if (adapter == null || adapter.getItemCount() == 0) {
            adapter = new ClassicItemAdapter(this, resultBeans);
            classicRecycle.setAdapter(adapter);
//            classicRecycle.scrollToPosition(1);

        }
    }


    @OnClick(R.id.tab_classify)
    public void onViewClicked() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
