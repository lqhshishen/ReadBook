package com.liqihao.readbook.module.Login.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liqihao.readbook.R;
import com.liqihao.readbook.base.BaseActivity;
import com.liqihao.readbook.module.Home.ui.ActivityHome;
import com.liqihao.readbook.module.Login.bean.MobileReg;
import com.liqihao.readbook.module.Login.bean.SendCodeBean;
import com.liqihao.readbook.module.Login.contract.RegisterContract;
import com.liqihao.readbook.module.Login.presenter.RegisterPresenter;
import com.liqihao.readbook.utils.GetContext;
import com.liqihao.readbook.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("Registered")
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.view {


    @BindView(R.id.register_edtinputtel)
    EditText registerEdtinputtel;
    @BindView(R.id.register_inputnumber)
    EditText edtInputnumber;
    @BindView(R.id.register_edtbutton)
    Button registerEdtbutton;
    @BindView(R.id.register_btnregister)
    Button registerBtnregister;
    @BindView(R.id.register_edtinputPass)
    EditText registerEdtinputPass;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.textView)
    TextView textView;


    @Override
    public void bindView() {
        ButterKnife.bind(this);
        textView.setText("注册页面");
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void setPresenter(RegisterPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = new RegisterPresenter();
        }
    }

    @OnClick({R.id.register_edtinputtel, R.id.register_edtinputPass, R.id.register_inputnumber, R.id.register_edtbutton, R.id.register_btnregister,R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.register_edtinputtel:
//                break;
//            case R.id.register_edtinputPass:
//
//                break;
//            case R.id.register_inputnumber:
//                break;
            case R.id.register_edtbutton:
                if (!TextUtils.isEmpty(registerEdtinputtel.getText())) {
                    if (registerEdtinputtel.getText().toString().length() != 11) {
                        Toast.makeText(this, "请输入11位的手机号码", Toast.LENGTH_SHORT).show();
                    }
                    presenter.getCode(registerEdtinputtel.getText().toString());
                    onStartGetMsg();
                } else {
                    Toast.makeText(this, "您还未输入手机号码", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.register_btnregister:
                presenter.doRegister(registerEdtinputtel.getText().toString()
                ,registerEdtinputPass.getText().toString()
                ,edtInputnumber.getText().toString());
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }

    int duration = 30 * 1000;
    int tempDuration = 0;
    Timer mTimer;
    TimerTask mTimerTask;
    String clickafter = "秒";
    String clickbefore = "获取";
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        public void handleMessage(Message msg) {
            registerEdtbutton.setText(tempDuration / 1000 + clickafter);
            tempDuration -= 1000;
            if (tempDuration < 0) {
                registerEdtbutton.setEnabled(true);
                registerEdtbutton.setTextColor(RegisterActivity.this.getResources().getColor(R.color.colorRed));
                registerEdtbutton.setText(clickbefore);
                mTimerTask.cancel();
            }
        }
    };

    @Override
    public void onStartGetMsg() {
        tempDuration = duration;
        registerEdtbutton.setTextColor(this.getResources().getColor(R.color.colorGrey));
        registerEdtbutton.setEnabled(false);
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0x01);
            }
        };
        mTimer.schedule(mTimerTask, 0, 1000);
    }

    @Override
    public void onGetMsg(SendCodeBean sendCodeBean) {
        Log.e("getMsg", sendCodeBean.getResult().getVcode());
        Log.e("getMsg", sendCodeBean.getCode() + sendCodeBean.getSuccess());
    }

    @Override
    public void onRegister(MobileReg mobileReg) {
        if ("success".equals(mobileReg.getMsg())) {
            ToastUtils.showShort(GetContext.getContext(),"注册成功,转回登录页面");
            finish();
        } else {
            ToastUtils.showShort(getApplicationContext(),"验证码输入有误或账号已经注册");
        }
    }


}
