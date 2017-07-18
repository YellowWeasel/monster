package com.erayic.agr.manage.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.event.ManageRefreshMessage;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.enums.EnumUserRole;
import com.erayic.agr.common.util.ErayicRegularly;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicTextDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.presenter.IPersonnelInfoPresenter;
import com.erayic.agr.manage.presenter.impl.PersonnelInfoPresenterImpl;
import com.erayic.agr.manage.view.IPersonnelInfoView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/PersonnelInfoActivity", name = "增加删除修改人员")
public class PersonnelInfoActivity extends BaseActivity implements IPersonnelInfoView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_personnel_info_name)
    EditText managePersonnelInfoName;
    @BindView(R2.id.manage_personnel_info_tel)
    EditText managePersonnelInfoTel;
    @BindView(R2.id.manage_personnel_info_admin)
    RadioButton managePersonnelInfoAdmin;
    @BindView(R2.id.manage_personnel_info_producer)
    RadioButton managePersonnelInfoProducer;
    @BindView(R2.id.manage_personnel_info_group)
    RadioGroup managePersonnelInfoGroup;

    private LoadingDialog dialog;

    @Autowired
    boolean isAdd;//是否增加
    @Autowired
    CommonPersonnelBean bean;//用户信息

    private boolean isWrite = false;//是否可写
    private KeyListener keyListener;

    private IPersonnelInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_personnel_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        toolbar.setTitle("人员信息");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        keyListener = managePersonnelInfoName.getKeyListener();
    }

    @Override
    public void initData() {
        presenter = new PersonnelInfoPresenterImpl(this);
        if (!isAdd) {
            managePersonnelInfoName.setText(TextUtils.isEmpty(bean.getName()) ? "" : bean.getName());
            managePersonnelInfoTel.setText(TextUtils.isEmpty(bean.getTel()) ? "" : bean.getTel());
            if (bean.getRole() == EnumUserRole.Role_Manager) {
                managePersonnelInfoAdmin.setChecked(true);
            } else if (bean.getRole() == EnumUserRole.Role_Usage) {
                managePersonnelInfoProducer.setChecked(true);
            }
        }
    }


    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(PersonnelInfoActivity.this);
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
                    dialog = new LoadingDialog(PersonnelInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void sendSure() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ManageRefreshMessage message = new ManageRefreshMessage();
                message.setMsgType(ManageRefreshMessage.MANAGE_MASTER_PERSONNEL_LIST);
                EventBus.getDefault().post(message);
                ErayicStack.getInstance().finishCurrentActivity();//出栈
            }
        });
    }

    /**
     * VIEW不可编辑
     */
    private void disableView() {
        managePersonnelInfoName.setKeyListener(null);
        managePersonnelInfoName.setBackground(null);
        managePersonnelInfoTel.setKeyListener(null);
        managePersonnelInfoTel.setBackground(null);
        if (isWrite) {
            for (int i = 0; i < managePersonnelInfoGroup.getChildCount(); i++) {
                managePersonnelInfoGroup.getChildAt(i).setEnabled(true);
            }
        } else {
            for (int i = 0; i < managePersonnelInfoGroup.getChildCount(); i++) {
                managePersonnelInfoGroup.getChildAt(i).setEnabled(false);
            }
        }
    }

    /**
     * VIEW可编辑
     */
    private void enableView() {
        managePersonnelInfoName.setKeyListener(keyListener);
        managePersonnelInfoName.setBackground(ContextCompat.getDrawable(PersonnelInfoActivity.this, R.drawable.app_base_edit_back_gray));
        managePersonnelInfoTel.setKeyListener(keyListener);
        managePersonnelInfoTel.setBackground(ContextCompat.getDrawable(PersonnelInfoActivity.this, R.drawable.app_base_edit_back_gray));

        for (int i = 0; i < managePersonnelInfoGroup.getChildCount(); i++) {
            managePersonnelInfoGroup.getChildAt(i).setEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_personnel_save) {//保存
            if (isAdd) {
                if (!ErayicRegularly.isPhone(managePersonnelInfoTel.getText().toString())) {
                    managePersonnelInfoTel.setError("请输入正确的手机号码");
                    return super.onOptionsItemSelected(item);
                }
                if (TextUtils.isEmpty(managePersonnelInfoName.getText().toString())) {
                    managePersonnelInfoName.setError("请输入用户姓名");
                    return super.onOptionsItemSelected(item);
                }
                if (managePersonnelInfoGroup.getCheckedRadioButtonId() == R.id.manage_personnel_info_admin) {
                    presenter.sendInvite(managePersonnelInfoTel.getText().toString(), managePersonnelInfoName.getText().toString(), 1);
                } else if (managePersonnelInfoGroup.getCheckedRadioButtonId() == R.id.manage_personnel_info_producer) {
                    presenter.sendInvite(managePersonnelInfoTel.getText().toString(), managePersonnelInfoName.getText().toString(), 9);
                } else {
                    showToast("请选择用户权限");
                }
            } else {
                if (managePersonnelInfoGroup.getCheckedRadioButtonId() == R.id.manage_personnel_info_admin) {
                    presenter.updateUserInfo(bean.getUserID(), managePersonnelInfoTel.getText().toString(), managePersonnelInfoName.getText().toString(), 1);
                } else if (managePersonnelInfoGroup.getCheckedRadioButtonId() == R.id.manage_personnel_info_producer) {
                    presenter.updateUserInfo(bean.getUserID(), managePersonnelInfoTel.getText().toString(), managePersonnelInfoName.getText().toString(), 9);
                } else {
                    showToast("请选择用户权限");
                }
            }
        } else if (item.getItemId() == R.id.action_manage_personnel_modify) {//编辑与不可编辑交替
            if (PreferenceUtils.getParam("UserID", "").equals(bean.getUserID())) {
                showToast("不可编辑自己，如需更改请前往个人信息");
                return super.onOptionsItemSelected(item);
            }
            supportInvalidateOptionsMenu();
        } else if (item.getItemId() == R.id.action_manage_personnel_delete) {//删除
            new ErayicTextDialog.Builder(PersonnelInfoActivity.this)
                    .setMessage("删除用户后该用户会失去您基地中的所有操作权限。\n您确定要把该用户从当前基地中删除吗？", null)
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
                            if (PreferenceUtils.getParam("UserID", "").equals(bean.getUserID())) {
                                showToast("不可删除自己");
                            } else
                                presenter.deleteUser(bean.getUserID());
                            dialog.dismiss();
                        }
                    }).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem save = menu.findItem(R.id.action_manage_personnel_save);
        MenuItem modify = menu.findItem(R.id.action_manage_personnel_modify);
        MenuItem delete = menu.findItem(R.id.action_manage_personnel_delete);
        if (isAdd) {
            save.setVisible(true);
            save.setTitle("发送邀请");
            modify.setVisible(false);
            delete.setVisible(false);
            enableView();
        } else {
            disableView();
            if (isWrite) {
                save.setVisible(true);
                save.setTitle("保存");
                delete.setVisible(false);
                modify.setTitle("取消编辑");
                isWrite = false;
            } else {
                save.setVisible(false);
                delete.setVisible(true);
                modify.setTitle("编辑");
                isWrite = true;
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_personnel_info, menu);
        return true;
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
