package com.liqihao.readbook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Activity mContext;
    //    BindView(R.id.LN_main)
//        LinearLayout main;
//    BindView(R.id.toolbar)
//        android.support.v7.widget.Toolbar toolbar;
//    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.bottom_view)
    View bottomView;
    @BindView(R.id.top_rela)
    RelativeLayout topRela;
    @BindView(R.id.bottom_lin)
    LinearLayout bottomLin;
    @BindView(R.id.day_night_mode)
    ImageView mode;
    @BindView(R.id.Ln_main)
    RelativeLayout relativeLayout;
    @BindView(R.id.content)
    ImageView content;
    @BindView(R.id.aa)
    ImageView aa;
    @BindView(R.id.more)
    ImageView more;

    private PageFactory mPageFactory;
    private PageView pageView;
    private int originPosition = -1;
    private DrawerLayout mDrawerLayout;
    private static final int REQUEST_CODE = 666;
    View.OnClickListener clickListener;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);}
        }
        mContext = MainActivity.this;
        ButterKnife.bind(this);
        mDrawerLayout = findViewById(R.id.side_content);
        topRela = findViewById(R.id.top_rela);
        bottomView = findViewById(R.id.bottom_view);
        bottomLin = findViewById(R.id.bottom_lin);
        aa = findViewById(R.id.aa);
        content = findViewById(R.id.content);
        mode = findViewById(R.id.day_night_mode);
        more = findViewById(R.id.more);
        relativeLayout = findViewById(R.id.Ln_main);
        pageView = findViewById(R.id.pageview);
        Book book = new Book("chenxizhijian","/storage/emulated/0/Download/晨曦之剑.txt","GB18030");
        mPageFactory = com.liqihao.readbook.PageFactory.getInstance(pageView,book);
        mPageFactory.nextPage();
        more.setOnClickListener(clickListener);
        mode.setOnClickListener(clickListener);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
                mDrawerLayout.setClickable(true);
            }
        });
        aa.setOnClickListener(clickListener);
        pageView.setOnClickCallback(new PageView.OnClickCallback() {
            @Override
            public void onLeftClick() {
                if(isShowMenu()){
                    disMissState();
                }else{
                    mPageFactory.prePage();
                }
            }
            @Override
            public void onMiddleClick() {
                if(isShowMenu()){
                    disMissState();
                }else {
                    showState();
                }
            }
            @Override
            public void onRightClick() {
                if(isShowMenu()){
                    disMissState();
                }else{
                    mPageFactory.nextPage();
                }
            }
        });
        pageView.setOnScrollListener(new PageView.OnScrollListener() {
            @Override
            public void onLeftScroll() {
                if(isShowMenu()){
                    disMissState();
                }else {
                    mPageFactory.nextPage();
                }
            }

            @Override
            public void onRightScroll() {
                if(isShowMenu()){
                    disMissState();
                }else{
                    mPageFactory.prePage();
                }
            }
        });

    }
    private boolean isShowMenu(){
        if (topRela.getVisibility() == View.GONE){
            return false;
        }else
            return true;
    }

    private void disMissState(){
        bottomLin.setVisibility(View.GONE);
        topRela.setVisibility(View.GONE);
        bottomView.setVisibility(View.GONE);
        pageView.setSystemUiVisibility(View.INVISIBLE);
    }
    private void showState() {
        bottomLin.setVisibility(View.VISIBLE);
        topRela.setVisibility(View.VISIBLE);
        bottomView.setVisibility(View.VISIBLE);
        pageView.setSystemUiVisibility(View.VISIBLE);
    }
}
