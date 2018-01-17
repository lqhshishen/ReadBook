package com.liqihao.readbook.module.Classification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liqihao.readbook.R;
import com.liqihao.readbook.module.Classification.bean.ClassicItemBean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/8.
 */

public class ClassicItemAdapter extends RecyclerView.Adapter<ClassicItemAdapter.ViewHolder>{

    private Context context;

    private List<ClassicItemBean.ResultBean> data;

    public ClassicItemAdapter (Context context,List<ClassicItemBean.ResultBean> data) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getBookname());
        holder.author.setText(data.get(position).getAuthor());
        Glide.with(context)
                .load(data.get(position).getIcon())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void upDateList(List<ClassicItemBean.ResultBean> newData) {
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,star1,star2,star3,star4,star5;
        TextView name;
        TextView author;
        TextView number;
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
        }
    }
}
