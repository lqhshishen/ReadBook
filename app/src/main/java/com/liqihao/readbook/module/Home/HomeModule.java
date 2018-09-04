package com.liqihao.readbook.module.Home;


import android.app.Activity;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.module.Home.contract.HomeContract;
import com.liqihao.readbook.module.Home.contract.SearchContract;
import com.liqihao.readbook.module.Home.presenter.BookCityPresenter;
import com.liqihao.readbook.module.Home.presenter.HomePresenter;
import com.liqihao.readbook.module.Home.presenter.SearchPresenter;
import com.liqihao.readbook.module.Home.ui.ActivityHome;
import com.liqihao.readbook.module.Home.ui.BookCityFragment;
import com.liqihao.readbook.module.Home.ui.ClassificationFragment;
import com.liqihao.readbook.module.Home.ui.SearchActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeModule {
    /**
     * 首页
     * @param activityHome
     * @return
     */
    @Binds
    abstract HomeContract.View provideHomeActivity(ActivityHome activityHome);

    @Provides
    static HomePresenter provideHomeP(ActivityHome activityHome){
        return new HomePresenter(activityHome);
    }

    /**
     * 搜索
     * @param activity
     * @return
     */
    @Binds
    abstract SearchContract.View provideSearchActivity(SearchActivity activity);

    @Provides
    static SearchPresenter provideSearchP(SearchActivity activity, BookApi bookApi){
        return new SearchPresenter(activity,bookApi);
    }

}
