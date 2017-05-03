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
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.index.R;
import com.erayic.agr.index.R2;
import com.erayic.agr.index.presenter.ILoginPresenter;
import com.erayic.agr.index.presenter.impl.LoginPresenterImpl;
import com.erayic.agr.index.view.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/index/Activity/LoginActivity", name = "登陆")
public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.index_login_et_tel)
    EditText indexLoginEtTel;
    @BindView(R2.id.index_login_et_pass)
    EditText indexLoginEtPass;
    @BindView(R2.id.index_login_btn_login)
    Button indexLoginBtnLogin;
    @BindView(R2.id.index_login_tv_register)
    TextView indexLoginTvRegister;
    @BindView(R2.id.login_tv_agreements)
    TextView loginTvAgreements;
    //    private Toolbar toolbar;
//
//    private EditText index_login_et_tel, index_login_et_pass;
//    private TextView index_login_tv_register;
//    private Button index_login_btn_login;
    private LoadingDialog dialog;

    private ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_login);
    }

    @Override
    public void initView() {
        toolbar.setTitle("登陆");
        setSupportActionBar(toolbar);

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


    @OnClick(R2.id.index_login_btn_login)
    public void onLoginViewClicked() {
        if (isVerification()) {
            presenter.login(indexLoginEtTel.getText().toString(), indexLoginEtPass.getText().toString());
        }
    }

    @OnClick(R2.id.index_login_tv_register)
    public void onRegisterViewClicked() {
        ARouter.getInstance().build("/index/Activity/RegisterActivity").navigation();
    }

    /**
     * 验证信息
     */
    private boolean isVerification() {
        boolean pass = true;
        if (!ErayicRegularly.isPhone(indexLoginEtTel.getText().toString())) {
            indexLoginEtTel.setError("请输入正确的手机号码");
            pass = false;
        } else if (indexLoginEtPass.getText().toString().length() < 6) {
            indexLoginEtPass.setError("密码格式错误");
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
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build("/index/Activity/CodeActivity").withString("tel", indexLoginEtTel.getText().toString()).navigation();
            }
        });
    }


}
