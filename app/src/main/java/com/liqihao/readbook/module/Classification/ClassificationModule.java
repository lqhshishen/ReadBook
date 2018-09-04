package com.liqihao.readbook.module.Classification;

import android.app.Activity;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.module.Classification.presenter.ClassicPresenter;
import com.liqihao.readbook.module.Classification.ui.ClassificationActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ClassificationModule {
    /**
     * 首页
     * @param activity
     * @return
     */
    @Provides
    Activity provideClassification(ClassificationActivity activity){return activity;}

    @Provides
    ClassicPresenter provideActivityP(ClassificationActivity activity, BookApi api){
        return new ClassicPresenter(activity, api);
    }

}
