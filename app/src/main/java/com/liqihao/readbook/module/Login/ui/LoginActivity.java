package com.liqihao.readbook.module.Login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liqihao.readbook.R;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Home.ui.ActivityHome;
import com.liqihao.readbook.module.Login.bean.MobileLoginBean;
import com.liqihao.readbook.module.Login.contract.LoginContract;
import com.liqihao.readbook.module.Login.presenter.LoginPresenter;
import com.liqihao.readbook.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.view {

    @BindView(R.id.login_edtinputtel)
    EditText loginEdtinputtel;
    @BindView(R.id.login_edtinputPass)
    EditText loginEdtinputPass;
    @BindView(R.id.login_btnlogin)
    Button loginBtnlogin;
    @BindView(R.id.login_qq)
    LinearLayout loginQq;
    @BindView(R.id.login_wechat)
    LinearLayout loginWechat;
    @BindView(R.id.login_forgetPass)
    TextView loginForgetPass;
    @BindView(R.id.login_register)
    TextView loginRegister;

    @Override
    public void bindView() {
        ButterKnife.bind(this);
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

    @OnClick({R.id.login_btnlogin, R.id.login_qq, R.id.login_wechat,R.id.login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btnlogin:
//                startActivity(new Intent(this,ActivityHome.class));
                judgment();
                break;
            case R.id.login_qq:
                break;
            case R.id.login_wechat:
                break;
            case R.id.login_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }

    @Override
    public void judgment() {
        if (loginEdtinputtel.getText().toString().length() == 0 
                || loginEdtinputPass.getText().toString().length() == 0) {
            ToastUtils.showShort(this,"您输入的账号密码为空");
        } else {
            presenter.doLogin(loginEdtinputtel.getText().toString(),loginEdtinputPass.getText().toString());
        }
    }

    @Override
    public void onLogin(MobileLoginBean mobileLoginBean) {
        if ("success".equals(mobileLoginBean.getMsg())) {
            App.token = mobileLoginBean.getResult().getAuth();
            startActivity(new Intent(LoginActivity.this, ActivityHome.class));
            ToastUtils.showShort(this,"登录成功");
            finish();
        } else {
            ToastUtils.showShort(this,"登录失败");
        }
    }
}
