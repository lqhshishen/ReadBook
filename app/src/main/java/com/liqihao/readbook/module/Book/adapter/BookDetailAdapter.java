package com.liqihao.readbook.module.Book.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.module.Book.bean.CommentList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2018/1/22.
 */

public class BookDetailAdapter extends RecyclerView.Adapter<BookDetailAdapter.ViewHolder> {

    private Context mContext;

    private List<CommentList.Result.Data> mData;

    public BookDetailAdapter (List<CommentList.Result.Data>data,Context mContext) {
        if (data.size() > 3) {
            mData = new ArrayList<>();
            mData.add(data.get(0));
            mData.add(data.get(1));
            mData.add(data.get(2));
        } else if (data.size() < 3) {
            mData = data;
        }
        this.mContext = mContext;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.bookdetail_item,parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.time.setText(mData.get(position).getDateline());
        holder.userName.setText(mData.get(position).getUser().getUsername());
        holder.comment.setText(mData.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView comment;
        TextView time;
        public ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.bookDetail_item_time);
            comment = itemView.findViewById(R.id.bookDetail_item_comment);
            userName = itemView.findViewById(R.id.bookDetail_item_userId);
        }
    }
}
