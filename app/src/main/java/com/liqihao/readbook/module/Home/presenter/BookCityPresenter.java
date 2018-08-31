package com.liqihao.readbook.module.Home.presenter;

import android.util.Log;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.AppPresenter;
import com.liqihao.readbook.base.BaseFragment;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Home.bean.BookCityBean;
import com.liqihao.readbook.module.Home.contract.BookCityContract;
import com.liqihao.readbook.module.Home.ui.BookCityFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by liqihao on 2017/12/27.
 */

public class BookCityPresenter extends AppPresenter<BookCityFragment> implements BookCityContract.Presenter {

    public List<BookCityBean> bookCityBeanList;

    public List<BookCityBean.BookCityBookBean> bookCityBookBeans;

    @Inject
    public BookCityPresenter(BookCityFragment bookCityFragment) {
        view = bookCityFragment;
    }

    @Override
    public void getBanner() {
        Log.e(TAG,"test");
        view.onVoid("测试");
    }

    @Override
    public void getBean() {
        bookCityBookBeans = new ArrayList<>();
        bookCityBeanList = new ArrayList<>();
        BookCityBean bookCityBean = new BookCityBean();
        bookCityBean.setTitle("今日特价");

        BookCityBean.BookCityBookBean bookCityBookBean1 = new BookCityBean.BookCityBookBean();
        bookCityBookBean1.setBookCode("1");
        bookCityBookBean1.setBookname("明朝那些事");
        bookCityBookBean1.setImg(R.mipmap.loading1);
        bookCityBookBean1.setPrice("0.99");

        BookCityBean.BookCityBookBean bookCityBookBean2 = new BookCityBean.BookCityBookBean();
        bookCityBookBean2.setBookCode("2");
        bookCityBookBean2.setBookname("高效15法则");
        bookCityBookBean2.setImg(R.mipmap.loading2);
        bookCityBookBean2.setPrice("0.99");

        BookCityBean.BookCityBookBean bookCityBookBean3 = new BookCityBean.BookCityBookBean();
        bookCityBookBean3.setBookCode("3");
        bookCityBookBean3.setBookname("不忍细看的大宋史");
        bookCityBookBean3.setImg(R.mipmap.site);
        bookCityBookBean3.setPrice("0.99");

        bookCityBookBeans.add(bookCityBookBean1);
        bookCityBookBeans.add(bookCityBookBean2);
        bookCityBookBeans.add(bookCityBookBean3);

        bookCityBean.setResult(bookCityBookBeans);

        bookCityBeanList.add(bookCityBean);
    }
}
