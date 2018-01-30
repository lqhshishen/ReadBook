package com.liqihao.readbook.module.Home.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Home.bean.HotSearchBean;
import com.liqihao.readbook.module.Home.presenter.SearchPresenter;
import com.liqihao.readbook.module.SearchDetail.bean.SearchBookBean;

import java.util.List;

/**
 * Created by liqihao on 2017/12/28.
 */

public interface SearchContract {
    interface View extends BaseView<SearchPresenter> {

        void onGetHotSearch(HotSearchBean hotSearchBean);
    }
    interface Presenter {
        List<String> getHistory();

        void getHotSearch();

    }
}
