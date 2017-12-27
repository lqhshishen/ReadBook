package com.liqihao.readbook.module.ReadPage.presenter;

import com.liqihao.readbook.module.Content.ui.Content;
import com.liqihao.readbook.module.Content.bean.BookmarkBean;
import com.liqihao.readbook.module.ReadPage.bean.Book;
import com.liqihao.readbook.module.ReadPage.contract.PageContract;
import com.liqihao.readbook.Util.GetContext;
import com.liqihao.readbook.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/11/23.
 */

public class PagePresenter extends BasePresenter<PageContract.MainView> implements PageContract.Presenter{

    Book book = new Book("chenxizhijian",
            "/storage/emulated/0/Download/晨曦之剑.txt","GB18030");
    Content content;
    public void onCreate(){

    }

    @Override
    public Book getBook() {
        return book;

    }

//    String head;
//    String body;
//    String time;
//    int position;

    @Override
    public void saveBookmark(String head,String body,String time,int position) {
        LitePal.initialize(GetContext.getContext());
        BookmarkBean bookmarkBean = new BookmarkBean();
        bookmarkBean.setBookmarkbyteposition(position);
        bookmarkBean.setBookmarkText(body);
        bookmarkBean.setBookmarkTime(time);
        bookmarkBean.setBookmarkTitle(head);
        bookmarkBean.save();
        EventBus.getDefault().post("create success");

//        List<BookmarkBean> book = DataSupport.findAll(BookmarkBean.class);
//        for(BookmarkBean bookmark : book){
//            Log.e("测试数据",bookmark.getBookmarkTitle());
//        }
    }
    @Override
    public List<Integer> getMark(){
        LitePal.initialize(GetContext.getContext());
        List<Integer> aa = new ArrayList<>();
        List<BookmarkBean> bmb = DataSupport.findAll(BookmarkBean.class);
        for(int a=  0 ;a < bmb.size(); a++){
            aa.add(bmb.get(a).getBookmarkbyteposition());
//            Log.e("所有标签", String.valueOf(bmb.get(a).getBookmarkbyteposition()));
        }
        return aa;
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void attachView(PageContract.MainView view) {
        super.attachView(view);
    }
}
