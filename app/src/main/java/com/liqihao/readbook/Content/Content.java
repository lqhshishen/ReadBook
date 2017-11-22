package com.liqihao.readbook.Content;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liqihao.readbook.Util.GetContext;
import com.liqihao.readbook.ReadPage.PageFactory;
import com.liqihao.readbook.R;

import java.util.List;

/**
 * Created by liqihao on 2017/11/15.
 */

public class Content extends Fragment implements ContentFactory.LoadCallback{

    ImageView contentImageGrey;
    ImageView contentImageRed;
    TextView contentWord;
    ImageView bookMarkImageGrey;
    ImageView bookMarkImageRed;
    TextView bookMark;
    private ProgressDialog progressDialog;
    private ChapterAdapter mAdapter;
    private ContentFactory contentFactory;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.content_layout, container, false);

        recyclerView = view.findViewById(R.id.content_re);
        mAdapter = new ChapterAdapter(GetContext.getContext());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(GetContext.getContext()));

        contentFactory = new ContentFactory();

        contentImageGrey = view.findViewById(R.id.content_grey);
        contentImageRed = view.findViewById(R.id.content_red);
        contentWord = view.findViewById(R.id.content);
        bookMarkImageGrey = view.findViewById(R.id.bookmark_grey);
        bookMarkImageRed = view.findViewById(R.id.bookmark_red);
        bookMark = view.findViewById(R.id.bookmark);


        contentImageGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentImageGrey.setVisibility(View.GONE);
                contentImageRed.setVisibility(View.VISIBLE);
                contentWord.setTextColor
                        (GetContext.getContext().getResources().getColor(R.color.colorRed));
                bookMark.setTextColor
                        (GetContext.getContext().getResources().getColor(R.color.contentTextColor));
                bookMarkImageRed.setVisibility(View.GONE);
                bookMarkImageGrey.setVisibility(View.VISIBLE);
            }
        });
        bookMarkImageGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookMarkImageGrey.setVisibility(View.GONE);
                bookMarkImageRed.setVisibility(View.VISIBLE);
                contentWord.setTextColor
                        (GetContext.getContext().getResources().getColor(R.color.contentTextColor));
                contentImageRed.setVisibility(View.GONE);
                contentImageGrey.setVisibility(View.VISIBLE);
                bookMark.setTextColor
                        (GetContext.getContext().getResources().getColor(R.color.colorRed));
            }
        });
        mAdapter.setOnItemClickListener(new ChapterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Chapter chapter) {
                Intent intent = new Intent();
                intent.putExtra("position",chapter.getChapterBytePosition());
//                setResult(RESULT_OK);
            }
        });
        loadChapters();
        return view;
    }

//    private void showDialog() {
//        progressDialog = new ProgressDialog(GetContext.getContext(),R.style.AppDialogTheme);
//        progressDialog.setMax(100);
//        progressDialog.setProgress(0);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setMessage("正在加载中.....");
//        progressDialog.show();
//        progressDialog.setCancelable(false);
//    }
    private void loadChapters() {
        String key = ContentFactory.KETWORD_ZHANG;
        mAdapter.clearData();
        mAdapter.notifyDataSetChanged();
//        showDialog();
//        contentFactory.setProgressCallback(new ContentFactory.ProgressCallback() {
//            @Override
//            public void currentPercentage(int percent) {
//                if(progressDialog.getProgress() != percent){
//                    progressDialog.setProgress(percent);
//                    progressDialog.setMessage("正在加载章节中...");
//                }
//            }
//        });
        contentFactory.setKeyword(key);
        contentFactory.getChapterFromFile(this);
    }

    @Override
    public void onFinishLoad(List<Chapter> List) {
        int chapterNumber = getChapterNumber(PageFactory.getInstance().getCurrentEnd(),List);
        mAdapter.setCurrentChapter(chapterNumber);
        mAdapter.clearData();
        mAdapter.addData(List);
        recyclerView.scrollToPosition(chapterNumber);
//        progressDialog.dismiss();
    }

    @Override
    public void onNotFound() {
        Toast.makeText(GetContext.getContext(), "未发现章节",
                Toast.LENGTH_SHORT).show();
//        progressDialog.dismiss();
    }
    private int getChapterNumber(int position,List<Chapter> list) {
        position -= 2;
        int begin = 0;
        int end = list.size()-1;
        while (begin <= end) {
            int middle = begin + (end - begin)/2;
            if(middle == 0 && list.get(middle).getChapterBytePosition() >= position) {
                return 0;
            }
            if(middle == list.size() -1 &&
                    list.get(list.size() -1).getChapterBytePosition() <= position) {
                return list.size() -1;
            }
            if(list.get(middle).getChapterBytePosition() <= position  && list.get(middle+1).getChapterBytePosition() > position){
                return middle;
            }else if (list.get(middle).getChapterBytePosition() > position && list.get(middle-1).getChapterBytePosition() <= position){
                return middle -1;
            }else if(list.get(middle).getChapterBytePosition() < position && list.get(middle+1).getChapterBytePosition() < position){
                begin = middle+1;
            }else if(list.get(middle).getChapterBytePosition() > position && list.get(middle-1).getChapterBytePosition() > position){
                end = middle-1;
            }
        }
        return 0;
    }
}
