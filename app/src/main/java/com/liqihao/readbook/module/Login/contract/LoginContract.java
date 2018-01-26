package com.liqihao.readbook.module.Login.contract;

import android.view.View;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Login.bean.MobileLoginBean;
import com.liqihao.readbook.module.Login.bean.UMLoginBean;
import com.liqihao.readbook.module.Login.presenter.LoginPresenter;

import java.util.Map;

/**
 * Created by liqihao on 2018/1/18.
 */

public interface LoginContract {
    interface view extends BaseView<LoginPresenter>{
        void judgment();

        void onLogin(MobileLoginBean mobileLoginBean);

        void onUMLogin(UMLoginBean umLoginBean,View view);

    }
    interface presenter {
        void doLogin(String mobile,String pass);

        void getToken(final Map<String,String>data,View view);
    }
}
