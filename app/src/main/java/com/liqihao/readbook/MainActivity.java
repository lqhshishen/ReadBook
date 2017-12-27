package com.liqihao.readbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.liqihao.readbook.module.Content.bean.GetPositionEventBean;
import com.liqihao.readbook.module.Login.LoginActivity;
import com.liqihao.readbook.module.ReadPage.View.PageFactory;
import com.liqihao.readbook.module.ReadPage.View.PageView;
import com.liqihao.readbook.module.ReadPage.contract.PageContract;
import com.liqihao.readbook.module.ReadPage.presenter.PagePresenter;
import com.liqihao.readbook.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<PagePresenter> implements PageContract.MainView{

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

    ImageView bookmarkGrey;

    ImageView bookmarkRed;

    private PageFactory mPageFactory;

    private PageView pageView;

    private DrawerLayout mDrawerLayout;

    int a;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventRecieve(GetPositionEventBean positionEventBean) {
        a = positionEventBean.getI();
        PageFactory.getInstance().setPosition(a);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        disMissState();
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void bindView() {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
//            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);}
        }

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mDrawerLayout = findViewById(R.id.side_content);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        bookmarkGrey = findViewById(R.id.main_bookmark_grey);
        bookmarkRed = findViewById(R.id.main_bookmark_red);
        topRela = findViewById(R.id.top_rela);
        bottomView = findViewById(R.id.bottom_view);
        bottomLin = findViewById(R.id.bottom_lin);
        aa = findViewById(R.id.aa);
        content = findViewById(R.id.content);
        mode = findViewById(R.id.day_night_mode);
        more = findViewById(R.id.more);
        relativeLayout = findViewById(R.id.Ln_main);
        pageView = findViewById(R.id.pageview);

//        预先读取侧滑数据
        mDrawerLayout.openDrawer(GravityCompat.START);
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void initData() {
        mPageFactory = PageFactory.getInstance(pageView,presenter.getBook());
        mPageFactory.nextPage();
    }

    @Override
    public void setPresenter(PagePresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new PagePresenter();
        }
    }

    @Override
    public void checkBookmark() {
//        int b = PageFactory.getInstance().getCurrentEnd();
//        Log.e("当前末尾字节数", String.valueOf(b));
//        bookmark = mPagePresenter.getMark();
//        bookmarkRed.setVisibility(View.GONE);
//        bookmarkGrey.setVisibility(View.VISIBLE);
//        for(int a = 0;a < bookmark.size();a++){
//            Log.e("书签字节数", String.valueOf(bookmark.get(a)));
//            if(b == bookmark.get(a)){
//
//                bookmarkRed.setVisibility(View.VISIBLE);
//                bookmarkGrey.setVisibility(View.GONE);
//            }
//        }
    }

    @Override
    public void onClick() {
        bookmarkRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookmarkRed.setVisibility(View.GONE);
                bookmarkGrey.setVisibility(View.VISIBLE);
            }
        });
        bookmarkGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookmarkGrey.setVisibility(View.GONE);
                bookmarkRed.setVisibility(View.VISIBLE);
                mPageFactory.sendBookmark();
            }
        });
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
                mDrawerLayout.setClickable(true);
            }
        });
        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        pageView.setOnClickCallback(new PageView.OnClickCallback() {
            @Override
            public void onLeftClick() {
                checkBookmark();
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
                checkBookmark();
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
                checkBookmark();
                if(isShowMenu()){
                    disMissState();
                } else {
                    mPageFactory.nextPage();
                }
            }

            @Override
            public void onRightScroll() {
                checkBookmark();
                if(isShowMenu()){
                    disMissState();
                }else{
                    mPageFactory.prePage();
                }
            }
        });
    }

    @Override
    public int getLayout() {
       return R.layout.activity_main;
    }

    @Override
    public boolean isShowMenu(){
        return topRela.getVisibility() != View.GONE;
    }

    @Override
    public void disMissState(){
        bottomLin.setVisibility(View.GONE);
        topRela.setVisibility(View.GONE);
        bottomView.setVisibility(View.GONE);
        pageView.setSystemUiVisibility(View.INVISIBLE);
    }

    @Override
    public void showState() {
        bottomLin.setVisibility(View.VISIBLE);
        topRela.setVisibility(View.VISIBLE);
        bottomView.setVisibility(View.VISIBLE);
        pageView.setSystemUiVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
