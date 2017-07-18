package com.erayic.agr.service.view.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TableRow;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.view.IBusinessAccountView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/service/activity/BusinessAccountActivity", name = "企业账户")
public class BusinessAccountActivity extends BaseActivity implements IBusinessAccountView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.service_business_notes)
    TableRow serviceBusinessNotes;
    @BindView(R2.id.service_business_invoice)
    TableRow serviceBusinessInvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_account);
    }

    @Override
    public void initView() {
        toolbar.setTitle("企业账户");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void initData() {

    }


    @OnClick(R2.id.service_business_notes)
    public void onServiceBusinessNotesClicked() {
        ARouter.getInstance().build("/service/activity/OrderByEntActivity").navigation();

    }

    @OnClick(R2.id.service_business_invoice)
    public void onServiceBusinessInvoiceClicked() {
        ARouter.getInstance().build("/service/activity/InvoiceSettingActivity").navigation();
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
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
