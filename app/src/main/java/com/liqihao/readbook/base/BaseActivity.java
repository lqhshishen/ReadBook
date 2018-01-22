package com.liqihao.readbook.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by liqihao on 2017/12/25.
 */

public abstract class BaseActivity<P extends BasePresenter>extends AppCompatActivity implements BaseView<P> {

    protected P presenter;

    String TAG;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setPresenter(presenter);
        if (presenter != null) {
            this.presenter.attachView(this);
        } else {
            Log.e("test","presenter is empty");
        }

        bindView();
        initData();
        onClick();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);}
        }
        TAG = this.toString();
    }

    public abstract void bindView();

    public abstract void initData();

    public abstract void onClick();

    public abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
        }
    }
}
