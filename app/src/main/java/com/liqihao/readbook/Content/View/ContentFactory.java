package com.liqihao.readbook.Content.View;

import android.os.Looper;
import android.util.Log;

import com.liqihao.readbook.Content.bean.Chapter;
import com.liqihao.readbook.Content.contract.ContentContract;
import com.liqihao.readbook.ReadPage.bean.Book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/11/21.
 */

public class ContentFactory {
    public static final String KETWORD_ZHANG = "章";
    public static final String KETWORD_JIE = "节";
    public static final String KETWORD_HUI = "回";

    private Book book;
    private MappedByteBuffer mappedByteBuffer;
    private int mappedFileLength;
    private String encoding;
    private String keyword = KETWORD_ZHANG;
    private ArrayList<Chapter> chapters = new ArrayList<>();
    private final ArrayList<Integer> positions = new ArrayList<>();

    private ProgressCallback progressCallback;

    private android.os.Handler mHandler = new android.os.Handler(Looper.getMainLooper());

    private volatile boolean hasChapters = true;

    private RandomAccessFile randomFile;

    public ContentFactory() {
        book = new Book("chenxizhijian","/storage/emulated/0/Download/晨曦之剑.txt","GB18030");
        File file = new File(book.getPath());
//        Log.e("书名", String.valueOf(PageFactory.getInstance().getBook().getName()));
//        mappedByteBuffer = PageFactory.getInstance().getMappedFile();
        mappedFileLength = (int) file.length();
        encoding = book.getEncoding();
        try {
            randomFile = new RandomAccessFile(file, "r");
            mappedByteBuffer = randomFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, (long) mappedFileLength);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getChapterFromFile(final LoadCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("loading","chapters");
                hasChapters = true;
                chapters.clear();
                findParagraphInBytePosition();
                findChapterParagraphPosition(callback);
            }
        }).start();
    }

//    public List<Chapter> getChapterFromDB() {
//        return DB
//    }
        /*
        识别章节
         */
    private void findChapterParagraphPosition(final LoadCallback callback) {
        chapters.clear();
        int i = 0;
        try {
            InputStreamReader isr = new InputStreamReader
                    (new FileInputStream(new File(book.getPath())),encoding);
            BufferedReader reader = new BufferedReader(isr);
            String temp;
            Chapter chapter;
            Log.e("chapter","start loading");
            while ((temp = reader.readLine()) != null) {
                if(temp.contains("第") && temp.contains(keyword)) {
                    chapter = new Chapter();
                    chapter.setChapterName(temp);
                    chapter.setName(book.getName());
                    chapter.setChapterParagraphPsition(i);
                    chapters.add(chapter);
                }
                i++;
            }
            if(chapters.size() == 0) {
                hasChapters = false;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onNotFound();
                    }
                });
                return;
            }
            Log.e("chapters","Load completely");

            synchronized (positions) {
                Log.e("start", "insert data");
                for (int a = 0; a < chapters.size(); a++) {
                    chapter = chapters.get(a);
                    chapter.setChapterBytePosition
                            (positions.get(Math.max(chapter.getChapterParagraphPsition()-1,0)));
                }
            }
            Log.e("insert","completely");
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onFinishLoad(chapters);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findParagraphInBytePosition() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (positions) {
                    Log.e("positions","start loading");
                    positions.clear();
                    byte[] fileBytes = new byte[mappedFileLength];
                    mappedByteBuffer.get(fileBytes);
                    mappedByteBuffer.position(0);
                    byte lastByte = 1;
                    boolean littleEndian = encoding.contains("LE");
                    for(int i = 0;i < mappedFileLength;i ++) {
                        if(littleEndian) {
                            if ( fileBytes[i] == 0 && lastByte ==10) {
                                positions.add(i-1);
                                if(i % 999 == 0 && progressCallback != null) {
                                    if(!hasChapters) {
                                        return;
                                    }
                                    final int percent = i * 100 / mappedFileLength;
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressCallback.currentPercentage(percent);
                                        }
                                    });
                                }
                            }
                        }else {
                            if(fileBytes[i] == 0x0a) {
                                positions.add(i+1);
                                if ( i % 1000 == 0 && progressCallback != null) {
                                    if(!hasChapters) {
                                        return;
                                    }
                                    final int percent = i * 100 / mappedFileLength;
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressCallback.currentPercentage(percent);
                                        }
                                    });
                                }
                            }
                        }
                        lastByte = fileBytes[i];
                    }
                    Log.e("positions","load completely");
                }
            }
        }).start();
    }

    public void setProgressCallback(ProgressCallback callback) {
        progressCallback = callback;
    }
    public void setKeyword(String keyword){
        this.keyword = keyword;
    }
    public interface LoadCallback {
        void onFinishLoad(List<Chapter> List);
        void onNotFound();
    }
    public interface ProgressCallback {
        void currentPercentage(int percent);
    }
}
