package com.liqihao.readbook.module.Home.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Home.presenter.RankPresenter;

/**
 * Created by liqihao on 2017/12/27.
 */

public interface RankContract{
    interface View extends BaseView<RankPresenter> {

    }
    interface Presenter {
        void getRecycleData();
    }
}
