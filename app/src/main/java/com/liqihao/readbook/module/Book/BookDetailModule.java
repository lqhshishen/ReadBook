package com.liqihao.readbook.module.Book;

import android.app.Activity;

import com.liqihao.readbook.api.BookApi;
import com.liqihao.readbook.module.Book.presenter.BookDetailPresenter;
import com.liqihao.readbook.module.Book.presenter.BookReviewPresenter;
import com.liqihao.readbook.module.Book.presenter.WholeContentPresenter;
import com.liqihao.readbook.module.Book.ui.BookDetailActivity;
import com.liqihao.readbook.module.Book.ui.BookReviewActivity;
import com.liqihao.readbook.module.Book.ui.WholeContentActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class BookDetailModule {

    /**
     * 书本详情
     * @param bookDetailActivity
     * @return
     */
    @Provides
    Activity provideBookDetail(BookDetailActivity bookDetailActivity){return bookDetailActivity;}

    @Provides
    BookDetailPresenter provideBookDetailP(BookDetailActivity bookDetailActivity,BookApi bookApi){
        return new BookDetailPresenter(bookDetailActivity,bookApi);
    }

    /**
     * 全部书评
     * @param bookReviewActivity
     * @return
     */
    @Provides
    Activity provideBookReview(BookReviewActivity bookReviewActivity){return bookReviewActivity;}

    @Provides
    BookReviewPresenter provideBookReviewP(BookReviewActivity bookReviewActivity,BookApi bookApi){
        return new BookReviewPresenter(bookReviewActivity,bookApi);
    }

    /**
     * 完整目录
     * @param wholeContentActivity
     * @return
     */
    @Provides
    Activity provideWholeContent(WholeContentActivity wholeContentActivity){return wholeContentActivity;}

    @Provides
    WholeContentPresenter provideWholeContentP(WholeContentActivity activity,BookApi bookApi){
        return new WholeContentPresenter(activity,bookApi);
    }
}
