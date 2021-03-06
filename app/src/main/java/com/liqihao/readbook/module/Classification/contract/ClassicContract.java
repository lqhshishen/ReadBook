package com.liqihao.readbook.module.Classification.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.Classification.presenter.ClassicPresenter;

import java.util.List;

/**
 * Created by liqihao on 2018/1/8.
 */

public interface ClassicContract {
    interface view extends BaseView<ClassicPresenter> {

        void recomputeT10ffset1(int index);

        void onSetAdapter(List<BookBean> resultBeans);
    }
    interface presenter {
        void getData(String page,String id);
    }
}
