package com.liqihao.readbook.module.Home.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseFragment;
import com.liqihao.readbook.module.Home.adapter.ClassificationAdapter;
import com.liqihao.readbook.module.Home.contract.ClassificationContract;
import com.liqihao.readbook.module.Home.presenter.ClassificationPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liqihao on 2017/12/27.
 */

public class ClassificationFragment extends BaseFragment<ClassificationPresenter> implements ClassificationContract.View {

    @BindView(R.id.classic_recycle)
    RecyclerView classicRecycle;
    Unbinder unbinder;

    @Override
    public void setPresenter(ClassificationPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new ClassificationPresenter();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_classic;
    }

    @Override
    public void bindView(View view) {
        unbinder = ButterKnife.bind(this, view);

        classicRecycle.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    @Override
    public void initData() {
        presenter.bindData();
        RecyclerView.Adapter adapter = new ClassificationAdapter(getContext(),presenter.data);
        classicRecycle.setAdapter(adapter);
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
