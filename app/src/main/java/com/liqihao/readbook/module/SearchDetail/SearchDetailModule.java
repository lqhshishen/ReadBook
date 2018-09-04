package com.liqihao.readbook.module.SearchDetail;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.module.SearchDetail.contract.SearchDetailContract;
import com.liqihao.readbook.module.SearchDetail.presenter.SearchDetailPresenter;
import com.liqihao.readbook.module.SearchDetail.ui.SearchDetailActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SearchDetailModule {

    @Binds
    abstract SearchDetailContract.view provideSearchDetailActivity(SearchDetailActivity activity);

    @Provides
    static SearchDetailPresenter provideSearchDetailP(SearchDetailActivity activity, BookApi api){
        return new SearchDetailPresenter(activity,api);
    }

}
