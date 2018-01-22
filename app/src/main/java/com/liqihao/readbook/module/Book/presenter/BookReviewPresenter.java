package com.liqihao.readbook.module.Book.presenter;

import android.util.Log;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Book.contract.BookReviewContract;
import com.liqihao.readbook.module.Book.ui.BookReviewActivity;
import com.liqihao.readbook.utils.GetContext;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2017/12/28.
 */

public class BookReviewPresenter extends BasePresenter<BookReviewActivity> implements BookReviewContract.presenter {
    String mPage;
    @Override
    public void getComment(String id, String page) {
        mPage = page;
        BookApi.getInstance(GetContext.getContext())
                .ShowCommentList(id,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("onSubscribe","startSubscribe");
                    }

                    @Override
                    public void onNext(CommentList commentList) {
                        if (Integer.parseInt(mPage) == 1) {
                            view.onReceiveComment(commentList);
                        } else {
                            view.onLoadMore(commentList);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
