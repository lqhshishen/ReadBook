package com.liqihao.readbook.module.Setting.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Setting.contract.SettingContract;
import com.liqihao.readbook.module.Setting.presenter.SettingPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liqihao on 2017/12/28.
 */

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.view {


    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.textView)
    TextView textView;
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

    @Override
    public void setPresenter(SettingPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new SettingPresenter();
        }
    }

    @Override
    public void bindView() {
        ButterKnife.bind(this);
        textView.setText(R.string.setting);
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

    @OnClick({R.id.back_btn, R.id.setting_message_push, R.id.setting_bindTel, R.id.setting_clear_cache, R.id.setting_feedback, R.id.setting_signOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
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
