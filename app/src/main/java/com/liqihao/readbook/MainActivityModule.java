package com.liqihao.readbook;

import android.app.Activity;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.module.ReadPage.contract.ContentContract;
import com.liqihao.readbook.module.ReadPage.presenter.PagePresenter;
import com.liqihao.readbook.module.ReadPage.ui.Content;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    Activity provideMainActivity(MainActivity mainActivity){return mainActivity;}

    @Provides
    PagePresenter ProvidePageP(MainActivity activity,BookApi bookApi){
        return new PagePresenter(activity,bookApi);
    }

    @Provides
    ContentContract.Content ProvideContent(Content content){return content;}
}
