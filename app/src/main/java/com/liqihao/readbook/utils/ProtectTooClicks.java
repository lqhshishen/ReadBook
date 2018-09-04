package com.liqihao.readbook.utils;

public class ProtectTooClicks {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime = 0;
    public static boolean isDoubleClick(){
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME){
            lastClickTime = curClickTime;
            return true;
        }
        return false;
    }

}
