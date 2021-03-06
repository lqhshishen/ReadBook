package com.liqihao.readbook.module.Book.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liqihao.readbook.R;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppActivity;
import com.liqihao.readbook.module.Book.adapter.BookReviewAdapter;
import com.liqihao.readbook.module.Book.bean.AddComment;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Book.contract.BookReviewContract;
import com.liqihao.readbook.module.Book.presenter.BookReviewPresenter;
import com.liqihao.readbook.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liqihao on 2017/12/28.
 */

public class BookReviewActivity extends AppActivity<BookReviewPresenter> implements BookReviewContract.view {

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
    @BindView(R.id.bookReview_noComment)
    TextView bookReviewNoComment;
    @BindView(R.id.bookReview_write)
    EditText bookReviewWrite;
    @BindView(R.id.bookReview_submit)
    Button bookReview_submit;

    int page = 1;

    String bookId;

    List<CommentList.Result.Data> mData;


    BookBean event;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void bindView() {
        ButterKnife.bind(this);
        initToolBar(mToolbar,true,"");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        presenter.getComment(event.getId(),String.valueOf(page));

        bookReviewRecycle.setLayoutManager(new LinearLayoutManager(this));
        bookReviewRecycle.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        bookReviewRecycle.setVisibility(View.GONE);
        bookReviewNoComment.setVisibility(View.VISIBLE);
        bookReviewNumber.setVisibility(View.GONE);
//        bookReviewRecycle.removeAllViews();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        event = (BookBean) bundle.get("name");
        bookId = event.getId();
        presenter.getComment(event.getId(), "1");
        bookReviewBookName.setText(event.getBookname());
        bookReviewAuthor.setText(event.getAuthor());
        Glide.with(this)
                .load(event.getIcon())
                .into(bookReviewImg);
        mData = new ArrayList<>();
        adapter = new BookReviewAdapter(this, mData);
        bookReviewRecycle.setAdapter(adapter);
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
                        presenter.getComment(bookId, "1");
                        bookReviewRecycle.refreshComplete();
                    }
                }, 3000);

            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        presenter.getComment(bookId, String.valueOf(page));
                    }
                }, 3000);
                bookReviewRecycle.loadMoreComplete();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_bookreview;
    }

    @OnClick({ R.id.bookReview_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bookReview_submit:
                judgeComment();
                break;
        }
    }

    BookReviewAdapter adapter;

    @SuppressLint("SetTextI18n")
    @Override
    public void onReceiveComment(CommentList commentList) {
        if (commentList.getResult().getData().size() == 0) {

        } else {
//            if (commentList.getResult().getData().size())
            mData.clear();
            mData.addAll(commentList.getResult().getData());
            adapter.notifyDataSetChanged();
//            mData.addAll(commentList.getResult().getData());
            bookReviewNumber.setText(commentList.getResult().getTotal() + "条书评");
            bookReviewNoComment.setVisibility(View.GONE);
            bookReviewNumber.setVisibility(View.VISIBLE);
            bookReviewRecycle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoadMore(CommentList commentList) {
        if (adapter != null) {
            adapter.addMore(commentList.getResult().getData());
        }
    }

    @Override
    public void onPostComment(AddComment addComment) {
        if ("0".equals(addComment.getCode())) {
            ToastUtils.showShort(this, "评论成功");
            bookReviewWrite.setText("");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            presenter.getComment(bookId, "1");
//            bookReviewNoComment.setVisibility(View.GONE);
//            bookReviewNumber.setVisibility(View.VISIBLE);
//            bookReviewRecycle.setVisibility(View.VISIBLE);
        } else {
            ToastUtils.showShort(this, "评论失败");
            Log.e("onError", addComment.getMsg() + addComment.getCode());
        }
    }

    @Override
    public void judgeComment() {
        if (bookReviewWrite.getText().toString().length() == 0) {
            ToastUtils.showShort(this, "请输入评论");
        } else if (bookReviewWrite.getText().toString().length() < 6) {
            ToastUtils.showShort(this, "请至少输入6个字符");
        } else {
            presenter.postComment(App.token, bookReviewWrite.getText().toString(), bookId, "4");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
