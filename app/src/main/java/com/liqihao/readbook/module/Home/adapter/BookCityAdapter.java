package com.liqihao.readbook.module.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liqihao.readbook.MainActivity;
import com.liqihao.readbook.R;
import com.liqihao.readbook.module.Home.bean.BookCityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2018/1/4.
 */

public class BookCityAdapter extends RecyclerView.Adapter<BookCityAdapter.ViewHolder> {
    List<BookCityBean>bookCityBeanList = new ArrayList<>();

    Context mContext;

    public BookCityAdapter(Context context,List<BookCityBean>list ) {
        this.bookCityBeanList = list;
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bookcity_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(bookCityBeanList.get(position).getTitle());
        holder.tvItem1.setText(bookCityBeanList.get(position).getResult().get(0).getBookname());
        holder.tvItem2.setText(bookCityBeanList.get(position).getResult().get(1).getBookname());
        holder.tvItem3.setText(bookCityBeanList.get(position).getResult().get(2).getBookname());
        Glide.with(mContext)
                .load(bookCityBeanList.get(position).getResult().get(0).getImg())
                .into(holder.iv1);
        Glide.with(mContext)
                .load(bookCityBeanList.get(position).getResult().get(1).getImg())
                .into(holder.iv2);
        Glide.with(mContext)
                .load(bookCityBeanList.get(position).getResult().get(2).getImg())
                .into(holder.iv3);
    }

    @Override
    public int getItemCount() {
        return bookCityBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,tvItem1,tvItem2,tvItem3;
        ImageView iv1,iv2,iv3;
        LinearLayout lt1,lt2,lt3;
        public ViewHolder(final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookCity_item_title);
            tvItem1 = itemView.findViewById(R.id.bookCityitem_1_name);
            tvItem2 = itemView.findViewById(R.id.bookcity_item_2_name);
            tvItem3 = itemView.findViewById(R.id.bookcity_item_3_name);
            iv1 = itemView.findViewById(R.id.bookCititem_1_img);
            iv2 = itemView.findViewById(R.id.bookcity_item_2_img);
            iv3 = itemView.findViewById(R.id.bookcity_item_3_img);
            lt1 = itemView.findViewById(R.id.bookcity_item_1);
            lt2 = itemView.findViewById(R.id.bookcity_item_2);
            lt3 = itemView.findViewById(R.id.bookcity_item_3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.setOnItemClickListener(itemView, v, (Integer) v.getTag());
                }
            });
            lt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                }
            });
            lt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                }
            });
            lt3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                }
            });
        }
    }
    private static MyItemClickListener mListener=null;
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mListener = listener;
    }
    public interface MyItemClickListener {
        void setOnItemClickListener(View itemView,View view,int postion);
    }
}
