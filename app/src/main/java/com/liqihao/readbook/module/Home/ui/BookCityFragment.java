package com.liqihao.readbook.module.Home.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseFragment;
import com.liqihao.readbook.module.Home.adapter.BookCityAdapter;
import com.liqihao.readbook.module.Home.contract.BookCityContract;
import com.liqihao.readbook.module.Home.presenter.BookCityPresenter;
import com.oragee.banners.BannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/12/27.
 */

public class BookCityFragment extends BaseFragment<BookCityPresenter> implements BookCityContract.View {
    BannerView bannerView;

    private ImageView imageView;

    private List<View> imageList;

    private RecyclerView recyclerView;
    @Override
    public void setPresenter(BookCityPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new BookCityPresenter();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_bookcity;
    }

    @Override
    public void bindView(View view) {
        bannerView = view.findViewById(R.id.bookCity_banner);
        recyclerView = view.findViewById(R.id.bookCity_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initData() {
        imageView = new ImageView(getContext());
        imageList = new ArrayList<>();

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(getContext())
                .load(R.mipmap.cat)
                .into(imageView);
        imageList.add(imageView);

        imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(getContext())
                .load(R.mipmap.site)
                .into(imageView);
        imageList.add(imageView);

        imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(getContext())
                .load(R.mipmap.mainpic)
                .into(imageView);
        imageList.add(imageView);

        bannerView.setViewList(imageList);
        bannerView.startLoop(true);

        BookCityAdapter bookCityAdapter = new BookCityAdapter(getContext(),presenter.bookCityBeanList);
        recyclerView.setAdapter(bookCityAdapter);
    }

    @Override
    public void onClick() {

    }
}
