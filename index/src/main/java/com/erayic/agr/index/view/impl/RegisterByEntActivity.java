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
import com.erayic.agr.index.presenter.IRegisterByEntPresenter;
import com.erayic.agr.index.presenter.impl.RegisterByEntPresenterImpl;
import com.erayic.agr.index.view.IRegisterByEntView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/index/Activity/RegisterByEntActivity", name = "企业注册")
public class RegisterByEntActivity extends BaseActivity implements IRegisterByEntView, View.OnClickListener {

    private Toolbar toolbar;

    private EditText index_register_ent_phone, index_register_ent_entName, index_register_ent_userName, index_register_ent_password, index_register_ent_verCode;
    private Button index_register_ent_send, index_register_ent_register;
    private TextView index_register_ent_eg;

    private IRegisterByEntPresenter presenter;
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_register_ent);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("企业注册");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        index_register_ent_phone = (EditText) findViewById(R.id.index_register_ent_phone);
        index_register_ent_entName = (EditText) findViewById(R.id.index_register_ent_entName);
        index_register_ent_userName = (EditText) findViewById(R.id.index_register_ent_userName);
        index_register_ent_password = (EditText) findViewById(R.id.index_register_ent_password);
        index_register_ent_verCode = (EditText) findViewById(R.id.index_register_ent_verCode);
        index_register_ent_send = (Button) findViewById(R.id.index_register_ent_send);
        index_register_ent_send.setOnClickListener(this);
        index_register_ent_register = (Button) findViewById(R.id.index_register_ent_register);
        index_register_ent_register.setOnClickListener(this);
        index_register_ent_eg = (TextView) findViewById(R.id.index_register_ent_eg);
    }

    @Override
    public void initData() {
        presenter = new RegisterByEntPresenterImpl(this);
        time = new TimeCount(60000, 1000);
        index_register_ent_eg.setText(getClickableSpan());
        //设置超链接可点击
        index_register_ent_eg.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.index_register_ent_register) {//注册
            if (isVerification()) {
                presenter.enterpriseRegister(index_register_ent_phone.getText().toString(), index_register_ent_userName.getText().toString(),
                        index_register_ent_entName.getText().toString(), index_register_ent_password.getText().toString(),
                        index_register_ent_verCode.getText().toString());
            }
        } else if (v.getId() == R.id.index_register_ent_send) {//发送验证码
            if (!ErayicRegularly.isPhone(index_register_ent_phone.getText().toString()))
                index_register_ent_phone.setError("请输入正确的手机号码");
            else {
                //发送验证码
                presenter.sendTelVerify(index_register_ent_phone.getText().toString());
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
                index_register_ent_send.setClickable(false);
                index_register_ent_send.setText("再次发送(" + millisUntilFinished / 1000 + "s)");
            }
        });
    }

    private void showCodeCountdownYes() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                index_register_ent_send.setClickable(true);
                index_register_ent_send.setText("再次发送");
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
                RegisterByEntActivity.this.finish();
            }
        });
    }

    /**
     * 验证信息
     */
    private boolean isVerification() {
        boolean pass = true;
        if (!ErayicRegularly.isPhone(index_register_ent_phone.getText().toString())) {
            index_register_ent_phone.setError("请输入正确的手机号码");
            pass = false;
        } else if (!ErayicRegularly.isEntName(index_register_ent_entName.getText().toString())) {
            index_register_ent_entName.setError("请输入正确的企业名称");
            pass = false;
        } else if (!ErayicRegularly.isActuaName(index_register_ent_userName.getText().toString())) {
            index_register_ent_userName.setError("请输入真实姓名");
            pass = false;
        } else if (!ErayicRegularly.isPassword(index_register_ent_password.getText().toString())) {
            index_register_ent_password.setError("6-21字母和数字组成，不能是纯数字或纯英文");
            pass = false;
        } else if (!ErayicRegularly.isVerCode(index_register_ent_verCode.getText().toString())) {
            index_register_ent_verCode.setError("验证码格式错误");
            pass = false;
        }
        return pass;
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
