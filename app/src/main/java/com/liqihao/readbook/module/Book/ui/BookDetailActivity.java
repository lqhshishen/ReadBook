package com.liqihao.readbook.module.Book.ui;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liqihao.readbook.MainActivity;
import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Book.adapter.BookDetailAdapter;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Book.contract.BookDetailContract;
import com.liqihao.readbook.module.Book.presenter.BookDetailPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liqihao on 2017/12/28.
 */

public class BookDetailActivity extends BaseActivity<BookDetailPresenter> implements BookDetailContract.View {
    @BindView(R.id.bookDetail_read)
    ImageView bookDetailRead;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.bookDetail_bookImg)
    ImageView bookDetailBookImg;
    @BindView(R.id.bookDetail_bookName)
    TextView bookDetailBookName;
    @BindView(R.id.bookDetail_author)
    TextView bookDetailAuthor;
    @BindView(R.id.bookDetail_star1)
    ImageView bookDetailStar1;
    @BindView(R.id.bookDetail_star2)
    ImageView bookDetailStar2;
    @BindView(R.id.bookDetail_star3)
    ImageView bookDetailStar3;
    @BindView(R.id.bookDetail_star4)
    ImageView bookDetailStar4;
    @BindView(R.id.bookDetail_star5)
    ImageView bookDetailStar5;
    @BindView(R.id.bookDetail_number)
    TextView bookDetailNumber;
    @BindView(R.id.bookDetail_classify)
    TextView bookDetailClassify;
    @BindView(R.id.bookDetail_addToBookshelf)
    ImageView bookDetailAddToBookshelf;
    @BindView(R.id.bookDetail_share)
    ImageView bookDetailShare;
    @BindView(R.id.bookDetail_brief)
    TextView bookDetailBrief;
    @BindView(R.id.bookDetail_comment)
    RecyclerView bookDetailComment;
    @BindView(R.id.bookdetail_comment_more)
    TextView bookDetailCommentMore;

    @Override
    public void setPresenter(BookDetailPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new BookDetailPresenter();
        }
    }

    @Override
    public void bindView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        textView.setText(null);
        bookDetailComment.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {


    }

    @Override
    public void onClick() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_bookdetail;
    }

    @OnClick(R.id.bookDetail_read)
    public void onViewClicked() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @OnClick({R.id.back_btn, R.id.bookDetail_addToBookshelf, R.id.bookDetail_share,R.id.bookdetail_comment_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.bookDetail_addToBookshelf:
                break;
            case R.id.bookDetail_share:
                break;
            case R.id.bookdetail_comment_more:
                startActivity(new Intent(this,BookReviewActivity.class));
                break;
        }
    }

    @Subscribe(sticky = true , threadMode = ThreadMode.MAIN)
    public void onStickyEvent(BookBean event) {
        presenter.getComment(event.getId(),"1");
        bookDetailBookName.setText(event.getBookname());
        bookDetailAuthor.setText(event.getAuthor());
        Glide.with(this)
                .load(event.getIcon())
                .into(bookDetailBookImg);
        bookDetailClassify.setText(event.getClassify());
        bookDetailBrief.setText(event.getBrief());
//        Log.e("wordcount",event.getStatus() + event.getWordcount());
//        Log.e("bookLink",event.getUrl());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    BookDetailAdapter adapter;
    @Override
    public void onReceiveComment(CommentList commentList) {
        adapter = new BookDetailAdapter(commentList.getResult().getData(),this);
        bookDetailComment.setAdapter(adapter);
    }
}
