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
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicRegularly;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.index.R;
import com.erayic.agr.index.presenter.ILoginPresenter;
import com.erayic.agr.index.presenter.impl.LoginPresenterImpl;
import com.erayic.agr.index.view.ILoginView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/index/Activity/LoginActivity", name = "登陆")
public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    private Toolbar toolbar;

    private EditText index_login_et_tel, index_login_et_pass;
    private TextView index_login_tv_register;
    private Button index_login_btn_login;
    private LoadingDialog dialog;

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
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(LoginActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    public void dismissLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(LoginActivity.this);
                dialog.dismiss();
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
    public void onClick(View v) {
        if (v.getId() == R.id.index_login_tv_register) {
            ARouter.getInstance().build("/index/Activity/RegisterActivity").navigation();
        } else if (v.getId() == R.id.index_login_btn_login) {
            if (isVerification()) {
                presenter.login(index_login_et_tel.getText().toString(), index_login_et_pass.getText().toString());
            }
        }
    }

    /**
     * 验证信息
     */
    private boolean isVerification() {
        boolean pass = true;
        if (!ErayicRegularly.isPhone(index_login_et_tel.getText().toString())) {
            index_login_et_tel.setError("请输入正确的手机号码");
            pass = false;
        } else if (index_login_et_pass.getText().toString().length() < 6) {
            index_login_et_pass.setError("密码格式错误");
            pass = false;
        }
        return pass;
    }

    @Override
    public void loginSure() {
        ARouter.getInstance().build("/main/Activity/MainActivity").navigation();
        LoginActivity.this.finish();
    }

    @Override
    public void toCodeActivity() {
        ARouter.getInstance().build("/index/CodeActivity").withString("tel", index_login_et_tel.getText().toString()).navigation();
    }


}
