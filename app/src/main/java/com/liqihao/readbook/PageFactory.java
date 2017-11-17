package com.liqihao.readbook;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/11/13.
 */

public class PageFactory {
    private int screenHeight, screenWidth; //屏幕尺寸
    private int pageHeight,pageWidth;    //文字排版页面尺寸
    private int lineNumber;//行数
    private int lineSpace = (int)(GetContext.getContext().getResources().getDisplayMetrics().density * 5);;
    private int fileLength;
    private int fontSize;

    private static final int margin = (int)(GetContext.getContext().getResources().getDisplayMetrics().density * 5);
    private Paint mPaint;
    private int begin;//当前阅读的字节数_开始
    private int end;//当前阅读的字节数_结束
    private MappedByteBuffer mappedFile;//映射到内存中的文件
    private RandomAccessFile randomFile;//关闭random流时使用

    private String encoding;
    private Context mContext;

    private PageView mView;
    private Canvas mCanvas;

    private ArrayList<String> content = new ArrayList<>();
    private Book book;

    private SPHelper spHelper = SPHelper.getInstance();

    private static PageFactory instance;


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
        lineNumber = pageHeight/(fontSize+lineSpace);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(fontSize);
        mPaint.setColor(mContext.getResources().getColor(R.color.dayModeTextColor));

        Bitmap bitmap = Bitmap.createBitmap(screenWidth,screenHeight,Bitmap.Config.ARGB_8888);
        mView.setBitmap(bitmap);
        mCanvas = new Canvas(bitmap);
    }

    public static PageFactory getInstance(){
        return instance;
    }

    public static PageFactory getInstance(PageView view,Book book) {
        if(instance == null) {
            synchronized (PageFactory.class) {
                if(instance == null) {
                    instance = new PageFactory(view);
                    instance.openBook(book);
                }
            }
        }
        return instance;
    }

    private void openBook(final Book book) {
        this.book = book;
        encoding = book.getEncoding();
        begin = spHelper.getBookmarkStart(book.getName());
        end = spHelper.getBookmarkEnd(book.getName());
        File file = new File(book.getPath());
        fileLength = (int) file.length();
        Log.i("文件长度",String.valueOf(fileLength));
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

    //下一页的内容
    private void pageDown() {
        String strParagraph = "";
        while (content.size() < lineNumber) {
            byte [] byteTemp = readParagraphForward(end);
            end += byteTemp.length;
            try {
                strParagraph = new String(byteTemp,encoding);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            strParagraph = strParagraph.replaceAll("\r\n","  ");
            strParagraph = strParagraph.replaceAll("\n","  ");
            while(strParagraph.length() >= 0) {
                int size = mPaint.breakText(strParagraph,true,pageWidth,null);
                content.add(strParagraph.substring(0,size));
                strParagraph = strParagraph.substring(size);
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
            while(strParagraph.length() > 0){
                int size = mPaint.breakText(strParagraph,true,pageWidth,null);
                tempList.add(strParagraph.substring(0, size));
                strParagraph = strParagraph.substring(size);
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
    public void printPage() {
        if (content.size() > 0) {
            int y = margin;
            mCanvas.drawColor(mContext.getResources().getColor(R.color.dayModeTextColor));
            for(String line : content) {
                y += fontSize+lineSpace;
                mCanvas.drawText(line,margin,y,mPaint);
                Log.e("小说内容",line);
            }
//            float percent = (float) begin / fileLength *100;
//            DecimalFormat format = new DecimalFormat("#0.00");
//            String readingProgress = format.format(percent) + "%";
//            int length = (int) mPaint.measureText(readingProgress);
//            mCanvas.drawText(readingProgress,(screenWidth - length) / 2
//                    ,screenHeight - margin,mPaint);
        }
        mView.invalidate();
    }

    private int serchPositionByKey (int beginPos,String key) {
        int position = begin;
        try {
            randomFile.seek(beginPos);
            String keyString = new String(key.getBytes(encoding), StandardCharsets.ISO_8859_1);
            String temp;
            long pointer;
            for(;;) {
                pointer = randomFile.getFilePointer();
                temp = randomFile.readLine();
                if(temp != null && temp.contains(keyString)) {
                    position = (int) pointer;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return position;
    }
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
    public void saveBookmark() {
        SPHelper.getInstance().setBookmarkEnd(book.getName(),begin);
        SPHelper.getInstance().setBookmarkStart(book.getName(),begin);
    }
    public int getFileLength() {
        return fileLength;
    }
    public MappedByteBuffer getMappedFile() {
        return mappedFile;
    }
    public MappedByteBuffer getMappedFilf() {
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

    public Book getBook() {
        return book;
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

    public static void close() {
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
