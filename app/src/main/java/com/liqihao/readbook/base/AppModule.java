package com.liqihao.readbook.base;

import android.app.Application;
import android.content.Context;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Binds
    abstract Context provideContext(Application application);

}
