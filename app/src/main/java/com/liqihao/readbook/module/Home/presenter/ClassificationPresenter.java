package com.liqihao.readbook.module.Home.presenter;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Home.bean.ClassificationBean;
import com.liqihao.readbook.module.Home.contract.ClassificationContract;
import com.liqihao.readbook.module.Home.ui.ClassificationFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/12/27.
 */

public class ClassificationPresenter extends BasePresenter<ClassificationFragment> implements ClassificationContract.Presenter {

    public List<ClassificationBean> data;
    @Override
    public void bindData() {
        data = new ArrayList<>();

        ClassificationBean classificationBean = new ClassificationBean();
        classificationBean.setClassName("人文社科");
        classificationBean.setNumber("1705");
        classificationBean.setImg(R.mipmap.share_pengyouquan);

        for (int i = 0;i < 12; i++) {
            data.add(classificationBean);
        }
    }
}
