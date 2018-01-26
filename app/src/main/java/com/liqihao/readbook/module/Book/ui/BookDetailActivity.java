package com.liqihao.readbook.module.Book.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liqihao.readbook.MainActivity;
import com.liqihao.readbook.R;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Book.adapter.BookDetailAdapter;
import com.liqihao.readbook.module.Book.bean.AddBookshelfBean;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.Book.contract.BookDetailContract;
import com.liqihao.readbook.module.Book.presenter.BookDetailPresenter;
import com.liqihao.readbook.module.User.bean.MyBookList;
import com.liqihao.readbook.utils.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
//    @BindView(R.id.bookDetail_addToBookshelf)
//    ImageView bookDetailAddToBookshelf;
    @BindView(R.id.bookDetail_share)
    ImageView bookDetailShare;
    @BindView(R.id.bookDetail_brief)
    TextView bookDetailBrief;
    @BindView(R.id.bookDetail_comment)
    RecyclerView bookDetailComment;
    @BindView(R.id.bookdetail_comment_more)
    TextView bookDetailCommentMore;
    @BindView(R.id.bookDetail_noComment)
    TextView textView1;
    @BindView(R.id.bookDetail_addToBookshelf)
    Button bookDetailAddToBookshelf;

    List<CommentList.Result.Data>mData;
    @Override
    public void setPresenter(BookDetailPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new BookDetailPresenter();
        }
    }

    @Override
    public void bindView() {
        Log.e("test","7");
        ButterKnife.bind(this);
        //EventBus.getDefault().register(this);
        textView.setText(null);
        textView1.setVisibility(View.GONE);
        bookDetailComment.setVisibility(View.VISIBLE);
        bookDetailComment.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        adapter = new BookDetailAdapter(mData,this);
        bookDetailComment.setAdapter(adapter);
    }
    String id;
    BookBean event;
    @Override
    public void initData() {
        Log.e("test","6");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        event= (BookBean) bundle.get("name");
        //presenter.getComment(event.getId(),"1");
        id=event.getId();
        bookDetailBookName.setText(event.getBookname());
        bookDetailAuthor.setText(event.getAuthor());
        Glide.with(this)
                .load(event.getIcon())
                .into(bookDetailBookImg);
        bookDetailClassify.setText(event.getClassify());
        bookDetailBrief.setText(event.getBrief());
        /**
         * page:页数
         */
        presenter.getComment(event.getId(),"1");
        presenter.getMyBookList(App.token,"1");
       /* CommentList commentList=new Gson().fromJson(Contents.AD,CommentList.class);
        mData.clear();
        mData .addAll(commentList.getResult().getData());*/
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

    @OnClick({R.id.back_btn,R.id.bookDetail_share,R.id.bookdetail_comment_more,R.id.bookDetail_addToBookshelf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.bookDetail_addToBookshelf:
                presenter.AddBookshelf(id, App.token);
                break;
            case R.id.bookDetail_share:
                sharePopup();
                break;
            case R.id.bookdetail_comment_more:
                Intent intent=new Intent(this, BookReviewActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("book",event);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
/*

        @Subscribe(sticky = true , threadMode = ThreadMode.MAIN)
        public void onStickyEvent(BookBean event) {
            Log.e("test","4");
            presenter.getComment(event.getId(),"1");
            bookDetailBookName.setText(event.getBookname());
            bookDetailAuthor.setText(event.getAuthor());
            Glide.with(this)
                    .load(event.getIcon())
                    .into(bookDetailBookImg);
            bookDetailClassify.setText(event.getClassify());
            bookDetailBrief.setText(event.getBrief());
            Log.e("test","5");
//        Log.e("wordcount",event.getStatus() + event.getWordcount());
//        Log.e("bookLink",event.getUrl());

    }
*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    BookDetailAdapter adapter;
    @Override
    public void onReceiveComment(CommentList commentList) {
      /*  CommentList commentList1=new Gson().fromJson(Contents.AD,CommentList.class);
        mData .addAll(commentList1.getResult().getData());*/

        if (commentList.getResult().getData().size() == 0) {
            bookDetailComment.setVisibility(View.GONE);
            textView1.setVisibility(View.VISIBLE);
        } else {
            Log.e("test","1");
            //mData.clear();
            //mData = commentList.getResult().getData();
            Log.e("onReceiveComment"," "+commentList.getResult().getData().size());
            mData.addAll(commentList.getResult().getData());
            adapter.notifyDataSetChanged();
            textView1.setVisibility(View.GONE);
            bookDetailComment.setVisibility(View.VISIBLE);
            Log.e("test","3");

        }
    }

    @Override
    public void onAddToBookshelf(AddBookshelfBean addBookshelfBean) {
        if ("0".equals(addBookshelfBean.getCode())){
            ToastUtils.showShort(this,"加入书架成功");
            bookDetailAddToBookshelf.setText("已在书架");
        }
        else
            ToastUtils.showShort(this,"添加失败");
    }

//    List<String>booId = new ArrayList<>();
    @Override
    public void onReceiveBookList(MyBookList myBookList) {
//        booId.clear();
        for (int i = 0;i < myBookList.getResult().size();i++) {
            if (Objects.equals(event.getId(), myBookList.getResult().get(i).getNid()))
                bookDetailAddToBookshelf.setText("已在书架");
        }
    }
    LinearLayout QQ;
    LinearLayout kongjian;
    LinearLayout pengyou;
    LinearLayout wechat;
    PopupWindow popupWindow;

    @Override
    public void sharePopup() {
        View pop;
        pop = getLayoutInflater().inflate(R.layout.pop_share,null);
        popupWindow = new PopupWindow(pop, ActionBar.LayoutParams.WRAP_CONTENT
        ,ActionBar.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),(Bitmap)null));
//        popupWindow.setAnimationStyle(R.style.anim_pop_top);
        popupWindow.update();
        popupWindow.showAsDropDown(bookDetailShare);
        QQ = pop.findViewById(R.id.share_qq);
        kongjian = pop.findViewById(R.id.share_qqkongjian);
        pengyou = pop.findViewById(R.id.share_friend);
        wechat = pop.findViewById(R.id.share_weixin);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                popupWindow.dismiss();
//            }
//        },3000);
        QQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFriend(SHARE_MEDIA.QQ);
            }
        });
    }

    private AlertDialog sharedialog;
    public void shareDialog(Activity activity) {
        Animation animation = AnimationUtils.loadAnimation(activity,R.anim.img_animation);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        sharedialog = new AlertDialog.Builder(activity,R.style.TransDialogStyle).create();
        if (!activity.isFinishing()) {
            sharedialog.show();
        }
        Window window = sharedialog.getWindow();
        window.setContentView(R.layout.share_dialog);
        ImageView imageView = window.findViewById(R.id.share_dialog_image);
        imageView.setAnimation(animation);
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            shareDialog(BookDetailActivity.this);
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            ToastUtils.showShort(getApplicationContext(),"分享成功");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            sharedialog.dismiss();
            ToastUtils.showShort(getApplicationContext(),"分享失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            sharedialog.dismiss();
            ToastUtils.showShort(getApplicationContext(),"分项取消");
        }
    };

    @Override
    public void shareFriend(SHARE_MEDIA share_media) {
        UMImage thumb = new UMImage(this,event.getIcon());
        String url = "www.baidu.com";
        UMWeb web = new UMWeb(url);
        web.setTitle(event.getBookname());
        web.setThumb(thumb);
        web.setDescription(event.getBrief());
        new ShareAction(this)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .withMedia(web).share();
    }
}
