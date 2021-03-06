package com.liqihao.readbook.module.Home.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Home.presenter.CommunityPresenter;

/**
 * Created by liqihao on 2017/12/27.
 */

public interface CommunityContract {
    interface View extends BaseView<CommunityPresenter>{

    }
    interface Presenter {
        void getData();
    }
}
