package com.liqihao.readbook.module.Home.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Home.bean.ClassificationBean;
import com.liqihao.readbook.module.Home.presenter.ClassificationPresenter;

import java.util.List;

/**
 * Created by liqihao on 2017/12/27.
 */

public interface ClassificationContract {
    interface View extends BaseView<ClassificationPresenter> {
        void doSetAdapter(List<ClassificationBean.result> results);
    }
    interface Presenter {
        void bindData();
    }
}
