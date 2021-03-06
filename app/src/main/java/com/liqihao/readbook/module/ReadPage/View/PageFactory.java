package com.liqihao.readbook.module.ReadPage.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.BatteryManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.liqihao.readbook.app.App;
import com.liqihao.readbook.module.ReadPage.bean.Chapter;
import com.liqihao.readbook.R;
import com.liqihao.readbook.module.ReadPage.bean.Book;
import com.liqihao.readbook.module.ReadPage.presenter.PagePresenter;
import com.liqihao.readbook.utils.FileUtils;
import com.liqihao.readbook.utils.GetContext;
import com.liqihao.readbook.utils.SPHelper;
import com.liqihao.readbook.utils.Util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by liqihao on 2017/11/13.
 */

public class PageFactory {
    private int screenHeight, screenWidth; //屏幕尺寸
    private int pageHeight,pageWidth;    //文字排版页面尺寸
    private int lineNumber;//行数
    private int lineSpace = Util.getPXWithDP(5);
    private int fileLength;
    private int fontSize;

    private static final int margin = (int)(Util.getPXWithDP(20));
    private Paint mPaint;
    private int begin;//当前阅读的字节数_开始
    private int end;//当前阅读的字节数_结束
    private MappedByteBuffer mappedFile;//映射到内存中的文件
    private RandomAccessFile randomFile;//关闭random流时使用

    private String encoding;//字节码
    private Context mContext;

    private PageView mView;
    private Canvas mCanvas;

    private ArrayList<String> content = new ArrayList<>();

    private SPHelper spHelper = SPHelper.getInstance();

    private static PageFactory instance;

    private Paint xPaint;

    private List<Chapter.Result> allChapter = new ArrayList<>();

    private String bookId;

    private String charset = "UTF-8";

    String chapterName = "";

    private PageFactory(PageView view){
        DisplayMetrics metrics = new DisplayMetrics();
        mContext = view.getContext();
        mView = view;

        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        fontSize = spHelper.getFontSize();
        pageHeight = screenHeight - margin*2 - fontSize;
        pageWidth = screenWidth - margin*2;
        lineNumber = (pageHeight/(fontSize+lineSpace))-1;

        xPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xPaint.setTextSize(15);
        xPaint.setColor(mContext.getResources().getColor(R.color.colorGrey));

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(fontSize);
        mPaint.setColor(mContext.getResources().getColor(R.color.dayModeTextColor));

        Bitmap bitmap = Bitmap.createBitmap(screenWidth,screenHeight,Bitmap.Config.ARGB_8888);
        mView.setBitmap(bitmap);
        mCanvas = new Canvas(bitmap);
        mBatteryPait = new Paint();
    }

    public static PageFactory getInstance(){
        return instance;
    }

    public static PageFactory getInstance(PageView view,String chapter,String chapterName
            , int[]position ,String bookId) {
        if(instance == null) {
            synchronized (PageFactory.class) {
                if(instance == null) {
                    instance = new PageFactory(view);
                    instance.bookId = bookId;
                    instance.openBook(chapter,position,chapterName);
                }
            }
        }
        return instance;
    }

    /**
     * bug!
     * @param chapter
     * @return
     */
    public File getBookFile(String chapter) {
        File file = FileUtils.getChapterFile(bookId,chapter);
        if (file != null && file.length() > 10) {
            charset = FileUtils.getCharset(file.getAbsolutePath());
        }
        return file;
    }

    private String currentChapter;
//    private int chapterSize;

    /**
     *
     * @param chapter 章节ID
     * @param position 读取位置
     */
    public void openBook(String chapter, int[] position,String chapterName) {
        Log.e("test","testOpenBoos");
//        this.book = book;
//        this.chapterSize = allChapter.size();
        this.currentChapter = chapter;
//        if (Integer.parseInt(currentChapter) > Integer.parseInt(allChapter.get(chapterSize - 1)))
//            currentChapter = allChapter.get(chapterSize - 1);
        String path = getBookFile(currentChapter).getPath();
        File file = new File(path);
        fileLength = (int)file.length();
        encoding = charset;
        begin = position[0];
        end = position[1];
        this.chapterName = chapterName;
//        begin = spHelper.getBookmarkStart(book.getName());
//        end = spHelper.getBookmarkEnd(book.getName());
//        File file = new File(book.getPath());
//        fileLength = (int) file.length();
//        Log.i("文件长度",String.valueOf(fileLength));
        try {
            randomFile = new RandomAccessFile(file,"r");
            mappedFile = randomFile.getChannel().map(FileChannel.MapMode.READ_ONLY,0,(long)fileLength);
        } catch (Exception e) {
            Log.e("nmb",Log.getStackTraceString(e));
            Toast.makeText(mContext, "打开失败!", Toast.LENGTH_SHORT).show();

        }
    }

    //向后读取一个段落，返回bytes

    private byte[] readParagraphForward(int end) {
        byte b0;
        int before = 0;
        int i = end;
        while (i < fileLength) {
            b0 = mappedFile.get(i);
            if (encoding.equals("UTF-16LE")) {
                if (b0 == 0 && before == 10) {
                    break;
                }
            } else {
                if (b0 == 10) {
                    break;
                }
            }
            before = b0;
            i++;
        }
        i = Math.min(fileLength-1,i);
        int nParaSize = i - end + 1;

        byte[] buf = new byte[nParaSize];
        for(i = 0;i < nParaSize; i++) {
            buf[i] = mappedFile.get(end + i);
        }
        return buf;
    }

    //向前读取一个段落
    private byte[] readParagraphBack(int begin) {
        byte b0;
        byte before = 1;
        int i = begin -1;
        while (i > 0) {
            b0 = mappedFile.get(i);
            if(encoding.equals("UTF-16LE")){
                if (b0 == 10 && before==0 && i != begin-2) {
                    i+=2;
                    break;
                }
            }else {
                if(b0 == 0x0a && i != begin -1) {
                    i++;
                    break;
                }
            }
            i--;
            before = b0;
        }
        int nParaSize = begin -i;
        byte [] buf = new byte[nParaSize];
        for (int j = 0; j < nParaSize; j++) {
            buf[j] = mappedFile.get(i + j);
        }
        return buf;
    }

    int number = 0;

//    void getNumber(String str) {
//        if (str.contains("<br/>")) {
//            number = 5;
//        } else if (str.contains("br/>")) {
//            number = 4;
//        } else if (str.contains("r/>")) {
//            number = 3;
//        } else if (str.contains("/>")) {
//            number = 2;
//        } else if (str.contains(">")) {
//            number = 1;
//        }
//    }

    int allPage;
    //下一页的内容
    private void pageDown() {
        String strParagraph = "";
        int length = 0;
        while ((content.size() < lineNumber) && end <fileLength) {
            byte [] byteTemp = readParagraphForward(end);
            end += byteTemp.length;
            length += byteTemp.length;
            try {
                 strParagraph = new String(byteTemp,encoding);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            strParagraph = strParagraph.replace("\r\n"," ");
            strParagraph = strParagraph.replace("\n"," ");
//            strParagraph = strParagraph.replaceAll("<br/>","\n\r" + "  ");
            while(strParagraph.length() > 0) {
                int size = mPaint.breakText(strParagraph,true,pageWidth,null);
                    if (strParagraph.contains("<br/>") && strParagraph.indexOf("<br/>") < size) {
                        content.add(strParagraph.substring(0,strParagraph.indexOf("<br/>")));
                        strParagraph = ("      " + strParagraph.substring(strParagraph.indexOf("<br/>") + number));
                        number = 5;
                    } else {
                        content.add(strParagraph.substring(0, size));
                        if (size + number > strParagraph.length()) {
                            strParagraph = strParagraph.substring(size);
                        } else {
                            strParagraph = (strParagraph.substring(size + number));
                        }
                        number = 0;
                    }
                if(content.size() >= lineNumber) {
                    break;
                }
            }
            if(strParagraph.length() > 0) {
                try {
                    end -= (strParagraph).getBytes(encoding).length;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        /*
       计算页数（有误）
         */
        allPage = ((fileLength - 1) / (length + 1) );
    }


    //上翻页
    private void pageUp() {
        String strParagraph="";
        List<String> tempList = new ArrayList<>();
        while (tempList.size() < lineNumber && begin > 0) {
            byte[] byteTemp = readParagraphBack(begin);
            begin -= byteTemp.length;
            try {
                strParagraph = new String(byteTemp,encoding);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            strParagraph = strParagraph.replaceAll("\r\n","  ");
            strParagraph = strParagraph.replaceAll("\n","  ");
//            strParagraph = strParagraph.replaceAll("<br/>","\n" + "  ");
            while(strParagraph.length() > 0){
                int size = mPaint.breakText(strParagraph,true,pageWidth,null);
                if (strParagraph.contains("<br/>") && strParagraph.indexOf("<br/>") < size ) {
                    tempList.add(strParagraph.substring(0,strParagraph.indexOf("<br/>")));
                    strParagraph = ("      " + strParagraph.substring(strParagraph.indexOf("<br/>") + 5));
                } else {
                    tempList.add(strParagraph.substring(0, size));
                    strParagraph = (strParagraph.substring(size));
                    number = 0;
                }
                if(tempList.size() >= lineNumber){
                    break;
                }
            }
            if(strParagraph.length() > 0){
                try{
                    begin+= strParagraph.getBytes(encoding).length;
                }catch (UnsupportedEncodingException u){
                    u.printStackTrace();
                }
            }
        }
    }
    private Paint mBatteryPait;
    private List<Chapter>alist = new ArrayList<>();

    public void setList(List<Chapter>list){
        this.alist = list;
    }



    @SuppressLint("ResourceAsColor")
    public void printPage() {
        if (content.size() > 0) {
            int y = (int)(App.AppContext.getResources().getDisplayMetrics().density * 40);
            mCanvas.drawColor(mContext.getResources().getColor(R.color.colorDay));
            for(String line : content) {
                y += fontSize+lineSpace;
                mCanvas.drawText(line,margin,y,mPaint);
            }
            //绘制电池
            RectF rect = new RectF(Util.getPXWithDP(20),
                    screenHeight - Util.getPXWithDP(29),
                    Util.getPXWithDP(10) + Util.getPXWithDP(20),
                    screenHeight  - Util.getPXWithDP(15));
            mBatteryPait.setColor(R.color.colorBlack);
            mBatteryPait.setStyle(Paint.Style.STROKE);
            mCanvas.drawRect(rect, mBatteryPait);
            mBatteryPait.setStrokeWidth(0);
            RectF rect2 = new RectF(Util.getPXWithDP(20),
                    screenHeight + Util.getPXWithDP(getBatteryPower()) - Util.getPXWithDP(29),
                    Util.getPXWithDP(10) + Util.getPXWithDP(20),
                    screenHeight - Util.getPXWithDP(15));
            mBatteryPait.setStyle(Paint.Style.FILL);
            mCanvas.drawRect(rect2, mBatteryPait);
            RectF headRect = new RectF(Util.getPXWithDP(3) + Util.getPXWithDP(20),
                    screenHeight - Util.getPXWithDP(31),
                    Util.getPXWithDP(7) + Util.getPXWithDP(20),
                    screenHeight - Util.getPXWithDP(29));
            mCanvas.drawRect(headRect, mBatteryPait);
            /*
            显示时间
             */
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
            String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            mBatteryPait.setTextSize(30);
            mCanvas.drawText(time,Util.getPXWithDP(45),screenHeight - Util.getPXWithDP(18),mBatteryPait);
            /*
            页数
             */
            String now = "13/2048";
            mCanvas.drawText(now,screenWidth-Util.getPXWithDP(90),screenHeight-Util.getPXWithDP(18),mBatteryPait);
            /*
             /绘制章节头部
             */
            mCanvas.drawText(chapterName,Util.getPXWithDP(20),Util.getPXWithDP(35),mBatteryPait);
        }
        mView.invalidate();
    }

    private int getBatteryPower() {
        int batteryPower;
        Intent batteryIntent = mContext.registerReceiver
                (null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int scaledLevel = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        float a = (scaledLevel / scale);
        batteryPower = (int)(14 * (1 - a));
        return batteryPower;
    }

    public boolean isFinish() {
        return end >= fileLength;
    }

    public boolean isHavePreContent() {
        return begin <= 0;
    }

    private boolean isFinish = false;
    public boolean isChapterFinsh(){
        return isFinish;
    }

    /**
     * 读取前一章最后页
     */
    public void preChapterLastPage() {
        while (end < fileLength) {
            content.clear();
            pageDown();
            begin = end;
        }
        printPage();
    }

    /**
     * 下一页
     */
    public void nextPage() {
        if (end >= fileLength) {
            return;
        }else{
            content.clear();
            begin = end;
            pageDown();
        }
        printPage();
    }

    /**
     * 上一页
     */
    public void prePage() {
        if(begin <= 0) {
            return;
        }else{
            content.clear();
            pageUp();
            end = begin;
            pageDown();
        }
        printPage();
    }
    public int getFileLength() {
        return fileLength;
    }
    public MappedByteBuffer getMappedFile() {
        return mappedFile;
    }
    public void setPosition(int position) {
        end = position;
        nextPage();
    }
    public int getProgress() {
        return begin * 100 / fileLength;
    }
    public int setProgress(int i) {
        int origin = begin;
        end = fileLength * i/100;
        if (end == fileLength) {
            end--;
        }
        if(end == 0) {
            nextPage();
        }else{
            nextPage();
            prePage();
            nextPage();
        }
        return origin;
    }

    public String getEncoding() {
        return encoding;
    }
    public int getCurrentEnd() {
        return end;
    }
    public int getCurrentBegin() {
        return begin;
    }

    public void close() {
        if(instance != null) {
            try {
                instance.randomFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }
}
