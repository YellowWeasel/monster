package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.presenter.IFertilizerInfoPresenter;
import com.erayic.agr.manage.presenter.impl.FertilizerInfoPresenterImpl;
import com.erayic.agr.manage.view.IFertilizerInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/FertilizerInfoActivity", name = "化肥详情")
public class FertilizerInfoActivity extends BaseActivity implements IFertilizerInfoView {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.manage_fertilizer_RecyclerView)
    RecyclerView manageFertilizerRecyclerView;

    @Autowired
    boolean isAdd;//增加或者查看
    @Autowired
    String resID;//资源ID
    @Autowired
    String resName;//资源名称

    boolean isUpdater;//是否在修改状态

    private LoadingDialog dialog;

    private IFertilizerInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_fertilizer_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("资源详情");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initData() {
        presenter = new FertilizerInfoPresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_resource, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem save = menu.findItem(R.id.action_manage_resource_save);
        MenuItem modify = menu.findItem(R.id.action_manage_resource_update);
        MenuItem delete = menu.findItem(R.id.action_manage_resource_delete);
        if (isAdd) {
            save.setVisible(true);
            modify.setVisible(false);
            delete.setVisible(false);
        } else {
            if (isUpdater) {
                save.setVisible(true);
                modify.setVisible(true);
                modify.setTitle("取消编辑");
                delete.setVisible(false);
            } else {
                save.setVisible(false);
                modify.setVisible(true);
                modify.setTitle("编辑");
                delete.setVisible(true);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_resource_save) {

        } else if (item.getItemId() == R.id.action_manage_resource_update) {

        } else if (item.getItemId() == R.id.action_manage_resource_delete) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(FertilizerInfoActivity.this);
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
                    dialog = new LoadingDialog(FertilizerInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateSure(Object bean) {

    }

    @Override
    public void saveSure() {
        ErayicStack.getInstance().finishCurrentActivity();
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
}
