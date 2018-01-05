package com.liqihao.readbook.module.Home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.liqihao.readbook.module.Home.bean.RankBean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/5.
 */

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder>{

    private List<RankBean> data;

    private Context context;

    public RankAdapter(Context context,List<RankBean>data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
