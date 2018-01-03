package com.liqihao.readbook.module.Home.ui;

import android.view.View;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseFragment;
import com.liqihao.readbook.module.Home.contract.ClassificationContract;
import com.liqihao.readbook.module.Home.contract.CommunityContract;
import com.liqihao.readbook.module.Home.presenter.CommunityPresenter;

/**
 * Created by liqihao on 2017/12/27.
 */

public class CommunityFragment extends BaseFragment<CommunityPresenter> implements CommunityContract.View {
    @Override
    public void setPresenter(CommunityPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new CommunityPresenter();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_community;
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
