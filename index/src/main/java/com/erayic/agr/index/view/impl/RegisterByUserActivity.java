package com.erayic.agr.index.view.impl;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicRegularly;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.index.R;
import com.erayic.agr.index.presenter.IRegisterByUserPresenter;
import com.erayic.agr.index.presenter.impl.RegisterByUserPresenterImpl;
import com.erayic.agr.index.view.IRegisterByUserView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/index/Activity/RegisterByUserActivity", name = "用户注册")
public class RegisterByUserActivity extends BaseActivity implements IRegisterByUserView, View.OnClickListener {

    private Toolbar toolbar;

    private EditText index_register_user_phone, index_register_user_code, index_register_user_password, index_register_user_verCode;
    private TextView index_register_user_eg;
    private Button index_register_user_send, index_register_user_register;

    private IRegisterByUserPresenter presenter;
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_register_user);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("邀请码注册");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        index_register_user_phone = (EditText) findViewById(R.id.index_register_user_phone);
        index_register_user_code = (EditText) findViewById(R.id.index_register_user_code);
        index_register_user_password = (EditText) findViewById(R.id.index_register_user_password);
        index_register_user_verCode = (EditText) findViewById(R.id.index_register_user_verCode);
        index_register_user_eg = (TextView) findViewById(R.id.index_register_user_eg);
        index_register_user_send = (Button) findViewById(R.id.index_register_user_send);
        index_register_user_send.setOnClickListener(this);
        index_register_user_register = (Button) findViewById(R.id.index_register_user_register);
        index_register_user_register.setOnClickListener(this);
    }

    @Override
    public void initData() {
        presenter = new RegisterByUserPresenterImpl(this);
        time = new TimeCount(60000, 1000);
        index_register_user_eg.setText(getClickableSpan());
        //设置超链接可点击
        index_register_user_eg.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.index_register_user_send) {
            if (!ErayicRegularly.isPhone(index_register_user_phone.getText().toString()))
                index_register_user_phone.setError("请输入正确的手机号码");
            else {
                //发送验证码
                presenter.sendTelVerify(index_register_user_phone.getText().toString());
            }
        } else if (v.getId() == R.id.index_register_user_register) {
            if (isVerification()) {
                presenter.userByInvite(index_register_user_password.getText().toString(), index_register_user_phone.getText().toString(),
                        index_register_user_code.getText().toString(), index_register_user_verCode.getText().toString());
            }
        }
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(), msg);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home://返回
            {
                finish();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 验证信息
     */
    private boolean isVerification() {
        boolean pass = true;
        if (!ErayicRegularly.isPhone(index_register_user_phone.getText().toString())) {
            index_register_user_phone.setError("请输入正确的手机号码");
            pass = false;
        } else if (!ErayicRegularly.isVerCode(index_register_user_code.getText().toString())) {
            index_register_user_code.setError("邀请码格式错误");
            pass = false;
        } else if (!ErayicRegularly.isPassword(index_register_user_password.getText().toString())) {
            index_register_user_password.setError("6-21字母和数字组成，不能是纯数字或纯英文");
            pass = false;
        } else if (!ErayicRegularly.isVerCode(index_register_user_verCode.getText().toString())) {
            index_register_user_verCode.setError("验证码格式错误");
            pass = false;
        }
        return pass;
    }

    /**
     * 获取可点击的SpannableString
     *
     * @return
     */
    private SpannableString getClickableSpan() {
        SpannableString spannableString = new SpannableString("注册代表同意服务条款和隐私政策");
        //设置下划线文字
        spannableString.setSpan(new UnderlineSpan(), 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的单击事件
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                showToast("服务条款");
            }
        }, 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置下划线文字
        spannableString.setSpan(new UnderlineSpan(), 11, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的单击事件
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                showToast("隐私政策");
            }
        }, 11, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 11, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    private void showCodeCountdownNo(final long millisUntilFinished) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                index_register_user_send.setClickable(false);
                index_register_user_send.setText("再次发送(" + millisUntilFinished / 1000 + "s)");
            }
        });
    }

    private void showCodeCountdownYes() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                index_register_user_send.setClickable(true);
                index_register_user_send.setText("再次发送");
            }
        });
    }

    @Override
    public void verSendSure() {
        time.start();
    }

    @Override
    public void registerSure() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast("注册成功");
                RegisterByUserActivity.this.finish();
            }
        });
    }

    /* 定义一个倒计时的内部类 */
    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            showCodeCountdownYes();
        }

        @Override
        public void onTick(final long millisUntilFinished) {// 计时过程显示
            showCodeCountdownNo(millisUntilFinished);
        }
    }

}
