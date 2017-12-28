package com.liqihao.readbook.module.Setting.ui;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Setting.contract.SettingContract;
import com.liqihao.readbook.module.Setting.presenter.SettingPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.view {

    @Override
    public void setPresenter(SettingPresenter presenter) {
        if (this.presenter == null){
            this.presenter = new SettingPresenter();
        }
    }

    @Override
    public void bindView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }
}
