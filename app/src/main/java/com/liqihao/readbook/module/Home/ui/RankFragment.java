package com.liqihao.readbook.module.Home.ui;

import android.view.View;

import com.liqihao.readbook.base.BaseFragment;
import com.liqihao.readbook.module.Home.contract.RankContract;
import com.liqihao.readbook.module.Home.presenter.RankPresenter;

/**
 * Created by liqihao on 2017/12/27.
 */

public class RankFragment extends BaseFragment<RankPresenter> implements RankContract.View {
    @Override
    public void setPresenter(RankPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new RankPresenter();
        }
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void bindView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick() {

    }
}
