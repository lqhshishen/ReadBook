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


    public static BookCityFragment newInstance(){
        return new BookCityFragment();
    }

    BannerView bannerView;

    private ImageView imageView;

    private List<View> imageList;

    private RecyclerView recyclerView;

    BookCityAdapter bookCityAdapter;

    @Override
    public void setPresenter(BookCityPresenter presenter) {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_bookcity;
    }

    @Override
    public void bindView(View view) {
        bannerView = view.findViewById(R.id.bookCity_banner);
        recyclerView = view.findViewById(R.id.bookCity_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void initData() {
        imageView = new ImageView(getActivity());
        imageList = new ArrayList<>();

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(getActivity())
                .load(R.mipmap.cat)
                .into(imageView);
        imageList.add(imageView);

        imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(getActivity())
                .load(R.mipmap.site)
                .into(imageView);
        imageList.add(imageView);

        imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(getActivity())
                .load(R.mipmap.mainpic)
                .into(imageView);
        imageList.add(imageView);

        bannerView.setViewList(imageList);
        bannerView.startLoop(true);

        presenter.getBean();
        bookCityAdapter = new BookCityAdapter(getActivity(),presenter.bookCityBeanList);
        recyclerView.setAdapter(bookCityAdapter);

        presenter.getBanner();
    }

    public void onVoid(String msg){
        Log.e(TAG,msg);
    }

    @Override
    public void onClick() {
        bookCityAdapter.setOnItemClickListener(new BookCityAdapter.MyItemClickListener() {
            @Override
            public void setOnItemClickListener(View itemView, View view, int postion) {

            }
        });
    }
}
