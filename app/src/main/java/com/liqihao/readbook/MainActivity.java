package com.liqihao.readbook;

import android.app.Activity;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Activity mContext;

//    BindView(R.id.LN_main)
//        LinearLayout main;
//    BindView(R.id.toolbar)
//        android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.pv)
        PageView pv;

    android.support.v7.widget.Toolbar toolbar;

    private com.liqihao.readbook.PageFactory mPageFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDayTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        ButterKnife.bind(mContext);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Book book = new Book("chenxizhijian","/storage/emulated/0/Download/晨曦之剑.txt");
        book.setEncoding("UTF-8");
        pv.setSystemUiVisibility(View.INVISIBLE);
        mPageFactory = com.liqihao.readbook.PageFactory.getInstance(pv,book);
        mPageFactory.nextPage();
        Log.i("a112233",book.getPath());

        pv.setOnClickCallback(new PageView.OnClickCallback() {
            @Override
            public void onLeftClick() {
                mPageFactory.prePage();
            }

            @Override
            public void onMiddleClick() {

            }

            @Override
            public void onRightClick() {
                mPageFactory.nextPage();
            }
        });
        pv.setOnScrollListener(new PageView.OnScrollListener() {
            @Override
            public void onLeftScroll() {
                mPageFactory.nextPage();
            }

            @Override
            public void onRightScroll() {
                mPageFactory.prePage();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPageFactory.saveBookmark();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(isActionB)
//    }

    private BottomSheetBehavior<CardView> bottomSheetBehavior;
    private int originPosition = -1;

    private boolean hadOtherWidgetShown() {
        if(isAnimating) {
            return true;
        }
        if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            originPosition = -1;
            return true;
        }
        return false;
    }
    private boolean isAnimating;


}
