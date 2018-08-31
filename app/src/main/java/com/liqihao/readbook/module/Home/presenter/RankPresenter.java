package com.liqihao.readbook.module.Home.presenter;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.base.AppPresenter;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Home.contract.RankContract;
import com.liqihao.readbook.module.Home.ui.RankFragment;

import javax.inject.Inject;

/**
 * Created by liqihao on 2017/12/27.
 */

public class RankPresenter extends AppPresenter<RankFragment> implements RankContract.Presenter {

    @Inject
    public RankPresenter(RankFragment fragment, BookApi api) {
        view = fragment;
        this.api = api;
    }

    @Override
    public void getRecycleData() {

    }
}
