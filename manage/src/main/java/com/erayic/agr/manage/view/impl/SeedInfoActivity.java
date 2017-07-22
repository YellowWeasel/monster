package com.erayic.agr.manage.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.ManageRefreshMessage;
import com.erayic.agr.common.net.back.CommonSeedBean;
import com.erayic.agr.common.net.back.enums.EnumResourceType;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicTextDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.presenter.ISeedInfoPresenter;
import com.erayic.agr.manage.presenter.impl.SeedInfoPresenterImpl;
import com.erayic.agr.manage.view.ISeedInfoView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/SeedInfoActivity", name = "种子详情")
public class SeedInfoActivity extends BaseActivity implements ISeedInfoView {


    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_content_name)
    EditText manageContentName;
    @BindView(R2.id.manage_content_factory)
    EditText manageContentFactory;
    @BindView(R2.id.manage_content_pid)
    EditText manageContentPid;

    @Autowired
    boolean isAdd;//增加或者查看
    @Autowired
    String resID;//资源ID
    @Autowired
    String resName;//资源名称

    boolean isUpdater;//是否在修改状态
    private KeyListener keyListener;

    private CommonSeedBean seedBean;

    private LoadingDialog dialog;

    private ISeedInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_seed_info);
    }

    @Override
    public void initView() {
        toolbar.setTitle("资源详情");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        keyListener = manageContentPid.getKeyListener();
    }

    @Override
    public void initData() {
        presenter = new SeedInfoPresenterImpl(this);
        if (isAdd) {
            toolbar.setTitle("增加种子");
            seedBean = new CommonSeedBean();
            initViewWrite();
        } else {
            toolbar.setTitle(resName);
            presenter.getSpecifyResources(resID, EnumResourceType.TYPE_SEED);
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
                initViewWrite();
                save.setVisible(true);
                modify.setVisible(true);
                modify.setTitle("取消编辑");
                delete.setVisible(false);
            } else {
                initViewRead();
                if (seedBean != null)
                    updateSure(seedBean);
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
            if (!TextUtils.isEmpty(manageContentName.getText().toString())) {
                seedBean.setPID(manageContentPid.getText().toString());
                seedBean.setProductName(manageContentName.getText().toString());
                seedBean.setManufacturer(manageContentFactory.getText().toString());
                presenter.saveSeed(seedBean);
            } else {
                showToast("请输入种子名称");
            }
        } else if (item.getItemId() == R.id.action_manage_resource_update) {
            if (seedBean.isReadOnly()) {
                showToast("不可编辑");
            } else {
                isUpdater = !isUpdater;
                supportInvalidateOptionsMenu();
            }
        } else if (item.getItemId() == R.id.action_manage_resource_delete) {
            new ErayicTextDialog.Builder(SeedInfoActivity.this)
                    .setMessage("将要删除该种子的一切信息\n确定删除吗？", null)
                    .setTitle("温馨提示")
                    .setButton1("取消", new ErayicTextDialog.OnClickListener() {
                        @Override
                        public void onClick(Dialog dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setButton2("确定", new ErayicTextDialog.OnClickListener() {
                        @Override
                        public void onClick(Dialog dialog, int which) {
                            dialog.dismiss();
                            presenter.deleteResource(resID, EnumResourceType.TYPE_SEED);
                        }
                    }).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(SeedInfoActivity.this);
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
                    dialog = new LoadingDialog(SeedInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateSure(final CommonSeedBean bean) {
        seedBean = bean;
        initViewRead();
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                manageContentPid.setText(bean.getPID() == null ? "" : bean.getPID());
                manageContentName.setText(bean.getProductName() == null ? "" : bean.getProductName());
                manageContentFactory.setText(bean.getManufacturer() == null ? "" : bean.getManufacturer());
            }
        });
    }

    private void initViewRead() {
        manageContentPid.setKeyListener(null);
        manageContentName.setKeyListener(null);
        manageContentFactory.setKeyListener(null);
    }

    private void initViewWrite() {
        manageContentPid.setKeyListener(keyListener);
        manageContentName.setKeyListener(keyListener);
        manageContentFactory.setKeyListener(keyListener);
    }

    @Override
    public void saveSure() {
        ManageRefreshMessage message = new ManageRefreshMessage();
        message.setMsgType(ManageRefreshMessage.MANAGE_MASTER_SEED_LIST);
        EventBus.getDefault().post(message);
        ErayicStack.getInstance().finishCurrentActivity();
    }
}
