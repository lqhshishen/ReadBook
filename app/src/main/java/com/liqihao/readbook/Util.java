package com.liqihao.readbook;

/**
 * Created by liqihao on 2017/11/20.
 */

public class Util {
    public static int getPXWithDP(int dp){
        float density = GetContext.getContext().getResources().getDisplayMetrics().density;
        return (int)(dp*density);
    }
}
