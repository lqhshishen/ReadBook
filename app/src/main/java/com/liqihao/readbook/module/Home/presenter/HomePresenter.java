package com.liqihao.readbook.module.Home.presenter;

import com.liqihao.readbook.base.AppPresenter;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Home.contract.HomeContract;
import com.liqihao.readbook.module.Home.ui.ActivityHome;

import javax.inject.Inject;

/**
 * Created by liqihao on 2017/12/27.
 */

public class HomePresenter extends AppPresenter<ActivityHome> implements HomeContract.Presenter{

    @Inject
    public HomePresenter(ActivityHome activityHome) {
        view = activityHome;
    }
}
