package com.erayic.agr.service.view.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonInvoiceBean;
import com.erayic.agr.common.util.ErayicRegularly;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.presenter.IInvoiceSettingPresenter;
import com.erayic.agr.service.presenter.impl.InvoiceSettingPresenterImpl;
import com.erayic.agr.service.view.IInvoiceSettingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/service/activity/InvoiceSettingActivity", name = "发票信息设置")
public class InvoiceSettingActivity extends BaseActivity implements IInvoiceSettingView {


    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.invoice_setting_ent_name)
    EditText invoiceSettingEntName;
    @BindView(R2.id.invoice_setting_user_name)
    EditText invoiceSettingUserName;
    @BindView(R2.id.invoice_setting_user_tel)
    EditText invoiceSettingUserTel;
    @BindView(R2.id.invoice_setting_user_area)
    TextView invoiceSettingUserArea;
    @BindView(R2.id.invoice_setting_area_layout)
    TableRow invoiceSettingAreaLayout;
    @BindView(R2.id.invoice_setting_user_address)
    EditText invoiceSettingUserAddress;

    private boolean isWrite = true;//是否可写
    private KeyListener keyListener;

    private IInvoiceSettingPresenter presenter;
    private LoadingDialog dialog;

    private CommonInvoiceBean invoiceBean, invoiceBeanBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_setting);
    }

    @Override
    public void initView() {
        toolbar.setTitle("发票信息设置");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        keyListener = invoiceSettingEntName.getKeyListener();
    }

    @Override
    public void initData() {
        presenter = new InvoiceSettingPresenterImpl(this);
        presenter.getInvoiceTitleInfo();
    }


    @OnClick(R2.id.invoice_setting_area_layout)
    public void onViewClicked() {

    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(InvoiceSettingActivity.this);
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
                    dialog = new LoadingDialog(InvoiceSettingActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateView(final CommonInvoiceBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                invoiceBean = bean;
                if (bean == null) {
                    isWrite = true;

                } else {
                    isWrite = false;
                }
                settingView();
                supportInvalidateOptionsMenu();
            }
        });
    }

    private void settingView() {

        invoiceSettingEntName.setText(invoiceBean == null ? "" : invoiceBean.getTitle());
        invoiceSettingUserName.setText(invoiceBean == null ? "" : invoiceBean.getReceiver());
        invoiceSettingUserTel.setText(invoiceBean == null ? "" : invoiceBean.getTel());
        invoiceSettingUserArea.setText(invoiceBean == null ? "海南省" : invoiceBean.getRegion());
        invoiceSettingUserAddress.setText(invoiceBean == null ? "" : invoiceBean.getAddress());
    }

    @Override
    public void submitSure() {
        showToast("保存成功");
        InvoiceSettingActivity.this.finish();
    }

    @Override
    public void getDataFailure() {
        InvoiceSettingActivity.this.finish();
    }

    /**
     * 检查数据合法性并提交
     */
    private void checkDataAndSubmit() {
        invoiceBeanBuffer = new CommonInvoiceBean();
        if (TextUtils.isEmpty(invoiceSettingEntName.getText().toString())) {
            invoiceSettingEntName.setError("发票抬头必须输入");
            return;
        } else
            invoiceBeanBuffer.setTitle(invoiceSettingEntName.getText().toString());

        if (TextUtils.isEmpty(invoiceSettingUserName.getText().toString())) {
            invoiceSettingUserName.setError("接收人必须输入");
            return;
        } else
            invoiceBeanBuffer.setReceiver(invoiceSettingUserName.getText().toString());

        if (!ErayicRegularly.isPhone(invoiceSettingUserTel.getText().toString())) {
            invoiceSettingUserTel.setError("请输入正确的手机号码");
            return;
        } else
            invoiceBeanBuffer.setTel(invoiceSettingUserTel.getText().toString());

        invoiceBeanBuffer.setRegion(invoiceSettingUserArea.getText().toString());

        invoiceBeanBuffer.setAddress(invoiceSettingUserAddress.getText().toString());

        presenter.updateInvoiceInfo(invoiceBeanBuffer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_invoice_setting_edit) {//编辑与不可编辑交替
            supportInvalidateOptionsMenu();
        } else if (item.getItemId() == R.id.action_invoice_setting_submit) {//提交更新
            checkDataAndSubmit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem submit = menu.findItem(R.id.action_invoice_setting_submit);
        MenuItem edit = menu.findItem(R.id.action_invoice_setting_edit);
        if (isWrite) {
            submit.setVisible(true);
            edit.setTitle("取消编辑");
            invoiceSettingEntName.setKeyListener(keyListener);
            invoiceSettingUserName.setKeyListener(keyListener);
            invoiceSettingUserTel.setKeyListener(keyListener);
            invoiceSettingUserArea.setKeyListener(keyListener);
            invoiceSettingUserAddress.setKeyListener(keyListener);
            isWrite = false;
        } else {
            submit.setVisible(false);
            edit.setTitle("编辑");
            invoiceSettingEntName.setKeyListener(null);
            invoiceSettingUserName.setKeyListener(null);
            invoiceSettingUserTel.setKeyListener(null);
            invoiceSettingUserArea.setKeyListener(null);
            invoiceSettingUserAddress.setKeyListener(null);
            settingView();
            isWrite = true;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.service_invoice_setting, menu);
        return true;
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(),msg);
            }
        });
    }


}
