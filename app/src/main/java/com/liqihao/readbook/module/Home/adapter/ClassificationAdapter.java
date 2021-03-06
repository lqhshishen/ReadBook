package com.liqihao.readbook.module.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    private List<ClassificationBean.result> data;

    private Context context;

    public ClassificationAdapter (Context context,List<ClassificationBean.result>data) {
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.className.setText(data.get(position).getClassify());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClassificationActivity.class);
                intent.putExtra("ClassId",data.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cardView;
        TextView className;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.classic_card);
            className = itemView.findViewById(R.id.classic_name);
        }
    }
}
