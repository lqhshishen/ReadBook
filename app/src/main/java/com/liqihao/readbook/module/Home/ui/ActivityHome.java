package com.liqihao.readbook.module.Home.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppActivity;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Home.contract.HomeContract;
import com.liqihao.readbook.module.Home.presenter.HomePresenter;
import com.liqihao.readbook.module.Setting.ui.SettingActivity;
import com.liqihao.readbook.module.User.ui.MyBookListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;


public class ActivityHome extends AppActivity<HomePresenter> implements HomeContract.View {

    @BindView(R.id.home_navView)
    ImageView homeNavView;
    @BindView(R.id.home_bookCity)
    TextView homeBookCity;
    @BindView(R.id.home_classification)
    TextView homeClassification;
    @BindView(R.id.home_rank)
    TextView homeRank;
    @BindView(R.id.community)
    TextView community;
    @BindView(R.id.home_frame)
    FrameLayout homeFrame;
    @BindView(R.id.nav_user)
    NavigationView navUser;
    @BindView(R.id.home_draw)
    DrawerLayout homeDraw;
    @BindView(R.id.home_navSearch)
    ImageView navSearch;

    private List<String> title;

    private List<DaggerFragment> fragments;

    int previousIndex;

    @Override
    public void bindView() {
        ButterKnife.bind(this);
        previousIndex = 0;
        title = new ArrayList<>();
        fragments = new ArrayList<>();
        homeBookCity.setTextSize(16);
        homeBookCity.setTextColor(this.getResources().getColor(R.color.colorWhite));

    }

    @Override
    public void initData() {
        title.add("书城");
        title.add("分类");
        title.add("排行");
        title.add("社区");
        fragments.clear();
        fragments.add(BookCityFragment.newInstance());
        fragments.add(new ClassificationFragment());
        fragments.add(new RankFragment());
        fragments.add(new CommunityFragment());
        showFragment(0,previousIndex);
    }

    @Override
    public void onClick() {
        navUser.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profileBookshelf:
                        startActivity(new Intent(ActivityHome.this,MyBookListActivity.class));
                        break;
                    case R.id.nav_profileHistory:
                        break;
                    case R.id.nav_profileRenews:
                        break;
                    case R.id.nav_profileSetting:
                        startActivity(new Intent(ActivityHome.this, SettingActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void setPresenter(HomePresenter presenter) {

    }

    @OnClick({R.id.home_navView, R.id.home_bookCity, R.id.home_classification, R.id.home_rank, R.id.community, R.id.home_navSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_navView:
                homeDraw.openDrawer(GravityCompat.START);
                break;
            case R.id.home_bookCity:
                clickHeadText();
                homeBookCity.setTextSize(16);
                homeBookCity.setTextColor(this.getResources().getColor(R.color.colorWhite));
                showFragment(0,previousIndex);
                previousIndex = 0;
                break;
            case R.id.home_classification:
                clickHeadText();
                homeClassification.setTextSize(16);
                homeClassification.setTextColor(this.getResources().getColor(R.color.colorWhite));
                showFragment(1,previousIndex);
                previousIndex = 1;
                break;
            case R.id.home_rank:
                clickHeadText();
                homeRank.setTextColor(this.getResources().getColor(R.color.colorWhite));
                homeRank.setTextSize(16);
                showFragment(2,previousIndex);
                previousIndex = 2;
                break;
            case R.id.community:
                clickHeadText();
                community.setTextColor(this.getResources().getColor(R.color.colorWhite));
                community.setTextSize(16);
                showFragment(3,previousIndex);
                previousIndex = 3;
                break;
            case R.id.home_navSearch:
                startActivity(new Intent(this,SearchActivity.class));
                break;
        }
    }

    @Override
    public void clickHeadText() {
        homeBookCity.setTextSize(14);
        homeClassification.setTextSize(14);
        homeRank.setTextSize(14);
        community.setTextSize(14);
        homeBookCity.setTextColor(this.getResources().getColor(R.color.homeTextNotChecked));
        homeClassification.setTextColor(this.getResources().getColor(R.color.homeTextNotChecked));
        homeRank.setTextColor(this.getResources().getColor(R.color.homeTextNotChecked));
        community.setTextColor(this.getResources().getColor(R.color.homeTextNotChecked));
    }

    @Override
    public void showFragment(int showIndex, int hideIndex) {
        DaggerFragment showFragment = fragments.get(showIndex);
        DaggerFragment hideFragment = fragments.get(hideIndex);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!showFragment.isAdded()) {
            ft.add(R.id.home_frame,showFragment);
        }
        if (showIndex == hideIndex) {
            ft.show(showFragment).commit();
        } else {
            ft.hide(hideFragment).show(showFragment).commit();
        }
    }

}
