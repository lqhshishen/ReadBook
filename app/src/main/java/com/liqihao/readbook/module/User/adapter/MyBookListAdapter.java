package com.liqihao.readbook.module.User.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liqihao.readbook.R;
import com.liqihao.readbook.module.User.bean.MyBookList;

import java.util.List;


/**
 * Created by liqihao on 2018/1/19.
 */

public class MyBookListAdapter extends RecyclerView.Adapter<MyBookListAdapter.ViewHolder> {
    private List<MyBookList.ResultBean>data;
    private Context mContext;

    public MyBookListAdapter(Context context, List<MyBookList.ResultBean>data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public MyBookListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.booklist_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyBookListAdapter.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(data.get(position).getBook().getIcon())
                .into(holder.img);
        holder.bookName.setText(data.get(position).getBook().getBookname());
        holder.jindu.setText(data.get(position).getHadread());
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView bookName;
        TextView jindu;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.booklist_item_img);
            bookName = itemView.findViewById(R.id.booklist_item_bookname);
            jindu = itemView.findViewById(R.id.booklist_item_jindu);
            linearLayout = itemView.findViewById(R.id.booklist_item);
        }
    }
}
