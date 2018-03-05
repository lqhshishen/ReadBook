package com.liqihao.readbook.utils;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.liqihao.readbook.app.App;

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
        float density = App.AppContext.getResources().getDisplayMetrics().density;
        return (int)(dp*density);
        }

    public static void toast(String msg) {
        Toast.makeText(App.AppContext, msg, Toast.LENGTH_SHORT).show();
        }

}
