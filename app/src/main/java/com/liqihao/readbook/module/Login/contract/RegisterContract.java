package com.liqihao.readbook.module.Login.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Login.bean.MobileReg;
import com.liqihao.readbook.module.Login.bean.SendCodeBean;
import com.liqihao.readbook.module.Login.presenter.RegisterPresenter;

/**
 * Created by liqihao on 2017/12/28.
 */

public interface RegisterContract {

    interface view extends BaseView<RegisterPresenter> {

        void onStartGetMsg();

        void onGetMsg(SendCodeBean sendCodeBean);

        void onRegister(MobileReg mobileReg);

    }
    interface presenter {

        void getCode(String mobile);

        void doRegister(String mobile,String pass,String vcode);
    }
}
