package com.liqihao.readbook.Content;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liqihao.readbook.R;

/**
 * Created by liqihao on 2017/11/22.
 */

public class BookmarkAdapter extends RecyclerView.Adapter <BookmarkAdapter.BookmarkViewHolder> {

    @Override
    public BookmarkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BookmarkViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView page;
        TextView time;
        TextView bookmarkText;
        public BookmarkViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookmark_title);
            page = itemView.findViewById(R.id.bookmark_page);
            time = itemView.findViewById(R.id.set_time);
            bookmarkText = itemView.findViewById(R.id.bookmark_text);
        }
    }
}
