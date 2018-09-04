package com.liqihao.readbook.module.Login;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.module.Login.contract.LoginContract;
import com.liqihao.readbook.module.Login.contract.RegisterContract;
import com.liqihao.readbook.module.Login.presenter.LoginPresenter;
import com.liqihao.readbook.module.Login.presenter.RegisterPresenter;
import com.liqihao.readbook.module.Login.ui.LoginActivity;
import com.liqihao.readbook.module.Login.ui.RegisterActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class LoginModule {


    /**
     * 登陆
     * @param activity
     * @return
     */
    @Binds
    abstract LoginContract.view provideLoginA(LoginActivity activity);

    @Provides
    static LoginPresenter provideLoginP(LoginActivity activity, BookApi api){
        return new LoginPresenter(activity,api);
    }


    /**
     * 注册
     */
    @Binds
    abstract RegisterContract.view provideRegisterA(RegisterActivity activity);

    @Provides
    static RegisterPresenter provideRegisterP(RegisterActivity activity,BookApi api){
        return new RegisterPresenter(activity,api);
    }


}
