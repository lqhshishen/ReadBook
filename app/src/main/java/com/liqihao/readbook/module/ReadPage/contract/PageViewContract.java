package com.liqihao.readbook.module.ReadPage.contract;

/**
 * Created by liqihao on 2017/11/23.
 */

public interface PageViewContract {
     interface OnScrollListener {
        void onLeftScroll();
        void onRightScroll();
    }

     interface OnClickCallback {
        void onLeftClick();
        void onMiddleClick();
        void onRightClick();
    }
}
