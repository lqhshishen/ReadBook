package com.liqihao.readbook.module.SearchDetail.contract;

import com.liqihao.readbook.base.BaseView;
import com.liqihao.readbook.module.SearchDetail.bean.SearchBookBean;
import com.liqihao.readbook.module.SearchDetail.presenter.SearchDetailPresenter;

/**
 * Created by liqihao on 2018/1/29.
 */

public interface SearchDetailContract {
    interface view extends BaseView<SearchDetailPresenter> {
        void onSearch(SearchBookBean searchBookBean);

        void finishSearch();
    }
    interface presenter{
        void search(String key);
    }
}
