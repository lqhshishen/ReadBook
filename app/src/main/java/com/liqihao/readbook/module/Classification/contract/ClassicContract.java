package com.liqihao.readbook.module.Classification.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Classification.presenter.ClassicPresenter;

/**
 * Created by liqihao on 2018/1/8.
 */

public interface ClassicContract {
    interface view extends BaseView<ClassicPresenter> {
        void resetColor();
    }
    interface presenter {
        void getBean();
    }
}
