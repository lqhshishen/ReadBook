package com.liqihao.readbook.module.Classification.presenter;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Classification.bean.ClassicItemBean;
import com.liqihao.readbook.module.Classification.contract.ClassicContract;
import com.liqihao.readbook.module.Classification.ui.ClassificationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2018/1/8.
 */

public class ClassicPresenter extends BasePresenter<ClassificationActivity> implements ClassicContract.presenter {

    public List<ClassicItemBean> data;

    @Override
    public void getBean() {
        data = new ArrayList<>();
        ClassicItemBean classicItemBean = new ClassicItemBean();
        classicItemBean.setAuthor("刘慈欣");
        classicItemBean.setBookName("三体（全三册）");
        classicItemBean.setImg(R.mipmap.site);
        classicItemBean.setStar(4d);
        classicItemBean.setNumber(600);

        for (int i = 0; i <10 ; i++) {
            data.add(classicItemBean);
        }
    }
}
