package com.liqihao.readbook.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liqihao.readbook.R;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.DaggerActivity;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class AppActivity<P extends AppPresenter> extends DaggerAppCompatActivity implements BaseView<P> {


    @Inject
    protected P presenter;

    public String TAG = getClass().getName();

    private Dialog loadingDialog;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bindView();
        initData();
        onClick();
    }
    protected void hideStatue(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);}
        }
    }
    /**
     * 设置布局文件id
     * @return
     */
    public abstract int getLayout();

    public abstract void bindView();

    public abstract void initData();

    public abstract void onClick();


    /**
     * 布局销毁 调用presenter置空view，防止内存溢出
     */
    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    /**
     *
     * @param toolbar
     * @param homeAsUpEnable 是否有返回键
     * @param title 标题
     *         设置toolbar
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnable, String title) {
        toolbar.setTitle(title);
//        toolbar.setTitleTextColor(this.getResources().getColor(R.color.topMoney));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(homeAsUpEnable);
    }

    /**
     * 创建dialog
     *
     * @param context
     * @param msg
     * @return
     */
    private static Dialog createWaiteDialog(Context context, String msg) {

        View view = LayoutInflater.from(context).inflate(R.layout.widget_wait_dialog, null);

        LinearLayout layout =  view.findViewById(R.id.dialog_view);

        ImageView circleImg = view.findViewById(R.id.img);

        TextView tip = view.findViewById(R.id.tipTextView);

        //加载动画
        Animation rotateAnim = AnimationUtils.loadAnimation(context, R.anim.progress_loading);

        //开始动画
        circleImg.startAnimation(rotateAnim);

        //设置加载信息
        tip.setText(msg);

        //创建自定义样式的dialog
        Dialog loadingDialog = new Dialog(context, R.style.CustomProgressDialog);

        //不可用返回键取消
        loadingDialog.setCancelable(false);

        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT
        ));
        return loadingDialog;
    }

    public void showWaitDialog(String message) {
            if (loadingDialog == null) {

                loadingDialog = createWaiteDialog(this, message);

            }
            loadingDialog.show();
    }

    public void hideWaitDialog() {
        if (loadingDialog != null) {
            try {
                loadingDialog.dismiss();
                loadingDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
