package com.liqihao.readbook.module.Home.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Home.presenter.ClassificationPresenter;

/**
 * Created by liqihao on 2017/12/27.
 */

public interface ClassificationContract {
    interface View extends BaseView<ClassificationPresenter> {

    }
    interface Presenter {
        void bindData();
    }
}
