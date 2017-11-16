package com.liqihao.readbook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;

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
    @BindView(R.id.bottom_view)
    View bottomView;
    @BindView(R.id.top_rela)
    RelativeLayout topRela;
    @BindView(R.id.bottom_lin)
    LinearLayout bottomLin;
    @BindView(R.id.aa)
    ImageView aa;
    @BindView(R.id.content)
    ImageView content;
    @BindView(R.id.day_night_mode)
    ImageView mode;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.Ln_main)
    RelativeLayout relativeLayout;

    View.OnClickListener clickListener;
//    @BindView(R.id.main_btn)
//    Button mainBtn;

    private DrawerLayout mDrawerLayout;

    AgentWeb mAgentWeb;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        ButterKnife.bind(this);
        mDrawerLayout = findViewById(R.id.side_content);
        topRela = findViewById(R.id.top_rela);
        topView = findViewById(R.id.top_view);
        bottomView = findViewById(R.id.bottom_view);
        bottomLin = findViewById(R.id.bottom_lin);
        aa = findViewById(R.id.aa);
        content = findViewById(R.id.content);
        mode = findViewById(R.id.day_night_mode);
        more = findViewById(R.id.more);
        relativeLayout = findViewById(R.id.Ln_main);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topView.getVisibility() == View.GONE) {
                    topView.setVisibility(View.VISIBLE);
                    bottomLin.setVisibility(View.VISIBLE);
                    topRela.setVisibility(View.VISIBLE);
                    bottomView.setVisibility(View.VISIBLE);
                    v.setSystemUiVisibility(View.VISIBLE);
                } else {
                    topView.setVisibility(View.GONE);
                    bottomLin.setVisibility(View.GONE);
                    topRela.setVisibility(View.GONE);
                    bottomView.setVisibility(View.GONE);
                    v.setSystemUiVisibility(View.INVISIBLE);
                }
            }
        });
        more.setOnClickListener(clickListener);
        mode.setOnClickListener(clickListener);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
                mDrawerLayout.setClickable(true);
            }
        });
        aa.setOnClickListener(clickListener);
//
//        mAgentWeb = AgentWeb.with(mContext)
//                .setAgentWebParent(relativeLayout, new RelativeLayout.LayoutParams(-1,-1))
//                .useDefaultIndicator()
//                .defaultProgressBarColor()
//                .createAgentWeb()
//                .ready()
//                .go("file:///android_asset/a123.html");
    }

//    @OnClick({R.id.Ln_main, R.id.aa, R.id.content, R.id.day_night_mode, R.id.more})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.Ln_main:
//                if (topView.getVisibility() == View.GONE) {
//                    topView.setVisibility(View.VISIBLE);
//                    bottomRela.setVisibility(View.VISIBLE);
//                    topRela.setVisibility(View.VISIBLE);
//                    bottomView.setVisibility(View.VISIBLE);
//                } else {
//                    topView.setVisibility(View.GONE);
//                    bottomRela.setVisibility(View.GONE);
//                    topRela.setVisibility(View.GONE);
//                    bottomView.setVisibility(View.GONE);
//                }
//                break;
//            case R.id.aa:
//                break;
//            case R.id.content:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                break;
//            case R.id.day_night_mode:
//                break;
//            case R.id.more:
//                break;
//        }
//    }

}
