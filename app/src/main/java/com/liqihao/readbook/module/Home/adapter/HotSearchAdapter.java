package com.liqihao.readbook.module.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.module.Book.ui.BookDetailActivity;
import com.liqihao.readbook.module.Home.bean.HotSearchBean;
import com.liqihao.readbook.module.SearchDetail.ui.SearchDetailActivity;

import java.util.List;

/**
 * Created by liqihao on 2018/1/29.
 */

public class HotSearchAdapter extends RecyclerView.Adapter<HotSearchAdapter.ViewHolder> {
    private List<HotSearchBean.Result>data;

    private Context context;

    public HotSearchAdapter(List<HotSearchBean.Result>data,Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.hotsearch_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",data.get(holder.getAdapterPosition()).getName());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.hotsearch_item);
        }
    }
}
