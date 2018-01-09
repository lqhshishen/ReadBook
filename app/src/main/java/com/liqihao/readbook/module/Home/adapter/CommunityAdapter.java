package com.liqihao.readbook.module.Home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liqihao.readbook.R;
import com.liqihao.readbook.module.Home.bean.CommunityBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liqihao on 2018/1/9.
 */

public class CommunityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context mContext;

    List<CommunityBean>data;

    public CommunityAdapter (Context context,List<CommunityBean>data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.community_item_oneimg,parent,false);
            return new OneImgHolder(view);
        }
        if (viewType == 2) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.community_item_bookreview,parent,false);
            return new BookReviewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OneImgHolder) {
            Glide.with(mContext)
                    .load(data.get(position).getImg())
                    .into(((OneImgHolder) holder).img);
            Glide.with(mContext)
                    .load(data.get(position).getUserImg())
                    .into(((OneImgHolder) holder).headImg);
            ((OneImgHolder) holder).userName.setText(data.get(position).getUserName());
            ((OneImgHolder) holder).time.setText(data.get(position).getTime());
            ((OneImgHolder) holder).msg.setText(data.get(position).getMsg());
            ((OneImgHolder) holder).likeNum.setText(String.valueOf(data.get(position).getLike()));
            ((OneImgHolder) holder).rePostNum.setText(String.valueOf(data.get(position).getRepostNum()));
            ((OneImgHolder) holder).forwardNum.setText(String.valueOf(data.get(position).getForwardNum()));
        } else if (holder instanceof BookReviewHolder) {
            Glide.with(mContext)
                    .load(data.get(position).getBookImg())
                    .into(((BookReviewHolder) holder).bookImg);
            Glide.with(mContext)
                    .load(data.get(position).getUserImg())
                    .into(((BookReviewHolder) holder).headImg);
            ((BookReviewHolder) holder).userName.setText(data.get(position).getUserName());
            ((BookReviewHolder) holder).time.setText(data.get(position).getTime());
            ((BookReviewHolder) holder).msg.setText(data.get(position).getMsg());
            ((BookReviewHolder) holder).likeNum.setText(String.valueOf(data.get(position).getLike()));
            ((BookReviewHolder) holder).rePostNum.setText(String.valueOf(data.get(position).getRepostNum()));
            ((BookReviewHolder) holder).forwardNum.setText(String.valueOf(data.get(position).getForwardNum()));
            ((BookReviewHolder) holder).bookName.setText(data.get(position).getBookName());
            ((BookReviewHolder) holder).bookMsg.setText(data.get(position).getBookMsg());
            ((BookReviewHolder) holder).author.setText(data.get(position).getAuthor());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class OneImgHolder extends RecyclerView.ViewHolder {
        CircleImageView headImg;
        TextView userName,time,msg,likeNum,rePostNum,forwardNum;
        ImageView img;
        public OneImgHolder(View itemView) {
            super(itemView);
            headImg = itemView.findViewById(R.id.community_headImg);
            userName = itemView.findViewById(R.id.community_name);
            time = itemView.findViewById(R.id.community_time);
            msg = itemView.findViewById(R.id.community_msg);
            likeNum = itemView.findViewById(R.id.community_likeNumber);
            rePostNum = itemView.findViewById(R.id.community_rePostNumber);
            forwardNum = itemView.findViewById(R.id.community_forwardNumber);
            img = itemView.findViewById(R.id.community_img0);
        }
    }

    private static class BookReviewHolder extends RecyclerView.ViewHolder {
        CircleImageView headImg;
        TextView userName,time,msg,bookName,author,bookMsg,likeNum,rePostNum,forwardNum;
        ImageView bookImg;
        public BookReviewHolder(View itemView) {
            super(itemView);
            headImg = itemView.findViewById(R.id.community_bookreview_headImg);
            userName = itemView.findViewById(R.id.community_bookreview_name);
            time = itemView.findViewById(R.id.community_bookreview_time);
            msg = itemView.findViewById(R.id.community_bookreview_msg);
            bookName = itemView.findViewById(R.id.community_bookreview_bookName);
            author = itemView.findViewById(R.id.community_bookreview_author);
            bookMsg = itemView.findViewById(R.id.community_bookreview_bookMsg);
            likeNum = itemView.findViewById(R.id.community_bookreview_likeNumber);
            rePostNum = itemView.findViewById(R.id.community_bookreview_rePostNumber);
            forwardNum = itemView.findViewById(R.id.community_bookreview_forwardNumber);
            bookImg = itemView.findViewById(R.id.community_bookreview_bookImg);

        }
    }
}
