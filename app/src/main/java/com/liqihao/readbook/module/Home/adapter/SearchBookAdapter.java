package com.liqihao.readbook.module.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liqihao.readbook.R;
import com.liqihao.readbook.module.SearchDetail.adapter.SearchDetailAdapter;
import com.liqihao.readbook.module.SearchDetail.ui.SearchDetailActivity;

import java.util.List;

/**
 * Created by liqihao on 2018/1/26.
 */

public class SearchBookAdapter extends RecyclerView.Adapter<SearchBookAdapter.ViewHolder> {
    private List<String>data;

    private Context context;

    public SearchBookAdapter(List<String>data,Context context) {
        this.data = data;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.searchbook_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bookName.setText(data.get(position));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",data.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView bookName;
        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.search_item);
            bookName = itemView.findViewById(R.id.searchbook_name);

        }
    }
}
