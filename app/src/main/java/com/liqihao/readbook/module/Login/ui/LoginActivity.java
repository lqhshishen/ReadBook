package com.liqihao.readbook.module.Login.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liqihao.readbook.R;
import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.api.BookApiService;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppActivity;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.contents.Constant;
import com.liqihao.readbook.module.Home.ui.ActivityHome;
import com.liqihao.readbook.module.Login.bean.MobileLoginBean;
import com.liqihao.readbook.module.Login.bean.UMLoginBean;
import com.liqihao.readbook.module.Login.contract.LoginContract;
import com.liqihao.readbook.module.Login.presenter.LoginPresenter;
import com.liqihao.readbook.utils.ToastUtils;
import com.liqihao.readbook.utils.WeiboDialogUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppActivity<LoginPresenter> implements LoginContract.view {

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
        umShareAPI = UMShareAPI.get(this);
    }

    @Override
    public void onClick() {

    }
    private Dialog getDialog;
    private Dialog dialog;
    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Log.e("umen_tool","---------------1");
            getDialog = WeiboDialogUtils.createLoadingDialog(getApplicationContext(),"正在启动......");
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Log.e("Loaing","---------------2");
            WeiboDialogUtils.closeDialog(getDialog);
            Log.e("QQ的值",map.toString());
            dialog = WeiboDialogUtils.createLoadingDialog(getApplicationContext(),"正在登陆.....");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            ToastUtils.showShort(getApplicationContext(),"失败" + throwable.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            ToastUtils.showShort(getApplicationContext(),"已取消");
        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }


    UMShareAPI umShareAPI;
    @OnClick({R.id.login_btnlogin, R.id.login_qq, R.id.login_wechat,R.id.login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btnlogin:
//                startActivity(new Intent(this,ActivityHome.class));
                judgment();
                break;
            case R.id.login_qq:
                umShareAPI.getPlatformInfo(LoginActivity.this,SHARE_MEDIA.QQ,authListener);
                break;
            case R.id.login_wechat:
                umShareAPI.getPlatformInfo(LoginActivity.this,SHARE_MEDIA.WEIXIN,authListener);
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

    @Override
    public void onUMLogin(UMLoginBean umLoginBean,View view) {
        Log.e("LoginActivity","umLoginBean.getResult().getIs_new()");
        if (umLoginBean.getResult().getIs_new().equals("1")) {
            Snackbar.make(view,"三方登陆需要绑定手机和密码",Snackbar.LENGTH_LONG)
                    .setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                        }
                    }).show();
        }
    }
}
