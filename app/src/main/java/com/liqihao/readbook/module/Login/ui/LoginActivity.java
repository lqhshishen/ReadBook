package com.liqihao.readbook.module.Login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.liqihao.readbook.MainActivity;
import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Home.ui.ActivityHome;
import com.liqihao.readbook.module.Login.contract.LoginContract;
import com.liqihao.readbook.module.Login.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.view {


    @BindView(R.id.login_edtinputtel)
    EditText loginEdtinputtel;
    @BindView(R.id.edt_inputnumber)
    EditText edtInputnumber;
    @BindView(R.id.login_edtbutton)
    Button loginEdtbutton;
    @BindView(R.id.login_btnlogin)
    Button loginBtnlogin;
    @BindView(R.id.login_qq)
    LinearLayout loginQq;
    @BindView(R.id.login_wechat)
    LinearLayout loginWechat;

    @Override
    public void bindView() {
        ButterKnife.bind(this);
        presenter.Test();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new LoginPresenter();
        }
    }

    @OnClick(R.id.login_btnlogin)
    public void onViewClicked() {
        startActivity(new Intent(LoginActivity.this, ActivityHome.class));
    }
}
