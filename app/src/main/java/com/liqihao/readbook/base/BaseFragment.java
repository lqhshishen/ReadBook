package com.liqihao.readbook.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liqihao on 2017/12/25.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView<P> {
    protected P presenter;

    View view;

    protected Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(),container,false);
        setPresenter(presenter);
        bindView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
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
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
        }
    }
}
