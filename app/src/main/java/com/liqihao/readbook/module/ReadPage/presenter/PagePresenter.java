package com.liqihao.readbook.module.ReadPage.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.liqihao.readbook.MainActivity;
import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;
import com.liqihao.readbook.module.ReadPage.bean.ChapterDetailBean;
import com.liqihao.readbook.module.ReadPage.bean.BookmarkBean;
import com.liqihao.readbook.module.ReadPage.contract.PageContract;
import com.liqihao.readbook.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/11/23.
 */

public class PagePresenter extends BasePresenter<MainActivity> implements PageContract.Presenter{


    @Override
    public void saveBookmark(String head,String body,String time,int position) {
        LitePal.initialize(App.AppContext);
        BookmarkBean bookmarkBean = new BookmarkBean();
        bookmarkBean.setBookmarkbyteposition(position);
        bookmarkBean.setBookmarkText(body);
        bookmarkBean.setBookmarkTime(time);
        bookmarkBean.setBookmarkTitle(head);
        bookmarkBean.save();
        EventBus.getDefault().post("create success");

    }
    @Override
    public List<Integer> getMark(){
        LitePal.initialize(App.AppContext);
        List<Integer> aa = new ArrayList<>();
        List<BookmarkBean> bmb = DataSupport.findAll(BookmarkBean.class);
        for(int a=  0 ;a < bmb.size(); a++){
            aa.add(bmb.get(a).getBookmarkbyteposition());
//            Log.e("所有标签", String.valueOf(bmb.get(a).getBookmarkbyteposition()));
        }
        return aa;
    }

    @Override
    public void getChapter(String bookId) {
        BookApi.getInstance(App.AppContext)
                .getChapterList(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Chapter>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Chapter chapter) {
                        view.onGetChapterList(chapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getChapterDetail(String bookId, final String chapterId) {
        BookApi.getInstance(App.AppContext)
                .getChapterDetail(bookId,chapterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChapterDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onNext(ChapterDetailBean chapterDetailBean) {
                        if (chapterDetailBean.getResult() == null)
                            view.onGetNullChapter(chapterDetailBean);
                        else
                            view.showChapterRead(chapterDetailBean,chapterId);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
