package com.liqihao.readbook.module.Classification.adapter;

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
import com.liqihao.readbook.R;
import com.liqihao.readbook.module.Book.bean.BookBean;
import com.liqihao.readbook.module.Book.ui.BookDetailActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by liqihao on 2018/1/8.
 */

public class ClassicItemAdapter extends RecyclerView.Adapter<ClassicItemAdapter.ViewHolder>{

    private Context context;

    private List<BookBean> data;

    public ClassicItemAdapter (Context context,List<BookBean> data) {
        this.context = context;
        this.data = data;
    }

    public void clearData() {
        data.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.classic_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getBookname());
        holder.author.setText(data.get(position).getAuthor());
        Glide.with(context)
                .load(data.get(position).getIcon())
                .into(holder.img);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(data.get(position));
                context.startActivity(new Intent(context, BookDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void upDateList(List<BookBean> newData) {
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,star1,star2,star3,star4,star5;
        TextView name;
        TextView author;
        TextView number;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.classic_item_img);
//            star1 = itemView.findViewById(R.id.classic_item_star1);
//            star2 = itemView.findViewById(R.id.classic_item_star2);
//            star3 = itemView.findViewById(R.id.classic_item_star3);
//            star4 = itemView.findViewById(R.id.classic_item_star4);
//            star5 = itemView.findViewById(R.id.classic_item_star5);
            name = itemView.findViewById(R.id.classic_item_name);
            author = itemView.findViewById(R.id.classic_item_author);
            number = itemView.findViewById(R.id.classic_item_number);
            linearLayout = itemView.findViewById(R.id.classic_item);
        }
    }
}
