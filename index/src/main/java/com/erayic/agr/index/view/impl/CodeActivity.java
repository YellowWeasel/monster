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
import com.erayic.agr.common.util.ErayicIdentifier;
import com.erayic.agr.common.util.ErayicRegularly;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.index.R;
import com.erayic.agr.index.R2;
import com.erayic.agr.index.presenter.ICodePresenter;
import com.erayic.agr.index.presenter.impl.CodePresenterImpl;
import com.erayic.agr.index.view.ICodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/index/Activity/CodeActivity", name = "验证")
public class CodeActivity extends BaseActivity implements ICodeView {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.index_code_tel)
    TextView indexCodeTel;
    @BindView(R2.id.index_code_verCode)
    EditText indexCodeVerCode;
    @BindView(R2.id.index_code_send)
    Button indexCodeSend;
    @BindView(R2.id.index_code_check)
    Button indexCodeCheck;

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
        toolbar.setTitle("验证信息");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void initData() {
        presenter = new CodePresenterImpl(this);
        time = new TimeCount(60000, 1000);
        indexCodeTel.setText(tel);
    }

    @OnClick(R2.id.index_code_send)
    public void onIndexCodeSendClicked() {
        presenter.sendTelVerify(tel);
    }

    @OnClick(R2.id.index_code_check)
    public void onIndexCodeCheckClicked() {
        if (!ErayicRegularly.isVerCode(indexCodeVerCode.getText().toString())) {
            indexCodeVerCode.setError("验证码格式错误");
        } else {
            presenter.checkTelVerify(tel, indexCodeVerCode.getText().toString(), ErayicIdentifier.getInstance(CodeActivity.this).getErayicdentifier());
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
                ErayicStack.getInstance().finishCurrentActivity();
            }
        });
    }

    private void showCodeCountdownNo(final long millisUntilFinished) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                indexCodeSend.setClickable(false);
                indexCodeSend.setText("再次发送(" + millisUntilFinished / 1000 + "s)");
            }
        });
    }

    private void showCodeCountdownYes() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                indexCodeSend.setClickable(true);
                indexCodeSend.setText("再次发送");
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
