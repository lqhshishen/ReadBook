package com.liqihao.readbook.module.Home.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.Home.presenter.SearchPresenter;

import java.util.List;

/**
 * Created by liqihao on 2017/12/28.
 */

public interface SearchContract {
    interface View extends BaseView<SearchPresenter> {
        void onSearch(String key);
    }
    interface Presenter {
        List<String> getHistory();

    }
}
