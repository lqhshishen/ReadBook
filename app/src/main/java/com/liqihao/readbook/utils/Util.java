package com.liqihao.readbook.utils;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by liqihao on 2017/11/20.
 */

public class Util extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static int getPXWithDP(int dp){
        float density =GetContext.getContext().getResources().getDisplayMetrics().density;
        return (int)(dp*density);
        }

    public static void toast(String msg) {
        Toast.makeText(GetContext.getContext(), msg, Toast.LENGTH_SHORT).show();
        }

}
