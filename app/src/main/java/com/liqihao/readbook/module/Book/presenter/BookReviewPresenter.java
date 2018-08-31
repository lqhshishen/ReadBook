package com.liqihao.readbook.module.Book.presenter;

import android.util.Log;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppPresenter;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Book.bean.AddComment;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Book.contract.BookReviewContract;
import com.liqihao.readbook.module.Book.ui.BookReviewActivity;
import com.liqihao.readbook.utils.GetContext;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.GET;

/**
 * Created by liqihao on 2017/12/28.
 */

public class BookReviewPresenter extends AppPresenter<BookReviewActivity> implements BookReviewContract.presenter {
    private String mPage;

    @Inject
    public BookReviewPresenter(BookReviewActivity activity,BookApi api) {
        view = activity;
        this.api = api;
    }

    @Override
    public void getComment(String id, String page) {
        mPage = page;
        api.ShowCommentList(id,page)
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

    @Override
    public void postComment(String auth, String content, String id, String grade) {
        api.addComment(auth,content,id,grade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddComment>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddComment addComment) {
                        view.onPostComment(addComment);
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
