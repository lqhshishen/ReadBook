package com.liqihao.readbook;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Activity mContext;

//    BindView(R.id.LN_main)
//        LinearLayout main;
//    BindView(R.id.toolbar)
//        android.support.v7.widget.Toolbar toolbar;
    LinearLayout Ln;

    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        ButterKnife.bind(mContext);
    }
}
