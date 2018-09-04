package com.liqihao.readbook.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public abstract class AppFragment<P extends AppPresenter> extends DaggerFragment implements BaseView<P> {

    @Inject
    protected P presenter;

    View view;

    protected Activity activity;

    protected String TAG = getClass().getName();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(),container,false);
        bindView();
        initData();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClick();
    }


    @Override
    public void bindView() {
        bindView(view);
    }

    public abstract int getLayout();

    public abstract void bindView(View view);

    public abstract void initData();

    public abstract void onClick();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter!=null){
            presenter.onDetach();
        }
    }
}
