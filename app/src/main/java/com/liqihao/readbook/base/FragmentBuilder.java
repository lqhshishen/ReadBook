package com.liqihao.readbook.base;

import com.liqihao.readbook.MainActivityModule;
import com.liqihao.readbook.module.Home.HomeFragmentsModule;
import com.liqihao.readbook.module.Home.ui.BookCityFragment;
import com.liqihao.readbook.module.Home.ui.ClassificationFragment;
import com.liqihao.readbook.module.Home.ui.CommunityFragment;
import com.liqihao.readbook.module.Home.ui.RankFragment;
import com.liqihao.readbook.module.ReadPage.ui.Content;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = HomeFragmentsModule.class)
    abstract BookCityFragment provideBookCityFragmentBuilder();

    @ContributesAndroidInjector(modules = HomeFragmentsModule.class)
    abstract ClassificationFragment provideClassificationFragmentBuilder();

    @ContributesAndroidInjector(modules = HomeFragmentsModule.class)
    abstract CommunityFragment provideCommunityFragmentBuilder();

    @ContributesAndroidInjector(modules = HomeFragmentsModule.class)
    abstract RankFragment provideRankFragmentBuilder();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract Content provideContentFragmentBuilder();
}
