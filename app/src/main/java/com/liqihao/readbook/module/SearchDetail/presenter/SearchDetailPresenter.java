package com.liqihao.readbook.module.SearchDetail.presenter;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.BasePresenter;
import com.liqihao.readbook.module.SearchDetail.bean.SearchBookBean;
import com.liqihao.readbook.module.SearchDetail.contract.SearchDetailContract;
import com.liqihao.readbook.module.SearchDetail.ui.SearchDetailActivity;
import com.liqihao.readbook.utils.GetContext;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqihao on 2018/1/29.
 */

public class SearchDetailPresenter extends BasePresenter<SearchDetailActivity> implements SearchDetailContract.presenter {

    @Override
    public void search(String key) {
        BookApi.getInstance(App.AppContext)
                .searchBook(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBookBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBookBean searchBookBean) {
                        view.onSearch(searchBookBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        view.finishSearch();
                    }
                });
    }
}
