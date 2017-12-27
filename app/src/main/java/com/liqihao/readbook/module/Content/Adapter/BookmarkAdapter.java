package com.liqihao.readbook.module.Content.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liqihao.readbook.module.Content.bean.BookmarkBean;
import com.liqihao.readbook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/11/22.
 */

public class BookmarkAdapter extends RecyclerView.Adapter <BookmarkAdapter.BookmarkViewHolder> {
    private List<BookmarkBean> data = new ArrayList<>();
    private OnItemClickListener xListener;

    public BookmarkAdapter (Context context,List<BookmarkBean> list) {
        data = list;
    }

    @Override
    public BookmarkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmark_item,parent,false);
        BookmarkViewHolder holder = new BookmarkViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder (BookmarkViewHolder holder, int position) {
        holder.bookmarkText.setText(data.get(position).getBookmarkText());
        holder.title.setText(data.get(position).getBookmarkTitle());
//        holder.page.setText(data.get(position).getBookmarkpage());
        holder.time.setText(data.get(position).getBookmarkTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView page;
        TextView time;
        TextView bookmarkText;
        public BookmarkViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookmark_title);
//            page = itemView.findViewById(R.id.bookmark_page);
            time = itemView.findViewById(R.id.set_time);
            bookmarkText = itemView.findViewById(R.id.bookmark_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (xListener != null) {
                        xListener.onItemClick(data.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        xListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(BookmarkBean bookmarkBean);
    }
}
