package com.liqihao.readbook.ReadPage.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.liqihao.readbook.ReadPage.contract.PageViewContract;

/**
 * Created by liqihao on 2017/11/13.
 */

public class PageView extends View implements PageViewContract{
    private Bitmap bit;
    private int touchSlop;
    private OnScrollListener mScroll;
    private OnClickCallback mClick;
    private int x;
    private int currentX;
    private boolean moved;
    public PageView(Context context) {
        super(context);
    }
    public PageView(Context context, AttributeSet attr) {
        super(context,attr);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClick != null && Math.abs(x - currentX) < touchSlop) {
                    int width = getWidth();
                    if (x > width * 2/3) {
                        mClick.onRightClick();
                    }else if (x < width / 3) {
                        mClick.onLeftClick();
                    }else {
                        mClick.onMiddleClick();
                    }
                }
            }
        });
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            x = (int) event.getX();
        }else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (Math.abs(event.getX()-x) > touchSlop) {
                moved = true;
            }
        }else if (event.getAction() == MotionEvent.ACTION_UP) {
            currentX = (int) event.getX();
            if (moved && mScroll != null) {
                if(x > currentX) {
                    mScroll.onLeftScroll();
                }else {
                    mScroll.onRightScroll();
                }
            }
            moved = false;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawBitmap(bit,0,0,null);
        canvas.restore();
    }

    public void setBitmap(Bitmap bitmap) {
        bit = bitmap;
    }
    public void setOnClickCallback(OnClickCallback listener){
        mClick = listener;
    }
    public void setOnScrollListener(OnScrollListener listener) {
        mScroll = listener;
    }
}
