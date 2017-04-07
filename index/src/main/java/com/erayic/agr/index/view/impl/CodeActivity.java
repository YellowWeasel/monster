package com.erayic.agr.index.view.impl;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicRegularly;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.index.R;
import com.erayic.agr.index.presenter.ICodePresenter;
import com.erayic.agr.index.presenter.impl.CodePresenterImpl;
import com.erayic.agr.index.view.ICodeView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/index/Activity/CodeActivity", name = "验证")
public class CodeActivity extends BaseActivity implements ICodeView, View.OnClickListener {

    private Toolbar toolbar;

    private TextView index_code_tel;
    private EditText index_code_verCode;
    private Button index_code_send, index_code_check;

    @Autowired
    String tel;

    private TimeCount time;

    private ICodePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_code);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("验证信息");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        index_code_tel = (TextView) findViewById(R.id.index_code_tel);
        index_code_verCode = (EditText) findViewById(R.id.index_code_verCode);
        index_code_send = (Button) findViewById(R.id.index_code_send);
        index_code_send.setOnClickListener(this);
        index_code_check = (Button) findViewById(R.id.index_code_check);
        index_code_check.setOnClickListener(this);
    }

    @Override
    public void initData() {
        presenter = new CodePresenterImpl(this);
        time = new TimeCount(60000, 1000);
        index_code_tel.setText(tel);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.index_code_check) {
            if (!ErayicRegularly.isVerCode(index_code_verCode.getText().toString())) {
                index_code_verCode.setError("验证码格式错误");
            } else {
                presenter.checkTelVerify(tel, index_code_verCode.getText().toString());
            }
        } else if (v.getId() == R.id.index_code_check) {
            presenter.sendTelVerify(tel);
        }
    }

    @Override
    public void verSendSure() {
        time.start();
    }

    @Override
    public void checkSure() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setResult(9000);
            }
        });
    }

    private void showCodeCountdownNo(final long millisUntilFinished) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                index_code_send.setClickable(false);
                index_code_send.setText("再次发送(" + millisUntilFinished / 1000 + "s)");
            }
        });
    }

    private void showCodeCountdownYes() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                index_code_send.setClickable(true);
                index_code_send.setText("再次发送");
            }
        });
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
