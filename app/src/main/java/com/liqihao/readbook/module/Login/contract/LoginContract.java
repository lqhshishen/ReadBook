package com.liqihao.readbook.module.Login.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Login.bean.MobileLoginBean;
import com.liqihao.readbook.module.Login.presenter.LoginPresenter;

/**
 * Created by liqihao on 2018/1/18.
 */

public interface LoginContract {
    interface view extends BaseView<LoginPresenter>{
        void judgment();

        void onLogin(MobileLoginBean mobileLoginBean);

    }
    interface presenter {
        void doLogin(String mobile,String pass);
    }
}
