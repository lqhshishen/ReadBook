package com.liqihao.readbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.liqihao.readbook.base.AppActivity;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.ReadPage.View.PageFactory;
import com.liqihao.readbook.module.ReadPage.View.PageView;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;
import com.liqihao.readbook.module.ReadPage.bean.ChapterDetailBean;
import com.liqihao.readbook.module.ReadPage.contract.PageContract;
import com.liqihao.readbook.module.ReadPage.contract.PageViewContract;
import com.liqihao.readbook.module.ReadPage.presenter.PagePresenter;
import com.liqihao.readbook.module.ReadPage.ui.Content;
import com.liqihao.readbook.utils.CacheManager;
import com.liqihao.readbook.utils.ProtectTooClicks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppActivity<PagePresenter> implements PageContract.MainView {

    @BindView(R.id.bottom_view)
    View bottomView;
    @BindView(R.id.bottom_lin)
    LinearLayout bottomLin;
    @BindView(R.id.day_night_mode)
    ImageView mode;
    @BindView(R.id.Ln_main)
    RelativeLayout relativeLayout;
    @BindView(R.id.content)
    ImageView content;
    @BindView(R.id.aa)
    ImageView aa;
    @BindView(R.id.more)
    ImageView more;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private PageFactory mPageFactory;

    private PageView pageView;

    private DrawerLayout mDrawerLayout;

    String currentChapter = "";//请求章节的ID?

    int chapterNumber = 0;//章节在列表中的序列。


    //是否开始阅读
    boolean startRead = false;

    boolean nextPage = true;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void bindView() {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
//            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);}
        }
        ButterKnife.bind(this);
        initToolBar(mToolbar,true,"");
        mDrawerLayout = findViewById(R.id.side_content);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        bottomView = findViewById(R.id.bottom_view);
        bottomLin = findViewById(R.id.bottom_lin);
        aa = findViewById(R.id.aa);
        content = findViewById(R.id.content);
        mode = findViewById(R.id.day_night_mode);
        more = findViewById(R.id.more);
        relativeLayout = findViewById(R.id.Ln_main);
        pageView = findViewById(R.id.pageview);
        FragmentManager fm = getSupportFragmentManager();
        Fragment content = new Content();
        fm.beginTransaction().replace(R.id.fl_content, content).commit();
    }


    BookBean bookBean;
    Bundle bundle;
    Intent intent;

    @Override
    public void initData() {
        intent = getIntent();
        bundle = intent.getExtras();
        bookBean = (BookBean) bundle.get("name");
        presenter.getChapter(bookBean.getId());
    }


    @Override
    public void checkBookmark() {

    }

    List<Chapter.Result> allChapter = new ArrayList<>();

    @Override
    public void onGetChapterList(Chapter chapter) {
        allChapter.addAll(chapter.getResult());
        currentChapter = allChapter.get(chapterNumber).getId();
        readCurrentChapter();
    }

    /**
     * 当第一章为空时currentChapter+1并加载下一章
     *
     * @param chapterDetailBean
     */
    @RequiresApi(api = VERSION_CODES.KITKAT)
    @Override
    public void onGetNullChapter(ChapterDetailBean chapterDetailBean) {
        for (int i = 0; i < allChapter.size(); i++) {
            if (Objects.equals(currentChapter, allChapter.get(i))) {
                currentChapter = allChapter.get(i + 1).getId();
                presenter.getChapterDetail(bookBean.getId(), currentChapter);
            }
        }
    }


    @Override
    public void onClick() {
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
                mDrawerLayout.setClickable(true);
            }
        });
        pageView.setOnClickCallback(new PageView.OnClickCallback() {
            @Override
            public void onLeftClick() {
                checkBookmark();
                if (isShowMenu()) {
                    disMissState();
                } else {
                    if (mPageFactory.isHavePreContent()
                            && ProtectTooClicks.isDoubleClick()
                            && chapterNumber - 2 >= 0) {
                        chapterNumber -= 2;
                        currentChapter = allChapter.get(chapterNumber).getId();
                        chapterNumber++;
                        nextPage = false;
                        readCurrentChapter();
                    } else
                        mPageFactory.prePage();
                }
            }

            @Override
            public void onMiddleClick() {
                if (isShowMenu()) {
                    disMissState();
                } else {
                    showState();
                }
            }

            @Override
            public void onRightClick() {
                checkBookmark();
                if (isShowMenu()) {
                    disMissState();
                } else if (ProtectTooClicks.isDoubleClick()
                        && mPageFactory.isFinish()
                        && chapterNumber + 1 < allChapter.size()) {
                    currentChapter = allChapter.get(chapterNumber++).getId();
                    nextPage = true;
                    readCurrentChapter();
                } else mPageFactory.nextPage();
            }
        });
        pageView.setOnScrollListener(new PageView.OnScrollListener() {
            @Override
            public void onLeftScroll() {
                checkBookmark();
                if (isShowMenu()) {
                    disMissState();
                } else {
                    mPageFactory.nextPage();
                }
            }

            @Override
            public void onRightScroll() {
                checkBookmark();
                if (isShowMenu()) {
                    disMissState();
                } else if (ProtectTooClicks.isDoubleClick()
                        && mPageFactory.isFinish()
                        && chapterNumber + 1 < allChapter.size()) {
                    currentChapter = allChapter.get(chapterNumber++).getId();
                    readCurrentChapter();
                } else mPageFactory.nextPage();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isShowMenu() {
        return bottomLin.getVisibility() != View.GONE;
    }

    @Override
    public void disMissState() {
        bottomLin.setVisibility(View.GONE);
        bottomView.setVisibility(View.GONE);
        pageView.setSystemUiVisibility(View.INVISIBLE);
        mToolbar.setVisibility(View.GONE);
    }

    @Override
    public void showState() {
        bottomLin.setVisibility(View.VISIBLE);
        bottomView.setVisibility(View.VISIBLE);
        pageView.setSystemUiVisibility(View.VISIBLE);
        mToolbar.setVisibility(View.VISIBLE);
    }


    public void readCurrentChapter() {
        if (CacheManager.getInstance().getChapterFile(bookBean.getId(), currentChapter) != null)
            showChapterRead(null, currentChapter);
        else
            presenter.getChapterDetail(bookBean.getId(), currentChapter);
    }

    /**
     * @param data    小说内容
     * @param chapter 章节ID
     *                章节列表allChapter.get[0]可能为空
     */
    public synchronized void showChapterRead(ChapterDetailBean data, String chapter) {
        if (data != null)
            CacheManager.getInstance().saveChapterFile(bookBean.getId(), chapter, data);
        if (!startRead) {
            startRead = true;
            currentChapter = chapter;
            mPageFactory = PageFactory.getInstance(pageView, chapter, allChapter.get(chapterNumber).getChapter_name()
                    , new int[]{0, 0}, bookBean.getId());
            mPageFactory.nextPage();
            chapterNumber++;
        } else {
            mPageFactory.openBook(currentChapter, new int[]{0, 0}, allChapter.get(chapterNumber).getChapter_name());
            if (nextPage) mPageFactory.nextPage();
            else mPageFactory.preChapterLastPage();
        }
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public String bookId() {
        return bookBean.getId();
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
        currentChapter = allChapter.get(chapterNumber).getId();
        readCurrentChapter();
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPageFactory.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
