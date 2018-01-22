package com.liqihao.readbook.module.Book.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Book.adapter.BookReviewAdapter;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Book.contract.BookReviewContract;
import com.liqihao.readbook.module.Book.presenter.BookReviewPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liqihao on 2017/12/28.
 */

public class BookReviewActivity extends BaseActivity<BookReviewPresenter> implements BookReviewContract.view {

    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.bookReview_bookName)
    TextView bookReviewBookName;
    @BindView(R.id.bookReview_Author)
    TextView bookReviewAuthor;
    @BindView(R.id.bookReview_number)
    TextView bookReviewNumber;
    @BindView(R.id.bookReview_Recycle)
    XRecyclerView bookReviewRecycle;
    @BindView(R.id.bookReview_bookImg)
    ImageView bookReviewImg;

    int page = 0;

    String bookId;
    @Override
    public void setPresenter(BookReviewPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new BookReviewPresenter();
        }
    }

    @Override
    public void bindView() {
        ButterKnife.bind(this);
        bookReviewRecycle.setLayoutManager(new LinearLayoutManager(this));
        bookReviewRecycle.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    @Override
    public void initData() {

    }

    @Subscribe(sticky = true , threadMode = ThreadMode.MAIN)
    public void onStickyEvent(BookBean event) {
        presenter.getComment(event.getId(),"1");
        bookReviewBookName.setText(event.getBookname());
        bookReviewAuthor.setText(event.getAuthor());
        Glide.with(this)
                .load(event.getIcon())
                .into(bookReviewImg);
        presenter.getComment(event.getId(),String.valueOf(page));
        bookId = event.getId();
    }

    @Override
    public void onClick() {
        bookReviewRecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        adapter.clearData();
                        presenter.getComment(bookId, "1");
                        bookReviewRecycle.refreshComplete();
                    }
                },3000);

            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },3000);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_bookreview;
    }

    @OnClick({R.id.back_btn, R.id.textView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.textView:
                break;
        }
    }

    BookReviewAdapter adapter;
    @Override
    public void onReceiveComment(CommentList commentList) {
        adapter = new BookReviewAdapter(this,commentList.getResult().getData());
        bookReviewRecycle.setAdapter(adapter);
        bookReviewNumber.setText(commentList.getResult().getTotal());
    }

    @Override
    public void onLoadMore(CommentList commentList) {
        adapter.addMore(commentList.getResult().getData());
    }

}
