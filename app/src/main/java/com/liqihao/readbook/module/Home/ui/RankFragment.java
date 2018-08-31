package com.liqihao.readbook.module.Home.ui;

import android.view.View;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.AppFragment;
import com.liqihao.readbook.base.BaseFragment;
import com.liqihao.readbook.module.Home.contract.RankContract;
import com.liqihao.readbook.module.Home.presenter.RankPresenter;

/**
 * Created by liqihao on 2017/12/27.
 */

public class RankFragment extends AppFragment<RankPresenter> implements RankContract.View {
    @Override
    public void setPresenter(RankPresenter presenter) {
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_rank;
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
