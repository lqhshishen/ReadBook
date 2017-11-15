package com.liqihao.readbook;

import android.app.Activity;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Activity mContext;

    //    BindView(R.id.LN_main)
//        LinearLayout main;
//    BindView(R.id.toolbar)
//        android.support.v7.widget.Toolbar toolbar;
//    @BindView(R.id.top_view)
    View topView;
//    @BindView(R.id.bottom_view)
    View bottomView;
//    @BindView(R.id.top_rela)
    RelativeLayout topRela;
//    @BindView(R.id.bottom_rela)
    RelativeLayout bottomRela;
//    @BindView(R.id.aa)
    ImageView aa;
//    @BindView(R.id.content)
    ImageView content;
//    @BindView(R.id.day_night_mode)
    ImageView mode;
//    @BindView(R.id.more)
    ImageView more;
//    @BindView(R.id.Ln_main)
    CoordinatorLayout getmCoordinator;

    View.OnClickListener clickListener;

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        ButterKnife.bind(mContext);
        mDrawerLayout = findViewById(R.id.side_content);
        topRela = findViewById(R.id.top_rela);
        topView = findViewById(R.id.top_view);
        bottomView = findViewById(R.id.bottom_view);
        bottomRela = findViewById(R.id.bottom_rela);
        aa = findViewById(R.id.aa);
        content = findViewById(R.id.content);
        mode = findViewById(R.id.day_night_mode);
        more = findViewById(R.id.more);
        getmCoordinator = findViewById(R.id.Ln_main);
        getmCoordinator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topView.getVisibility() == View.GONE) {
                    topView.setVisibility(View.VISIBLE);
                    bottomRela.setVisibility(View.VISIBLE);
                    topRela.setVisibility(View.VISIBLE);
                    bottomView.setVisibility(View.VISIBLE);
                } else {
                    topView.setVisibility(View.GONE);
                    bottomRela.setVisibility(View.GONE);
                    topRela.setVisibility(View.GONE);
                    bottomView.setVisibility(View.GONE);
                }
            }
        });
        more.setOnClickListener(clickListener);
        mode.setOnClickListener(clickListener);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        aa.setOnClickListener(clickListener);
    }

}
