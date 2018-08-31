package com.liqihao.readbook.app;

import android.content.Context;

import com.liqihao.readbook.base.AppComponent;
import com.liqihao.readbook.base.DaggerAppComponent;
import com.liqihao.readbook.module.User.bean.User;
import com.liqihao.readbook.utils.LogUtils;
import com.liqihao.readbook.utils.SharedPreferencesUtil;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * APP初始化类
 * Created by wangwenzhang on 2017/11/9.
 */

public class App extends DaggerApplication {

    public static User user;
    public static Context AppContext;

    public static String token;
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.isShow=true;
        AppContext = getApplicationContext();
        initPrefs();
        user = new User();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }
}
