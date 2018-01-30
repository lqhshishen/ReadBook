package com.liqihao.readbook.module.SearchDetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liqihao.readbook.R;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.Book.bean.CommentList;
import com.liqihao.readbook.module.SearchDetail.bean.SearchBookBean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/30.
 */

public class SearchDetailAdapter extends RecyclerView.Adapter<SearchDetailAdapter.ViewHolder> {
    private List<BookBean>data;

    private Context context;

    public SearchDetailAdapter(List<BookBean>data,Context context) {
        this.data = data;
        this.context = context;
    }
    @Override
    public SearchDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.classic_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchDetailAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(data.get(position).getIcon())
                .into(holder.bookImg);
        holder.author.setText(data.get(position).getAuthor());
        holder.bookName.setText(data.get(position).getBookname());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImg;
        TextView bookName;
        TextView author;
        public ViewHolder(View itemView) {
            super(itemView);
            bookImg = itemView.findViewById(R.id.classic_item_img);
            bookName = itemView.findViewById(R.id.classic_item_name);
            author = itemView.findViewById(R.id.classic_item_author);
        }
    }
}
