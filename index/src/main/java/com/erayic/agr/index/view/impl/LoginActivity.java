package com.erayic.agr.index.view.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.index.R;
import com.erayic.agr.index.presenter.ILoginPresenter;
import com.erayic.agr.index.presenter.impl.LoginPresenterImpl;
import com.erayic.agr.index.view.ILoginView;
import com.jaeger.library.StatusBarUtil;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/index/LoginActivity", name = "登陆页面")
public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    private Toolbar toolbar;

    private EditText index_login_et_tel, index_login_et_pass;
    private TextView index_login_tv_register;
    private Button index_login_btn_login;

    private ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_login);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("登陆");
        setSupportActionBar(toolbar);

        index_login_et_tel = (EditText) findViewById(R.id.index_login_et_tel);
        index_login_et_pass = (EditText) findViewById(R.id.index_login_et_pass);
        index_login_tv_register = (TextView) findViewById(R.id.index_login_tv_register);
        index_login_tv_register.setOnClickListener(this);
        index_login_btn_login = (Button) findViewById(R.id.index_login_btn_login);
        index_login_btn_login.setOnClickListener(this);
    }

    @Override
    public void initData() {
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucent(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    @Override
    public void showToast(final String msg) {
        baseHandler.post(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(), msg, ErayicToast.BOTTOM);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.index_login_tv_register) {

        } else if (v.getId() == R.id.index_login_btn_login) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("pass-login", true);
            bundle.putBoolean("pass-code", false);
            ARouter.getInstance().build("/main/MainActivity").with(bundle).navigation();
        }
    }
}
