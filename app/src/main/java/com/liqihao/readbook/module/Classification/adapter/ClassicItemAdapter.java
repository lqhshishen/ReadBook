package com.liqihao.readbook.module.Classification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    private List<ClassicItemBean> data;

    public ClassicItemAdapter (Context context,List<ClassicItemBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.classic_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getBookName());
        String all = String.valueOf(data.get(position).getStar()) + "（" +
                data.get(position).getNumber() + "）";
        holder.number.setText(all);
        holder.author.setText(data.get(position).getAuthor());
        Glide.with(context)
                .load(data.get(position).getImg())
                .into(holder.img);
        switch ((int) data.get(position).getStar()){
            case 0:
                holder.star5.setVisibility(View.GONE);
                holder.star4.setVisibility(View.GONE);
                holder.star3.setVisibility(View.GONE);
                holder.star2.setVisibility(View.GONE);
                holder.star1.setVisibility(View.GONE);
                break;
            case 4:
                holder.star5.setVisibility(View.GONE);
                break;
            case 3:
                holder.star5.setVisibility(View.GONE);
                holder.star4.setVisibility(View.GONE);
                break;
            case 2:
                holder.star5.setVisibility(View.GONE);
                holder.star4.setVisibility(View.GONE);
                holder.star3.setVisibility(View.GONE);
                break;
            case 1:
                holder.star5.setVisibility(View.GONE);
                holder.star4.setVisibility(View.GONE);
                holder.star3.setVisibility(View.GONE);
                holder.star2.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,star1,star2,star3,star4,star5;
        TextView name;
        TextView author;
        TextView number;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.classic_item_img);
            star1 = itemView.findViewById(R.id.classic_item_star1);
            star2 = itemView.findViewById(R.id.classic_item_star2);
            star3 = itemView.findViewById(R.id.classic_item_star3);
            star4 = itemView.findViewById(R.id.classic_item_star4);
            star5 = itemView.findViewById(R.id.classic_item_star5);
            name = itemView.findViewById(R.id.classic_item_name);
            author = itemView.findViewById(R.id.classic_item_author);
            number = itemView.findViewById(R.id.classic_item_number);


        }
    }
}
