package com.liqihao.readbook.Content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liqihao.readbook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/11/21.
 */

public class ChapterAdapter extends RecyclerView.Adapter <ChapterAdapter.ChapterViewHolder> {
    private List<Chapter> data = new ArrayList<>();

    private OnItemClickListener mListener;
    private int currentChapter = -1;
    private Context mContext;
    public ChapterAdapter(Context context) {
        mContext = context;
    }
    @Override
    public ChapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.chapter_item,parent,false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChapterViewHolder holder, int position) {
            holder.text.setText(data.get(position).getChapterName());
            if(currentChapter == position) {
                holder.text.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            }else {
                holder.text.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public ChapterViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.chapter_item_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(data.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void addData(List<Chapter> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }
    public void clearData(){
        data.clear();
    }
    public void setCurrentChapter(int number) {
        currentChapter = number;
    }
    public interface OnItemClickListener {
        void onItemClick (Chapter chapter);
    }
}
