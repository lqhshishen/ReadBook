package com.liqihao.readbook.module.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liqihao.readbook.R;
import com.liqihao.readbook.module.Classification.ui.ClassificationActivity;
import com.liqihao.readbook.module.Home.bean.ClassificationBean;

import java.util.List;

/**
 * Created by liqihao on 2018/1/5.
 */

public class ClassificationAdapter extends RecyclerView.Adapter<ClassificationAdapter.ViewHolder> {

    private List<ClassificationBean> data;

    private Context context;

    public ClassificationAdapter (Context context,List<ClassificationBean>data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.classic_recycleitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.number.setText(data.get(position).getNumber());
        holder.className.setText(data.get(position).getClassName());
        Glide.with(context)
                .load(data.get(position).getImg())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView img;
        TextView className,number;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.classic_card);
            img = itemView.findViewById(R.id.classic_img);
            className = itemView.findViewById(R.id.classic_name);
            number = itemView.findViewById(R.id.classic_number);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ClassificationActivity.class));
                }
            });
        }
    }
}
