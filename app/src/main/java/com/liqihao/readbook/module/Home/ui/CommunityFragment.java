package com.liqihao.readbook.module.Home.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.AppFragment;
import com.liqihao.readbook.base.BaseFragment;
import com.liqihao.readbook.module.Home.adapter.CommunityAdapter;
import com.liqihao.readbook.module.Home.contract.CommunityContract;
import com.liqihao.readbook.module.Home.presenter.CommunityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liqihao on 2017/12/27.
 */

public class CommunityFragment extends AppFragment<CommunityPresenter> implements CommunityContract.View {
    @BindView(R.id.community_recycle)
    RecyclerView communityRecycle;
    Unbinder unbinder;



    @Override
    public int getLayout() {
        return R.layout.fragment_community;
    }

    @Override
    public void bindView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    CommunityAdapter adapter;
    @Override
    public void initData() {
        communityRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.getData();
        adapter = new CommunityAdapter(getContext(),presenter.data);
        communityRecycle.setAdapter(adapter);
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
