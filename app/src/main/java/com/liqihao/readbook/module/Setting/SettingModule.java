package com.liqihao.readbook.module.Setting;

import com.liqihao.readbook.module.Setting.contract.SettingContract;
import com.liqihao.readbook.module.Setting.presenter.SettingPresenter;
import com.liqihao.readbook.module.Setting.ui.SettingActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SettingModule {

    @Binds
    abstract SettingContract.view provideSettingA(SettingActivity activity);

    @Provides
    static SettingPresenter provideSettingP(SettingActivity activity){
        return new SettingPresenter(activity);
    }

}
