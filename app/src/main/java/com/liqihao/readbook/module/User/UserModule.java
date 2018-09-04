package com.liqihao.readbook.module.User;


import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.module.User.contract.MyBookListContract;
import com.liqihao.readbook.module.User.presenter.MyBookListPresenter;
import com.liqihao.readbook.module.User.ui.MyBookListActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class UserModule {

    /**
     * 我的书架
     * @param activity
     * @return
     */
    @Binds
    abstract MyBookListContract.View provideMyBookListA(MyBookListActivity activity);

    @Provides
    static MyBookListPresenter provideMyBookListP(MyBookListActivity activity, BookApi api){
        return new MyBookListPresenter(activity,api);
    }

}
