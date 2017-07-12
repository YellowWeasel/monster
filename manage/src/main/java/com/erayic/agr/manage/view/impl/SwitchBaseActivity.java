package com.erayic.agr.manage.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicTextDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageBaseListAdapter;
import com.erayic.agr.manage.presenter.ISwitchBasePresenter;
import com.erayic.agr.manage.presenter.impl.SwitchBasePresenterImpl;
import com.erayic.agr.manage.view.ISwitchBaseView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/manage/activity/SwitchBaseActivity", name = "切换基地")
public class SwitchBaseActivity extends BaseActivity implements ISwitchBaseView {


    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_base_RecyclerView)
    RecyclerView manageBaseRecyclerView;

    private LoadingDialog dialog;

    private ISwitchBasePresenter presenter;
    private ManageBaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_switch_base);
    }

    @Override
    public void initView() {
        toolbar.setTitle("切换基地");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(SwitchBaseActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageBaseRecyclerView.setLayoutManager(manager);
        adapter = new ManageBaseListAdapter(SwitchBaseActivity.this, null);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final CommonBaseListBean baseListBean = (CommonBaseListBean) adapter.getData().get(position);
                if (baseListBean == null) {
                    showToast("未知错误");
                    ErayicStack.getInstance().finishCurrentActivity();
                }
                if (TextUtils.equals(baseListBean.getBaseID(), PreferenceUtils.getParam("ActiveBaseID"))) {
                    showToast("当前基地无需切换");
                } else {
                    new ErayicTextDialog.Builder(SwitchBaseActivity.this)
                            .setMessage("该操作将失去对" + PreferenceUtils.getParam("BaseName") + "的使用\n同时对" + baseListBean.getBaseName() +
                                    "进行使用权\n您确定要切换到" + baseListBean.getBaseName() + "吗？", null)
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
                                    if (PreferenceUtils.clearData()) {
                                        presenter.changeBase(baseListBean.getBaseID());
                                        dialog.dismiss();
                                    }
                                }
                            }).show();
                }
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                return true;
            }
        });
        manageBaseRecyclerView.setAdapter(adapter);
        manageBaseRecyclerView.addItemDecoration(new DividerItemDecoration(SwitchBaseActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new SwitchBasePresenterImpl(this);
        presenter.getBaseListByUser();
    }

    @Override
    public void refreshViewData(final List<CommonBaseListBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(list);
            }
        });
    }

    @Override
    public void switchBaseSure() {
        ErayicStack.getInstance().finishAllActivity();//清空所有的activity
        ARouter.getInstance().build("/main/Activity/MainActivity").navigation();
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(SwitchBaseActivity.this);
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
                    dialog = new LoadingDialog(SwitchBaseActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        }
        return super.onOptionsItemSelected(item);
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
