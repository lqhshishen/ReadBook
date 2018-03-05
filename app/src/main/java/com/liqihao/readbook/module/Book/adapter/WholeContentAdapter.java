package com.liqihao.readbook.module.Book.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.module.Book.ui.WholeContentActivity;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by liqihao on 2018/3/5.
 */

public class WholeContentAdapter extends RecyclerView.Adapter<WholeContentAdapter.ViewHolder> {

    private Context mContext;

    private List<Chapter.Result> mData = new ArrayList<>();

    public WholeContentAdapter (Context context,List<Chapter.Result> data) {
        this.mContext = context;
        this.mData = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.chapter_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getChapter_name());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.chapter_item_text);
        }
    }
}
