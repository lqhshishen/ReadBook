package com.liqihao.readbook.module.Setting.presenter;

import com.liqihao.readbook.base.AppPresenter;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Setting.contract.SettingContract;
import com.liqihao.readbook.module.Setting.ui.SettingActivity;

import javax.inject.Inject;

/**
 * Created by liqihao on 2017/12/28.
 */

public class SettingPresenter extends AppPresenter<SettingActivity> implements SettingContract.presenter{

    @Inject
    public SettingPresenter(SettingActivity activity) {
        view = activity;
    }
}
