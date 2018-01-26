package com.liqihao.readbook.module.Home.presenter;

import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Home.contract.SearchContract;
import com.liqihao.readbook.module.Home.ui.SearchActivity;
import com.liqihao.readbook.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/12/28.
 */

public class SearchPresenter extends BasePresenter<SearchActivity> implements SearchContract.Presenter {

    @Override
    public List<String> getHistory() {
        List<String>historys = new ArrayList<>();
        String history = SharedPreferencesUtil.getInstance()
                .getString("searchHistory", null);
        String[]all = history.split(";");
        /**
         * 分割从sharedpreferences中得到的数据
         */
        if (all.length > 0) {
            for (int i = 0;i < all.length;i ++) {
                if (all[i] != null && all[i].length() > 0 && i < 10) {
                    historys.add(all[i]);
                }
            }
        }
        return historys;
    }

    public String getHistoryString() {
        return SharedPreferencesUtil.getInstance().getString("searchHistory",null);
    }
}
