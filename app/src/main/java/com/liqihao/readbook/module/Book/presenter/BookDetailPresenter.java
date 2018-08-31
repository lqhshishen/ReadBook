package com.liqihao.readbook.module.Book.presenter;

import android.util.Log;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppPresenter;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.Book.bean.AddBookshelfBean;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Book.contract.BookDetailContract;
import com.liqihao.readbook.module.Book.ui.BookDetailActivity;
import com.liqihao.readbook.module.User.bean.MyBookList;
import com.liqihao.readbook.module.User.presenter.MyBookListPresenter;
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

public class BookDetailPresenter extends AppPresenter<BookDetailActivity> implements BookDetailContract.Presenter{

    @Inject
    public BookDetailPresenter(BookDetailActivity bookDetailActivity,BookApi bookApi) {
        view = bookDetailActivity;
        api = bookApi;
    }

    @Override
    public void getComment(String id, String page) {
        api.ShowCommentList(id,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(CommentList commentList) {
                        view.onReceiveComment(commentList);
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
    public void AddBookshelf(String id, String auth) {
        api.addToBookshelf(id,auth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddBookshelfBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddBookshelfBean addBookshelfBean) {
                        view.onAddToBookshelf(addBookshelfBean);
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
    public void getMyBookList(String auth, String page) {
        api.getMyBookList(auth, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBookList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyBookList myBookList) {
                        view.onReceiveBookList(myBookList);
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
