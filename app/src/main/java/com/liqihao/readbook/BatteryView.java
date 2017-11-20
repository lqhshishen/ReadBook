package com.liqihao.readbook;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liqihao on 2017/11/20.
 */

public class BatteryView extends View{
    private int mPower = 100;
    private int orientation;
    private int width;
    private int height;
    private int mColor;

    public BatteryView(Context context) {
        super(context);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.Battery);
        mColor = typedArray.getColor(R.styleable.Battery_batteryColor,0xFFFFFFFF);
        orientation = typedArray.getInt(R.styleable.Battery_batterOrientation,0);
        mPower = typedArray.getInt(R.styleable.Battery_batteryPower,100);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();

        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (orientation != 0) {
            drawVerticalBattery(canvas);
        }
    }

    private void drawVerticalBattery(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(mColor);
        paint.setStyle(Paint.Style.STROKE);
        float strokeWidth = height / 20.0f;
        float strokeWidth2 = strokeWidth /2;
        paint.setStrokeWidth(strokeWidth);
        int headHeight = (int) (strokeWidth + 0.5f);
        RectF rect = new RectF(strokeWidth2,headHeight + strokeWidth2,
                width - strokeWidth2,height -strokeWidth2);
        canvas.drawRect(rect,paint);
        paint.setStrokeWidth(0);
        float topOFFset = (height - headHeight -strokeWidth) * (100 - mPower) / 100.0f;
        RectF rect2 = new RectF(strokeWidth,headHeight + strokeWidth + topOFFset,
                width - strokeWidth,height - strokeWidth);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect2,paint);
        RectF headRect = new RectF(width / 4.0f,0,width * 0.75f,headHeight);
        canvas.drawRect(headRect,paint);
    }


    public void setPower(int power) {
        this.mPower = power;
                if(mPower < 0) {
                    mPower = 100;
                }
                invalidate();
    }

    public void setColor(int color) {
        this.mColor = color;
        invalidate();
    }

    public int getPower() {
        return mPower;
    }
}
