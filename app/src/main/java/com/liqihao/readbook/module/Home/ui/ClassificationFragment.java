package com.liqihao.readbook.module.Home.ui;

import android.view.View;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseFragment;
import com.liqihao.readbook.module.Home.contract.ClassificationContract;
import com.liqihao.readbook.module.Home.presenter.ClassificationPresenter;

/**
 * Created by liqihao on 2017/12/27.
 */

public class ClassificationFragment extends BaseFragment<ClassificationPresenter> implements ClassificationContract.View{

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

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick() {

    }
}
