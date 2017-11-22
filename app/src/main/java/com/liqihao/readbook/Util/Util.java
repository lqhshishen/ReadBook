package com.liqihao.readbook.Util;

import android.app.Application;

/**
 * Created by liqihao on 2017/11/20.
 */

public class Util extends Application{
    public static int getPXWithDP(int dp){
        float density = GetContext.getContext().getResources().getDisplayMetrics().density;
        return (int)(dp*density);
    }
}
