package com.liqihao.readbook.module.Classification.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liqihao.readbook.R;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.Classification.adapter.ClassicItemAdapter;
import com.liqihao.readbook.module.Classification.contract.ClassicContract;
import com.liqihao.readbook.module.Classification.presenter.ClassicPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassificationActivity extends BaseActivity<ClassicPresenter> implements ClassicContract.view {


    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.classify_recycle)
    XRecyclerView classicRecycle;
    @BindView(R.id.back_btn)
    ImageView backBtn;

    @BindView(R.id.tab_classify)
    TabLayout tabClassify;


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
        textView.setText(R.string.book);
        classicRecycle.setLayoutManager(new LinearLayoutManager(ClassificationActivity.this));
        classicRecycle.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        Intent intent = getIntent();
        String id = intent.getStringExtra("ClassId");
//        Log.e("checkClassId", id);
        if (tabClassify.getTabAt(Integer.parseInt(id) - 1) != null){
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
                },3000);
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
                },2000);

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
                switch (tab.getPosition()){
                    case 0:
                        presenter.getData("1","1");
                        break;
                    case 1:
                        presenter.getData("1","2");
                        break;
                    case 2:
                        presenter.getData("1","3");
                        break;
                    case 3:
                        presenter.getData("1","4");
                        break;
                    case 4:
                        presenter.getData("1","5");
                        break;
                    case 5:
                        presenter.getData("1","6");
                        break;
                    case 6:
                        presenter.getData("1","7");
                        break;
                    case 7:
                        presenter.getData("1","8");
                        break;
                    case 8:
                        presenter.getData("1","9");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("onTabReselected","tabChange");
            }
        });
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

    @OnClick({R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
        }
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
        if (adapter == null || adapter.getItemCount()==0) {
            adapter = new ClassicItemAdapter(this , resultBeans);
            classicRecycle.setAdapter(adapter);
//            classicRecycle.scrollToPosition(1);

        }
    }


    @OnClick(R.id.tab_classify)
    public void onViewClicked() {
    }
}
