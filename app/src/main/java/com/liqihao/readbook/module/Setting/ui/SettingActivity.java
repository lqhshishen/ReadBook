package com.liqihao.readbook.module.Setting.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.AppActivity;
import com.liqihao.readbook.module.Setting.contract.SettingContract;
import com.liqihao.readbook.module.Setting.presenter.SettingPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liqihao on 2017/12/28.
 */

public class SettingActivity extends AppActivity<SettingPresenter> implements SettingContract.view {

    @BindView(R.id.activity_setting_kaiqi)
    TextView activitySettingKaiqi;
    @BindView(R.id.setting_message_push)
    LinearLayout settingMessagePush;
    @BindView(R.id.setting_bindTel)
    LinearLayout settingBindTel;
    @BindView(R.id.setting_clear_cache)
    LinearLayout settingClearCache;
    @BindView(R.id.setting_feedback)
    LinearLayout settingFeedback;
    @BindView(R.id.setting_signOut)
    Button settingSignOut;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.myFrontTextView3)
    TextView mMyFrontTextView3;


    @Override
    public void bindView() {
        ButterKnife.bind(this);
        initToolBar(mToolbar,true,"设置");
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

    @OnClick({ R.id.setting_message_push, R.id.setting_bindTel, R.id.setting_clear_cache, R.id.setting_feedback, R.id.setting_signOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_message_push:
                break;
            case R.id.setting_bindTel:
                break;
            case R.id.setting_clear_cache:
                break;
            case R.id.setting_feedback:
                break;
            case R.id.setting_signOut:
                break;
        }
    }

}
