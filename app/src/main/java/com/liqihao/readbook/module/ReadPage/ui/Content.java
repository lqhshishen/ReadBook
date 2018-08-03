package com.liqihao.readbook.module.ReadPage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqihao.readbook.MainActivity;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.module.ReadPage.Adapter.ChapterAdapter;
import com.liqihao.readbook.module.ReadPage.View.ContentFactory;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;
import com.liqihao.readbook.module.ReadPage.contract.ContentContract;
import com.liqihao.readbook.module.ReadPage.presenter.ContentPresenter;
import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
/**
 * Created by liqihao on 2017/11/15.
 */

public class Content extends BaseFragment<ContentPresenter> implements ContentContract.Content {

    ImageView contentImageGrey;
    ImageView contentImageRed;
    TextView contentWord;
    ImageView bookMarkImageGrey;
    ImageView bookMarkImageRed;
    TextView bookMark;


    private ChapterAdapter mAdapter;
//    private ContentFactory contentFactory;
    private RecyclerView recyclerView;
//    private RecyclerView xrectclerView;
//    private BookmarkAdapter xAdapter;

    @Override
    public int getLayout() {
        return R.layout.content_layout;
    }

    @Override
    public void bindView(View view) {
        EventBus.getDefault().register(this);
        contentImageGrey = view.findViewById(R.id.content_grey);
        contentImageRed = view.findViewById(R.id.content_red);
        contentWord = view.findViewById(R.id.content);
        bookMarkImageGrey = view.findViewById(R.id.bookmark_grey);
        bookMarkImageRed = view.findViewById(R.id.bookmark_red);
        bookMark = view.findViewById(R.id.bookmark);
        recyclerView = view.findViewById(R.id.content_re);
//        xrectclerView = view.findViewById(R.id.bookmark_re);
        recyclerView.setLayoutManager(new LinearLayoutManager(App.AppContext));
//        xrectclerView.setLayoutManager(new LinearLayoutManager(App.AppContext));
    }

    String bookid;
    int ChapterNumber;
    @Override
    public void initData() {
        mAdapter = new ChapterAdapter(App.AppContext);
        recyclerView.setAdapter(mAdapter);
        presenter.getChapterList(bookid);
    }

    @Override
    public void setPresenter(ContentPresenter presenter) {
        if (presenter == null) {
            this.presenter = new ContentPresenter();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventRecieve(String word) {
//        xAdapter = new BookmarkAdapter(GetContext.getContext(),presenter.getBookmarkList());
//        xrectclerView.setAdapter(xAdapter);
    }

    @Override
    public void onClick() {
           contentWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickContent();
            }
        });
        contentImageGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickContent();
            }
        });
        bookMarkImageGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBookmark();
            }
        });
        bookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBookmark();
            }
        });
    }

    @Override
    public void clickContent() {
        contentImageGrey.setVisibility(View.GONE);
        contentImageRed.setVisibility(View.VISIBLE);
        contentWord.setTextColor
                (App.AppContext.getResources().getColor(R.color.colorRed));
        bookMark.setTextColor
                (App.AppContext.getResources().getColor(R.color.contentTextColor));
        bookMarkImageRed.setVisibility(View.GONE);
        bookMarkImageGrey.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
//        xrectclerView.setVisibility(View.GONE);
//        presenter.setBookMark();
    }

    @Override
    public void clickBookmark() {
        bookMarkImageGrey.setVisibility(View.GONE);
        bookMarkImageRed.setVisibility(View.VISIBLE);
        contentWord.setTextColor
                (App.AppContext.getResources().getColor(R.color.contentTextColor));
        contentImageRed.setVisibility(View.GONE);
        contentImageGrey.setVisibility(View.VISIBLE);
        bookMark.setTextColor
                (App.AppContext.getResources().getColor(R.color.colorRed));
        recyclerView.setVisibility(View.GONE);
//        xrectclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShow(Chapter chapter) {
        mAdapter.addData(chapter.getResult());
        recyclerView.scrollToPosition(ChapterNumber);
    }

    @Override
    public void loadChapters() {
        String key = ContentFactory.KETWORD_ZHANG;
        mAdapter.clearData();
        mAdapter.notifyDataSetChanged();
//        contentFactory.setKeyword(key);
//        contentFactory.getChapterFromFile(this);
}


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        bookid = ((MainActivity)activity).bookId();
        ChapterNumber = ((MainActivity)activity).getChapterNumber();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
