package com.liqihao.readbook.base;


import com.liqihao.readbook.MainActivity;
import com.liqihao.readbook.MainActivityModule;
import com.liqihao.readbook.module.Book.BookDetailModule;
import com.liqihao.readbook.module.Book.ui.BookDetailActivity;
import com.liqihao.readbook.module.Book.ui.BookReviewActivity;
import com.liqihao.readbook.module.Book.ui.WholeContentActivity;
import com.liqihao.readbook.module.Classification.ClassificationModule;
import com.liqihao.readbook.module.Classification.ui.ClassificationActivity;
import com.liqihao.readbook.module.Home.HomeFragmentsModule;
import com.liqihao.readbook.module.Home.HomeModule;
import com.liqihao.readbook.module.Home.ui.ActivityHome;
import com.liqihao.readbook.module.Home.ui.BookCityFragment;
import com.liqihao.readbook.module.Home.ui.SearchActivity;
import com.liqihao.readbook.module.Login.LoginModule;
import com.liqihao.readbook.module.Login.ui.LoginActivity;
import com.liqihao.readbook.module.Login.ui.RegisterActivity;
import com.liqihao.readbook.module.SearchDetail.SearchDetailModule;
import com.liqihao.readbook.module.SearchDetail.ui.SearchDetailActivity;
import com.liqihao.readbook.module.Setting.SettingModule;
import com.liqihao.readbook.module.Setting.ui.SettingActivity;
import com.liqihao.readbook.module.User.UserModule;
import com.liqihao.readbook.module.User.ui.MyBookListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class,FragmentBuilder.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = BookDetailModule.class)
    abstract BookDetailActivity BindDetailActivity();

    @ContributesAndroidInjector(modules = BookDetailModule.class)
    abstract BookReviewActivity BindBookReview();

    @ContributesAndroidInjector(modules = BookDetailModule.class)
    abstract WholeContentActivity BindContent();

    @ContributesAndroidInjector(modules = ClassificationModule.class)
    abstract ClassificationActivity BindClassication();

    @ContributesAndroidInjector(modules = {HomeModule.class,FragmentBuilder.class})
    abstract ActivityHome bindHomeActivity();

    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract SearchActivity bindSearchActivity();

    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector(modules = SearchDetailModule.class)
    abstract SearchDetailActivity bindSearchDetailActivity();

    @ContributesAndroidInjector(modules = SettingModule.class)
    abstract SettingActivity binSettingActivity();

    @ContributesAndroidInjector(modules = UserModule.class)
    abstract MyBookListActivity bindMyBookListActivity();

}
