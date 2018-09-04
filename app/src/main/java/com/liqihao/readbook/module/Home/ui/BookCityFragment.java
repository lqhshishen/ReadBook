package com.liqihao.readbook.module.Home.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liqihao.readbook.R;
import com.liqihao.readbook.app.App;
import com.liqihao.readbook.base.AppFragment;
import com.liqihao.readbook.base.BaseFragment;
import com.liqihao.readbook.module.Home.adapter.BookCityAdapter;
import com.liqihao.readbook.module.Home.contract.BookCityContract;
import com.liqihao.readbook.module.Home.presenter.BookCityPresenter;
import com.oragee.banners.BannerView;

import java.util.ArrayList;
import java.util.List;

import static org.greenrobot.eventbus.EventBus.TAG;

/**
 * Created by liqihao on 2017/12/27.
 */

public class BookCityFragment extends AppFragment<BookCityPresenter> implements BookCityContract.View {

    BannerView bannerView;

    private ImageView imageView;

    private List<View> imageList;

    private RecyclerView recyclerView;

    BookCityAdapter bookCityAdapter;



    @Override
    public int getLayout() {
        return R.layout.fragment_bookcity;
    }

    @Override
    public void bindView(View view) {
    }

    @Override
    public void initData() {

    }

    public void onVoid(String msg){
        Log.e(TAG,msg);
    }

    @Override
    public void onClick() {
    }
}
