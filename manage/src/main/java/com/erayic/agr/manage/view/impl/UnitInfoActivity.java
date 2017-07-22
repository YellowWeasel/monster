package com.erayic.agr.manage.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.ManageRefreshMessage;
import com.erayic.agr.common.net.back.CommonMapArrayBean;
import com.erayic.agr.common.net.back.CommonUnitInfoBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicEditDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageBaseInfoAdapter;
import com.erayic.agr.manage.adapter.ManageUnitInfoAdapter;
import com.erayic.agr.manage.adapter.entity.ManageUnitInfoEntity;
import com.erayic.agr.manage.adapter.holder.ManageContentGroupViewHolder;
import com.erayic.agr.manage.event.UserCheckEvent;
import com.erayic.agr.manage.presenter.IUnitInfoPresenter;
import com.erayic.agr.manage.presenter.impl.UnitInfoPresenterImpl;
import com.erayic.agr.manage.view.IUnitInfoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/UnitInfoActivity", name = "管理单元详情")
public class UnitInfoActivity extends BaseActivity implements IUnitInfoView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_unit_RecyclerView)
    RecyclerView manageUnitRecyclerView;
    @BindView(R2.id.manage_unit_swipe)
    SwipeRefreshLayout manageUnitSwipe;

    @Autowired
    String baseID;
    @Autowired
    String unitID;
    @Autowired
    String unitName;

    private CommonUnitInfoBean unitInfoBean;

    private LoadingDialog dialog;

    private IUnitInfoPresenter presenter;
    private ManageUnitInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_unit_info);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);// 注册EventBus
        toolbar.setTitle(TextUtils.isEmpty(unitName) ? "未命名" : unitName);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        manageUnitSwipe.setOnRefreshListener(this);

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(UnitInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageUnitRecyclerView.setLayoutManager(manager);
        adapter = new ManageUnitInfoAdapter(UnitInfoActivity.this, null);
        adapter.setOnItemTypeClickListener(new OnItemTypeClickListener());
        manageUnitRecyclerView.setAdapter(adapter);
        manageUnitRecyclerView.addItemDecoration(new DividerItemDecoration(UnitInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new UnitInfoPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getUnitDetailInfo(unitID);
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageUnitSwipe != null && !manageUnitSwipe.isRefreshing())
                    manageUnitSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageUnitSwipe != null && manageUnitSwipe.isRefreshing())
                    manageUnitSwipe.setRefreshing(false);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserCheckBack(UserCheckEvent event) {
        //获取选择负责人返回的数据
        List<CommonMapArrayBean> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : event.getUserMap().entrySet()) {
            CommonMapArrayBean bean = new CommonMapArrayBean();
            bean.setKey(entry.getKey());
            bean.setValue(entry.getValue());
            list.add(bean);
        }
        unitInfoBean.setWorkers(list);
        refreshUnitInfoView(unitInfoBean);
    }

    @Override
    public void refreshUnitInfoView(final CommonUnitInfoBean bean) {
        unitInfoBean = bean;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageUnitInfoEntity> list = new ArrayList<>();
                //分割线
                ManageUnitInfoEntity entityDivider = new ManageUnitInfoEntity();
                entityDivider.setItemType(ManageUnitInfoEntity.TYPE_DIVIDER);
                list.add(entityDivider);
                //管理单元名称
                ManageUnitInfoEntity entityName = new ManageUnitInfoEntity();
                entityName.setItemType(ManageUnitInfoEntity.TYPE_UNIT_NAME);
                entityName.setName("名称");
                entityName.setSubName(TextUtils.isEmpty(bean.getName()) ? "" : bean.getName());
                list.add(entityName);
                //管理单元面积
                ManageUnitInfoEntity entityArea = new ManageUnitInfoEntity();
                entityArea.setItemType(ManageUnitInfoEntity.TYPE_UNIT_AREA);
                entityArea.setName("面积");
                entityArea.setSubName(bean.getArea() + "");
                list.add(entityArea);
                //管理单元地理位置
                ManageUnitInfoEntity entityRegins = new ManageUnitInfoEntity();
                entityRegins.setItemType(ManageUnitInfoEntity.TYPE_UNIT_REGIN);
                entityRegins.setName("地理位置");
                list.add(entityRegins);
                //管理单元负责人
                ManageUnitInfoEntity entityWorkes = new ManageUnitInfoEntity();
                entityWorkes.setItemType(ManageUnitInfoEntity.TYPE_UNIT_WORK);
                entityWorkes.setName("负责人");
                for (CommonMapArrayBean mapArrayBean : bean.getWorkers()) {
                    entityWorkes.setSubName((TextUtils.isEmpty(entityWorkes.getSubName()) ? "" : entityWorkes.getSubName()) + " " + mapArrayBean.getValue());
                }
                list.add(entityWorkes);
                //分割线
                list.add(entityDivider);
                //是否为大棚
                ManageUnitInfoEntity entityGreen = new ManageUnitInfoEntity();
                entityGreen.setItemType(ManageUnitInfoEntity.TYPE_UNIT_GREEN);
                entityGreen.setName("类型");
                entityGreen.setGreenhouse(bean.isGreenhouse());
                list.add(entityGreen);

                adapter.setNewData(list);
            }
        });
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(UnitInfoActivity.this);
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
                    dialog = new LoadingDialog(UnitInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateSure() {
        ManageRefreshMessage message = new ManageRefreshMessage();
        message.setMsgType(ManageRefreshMessage.MANAGE_MASTER_BASE_INFO);
        EventBus.getDefault().post(message);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_unit_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_unit_update) {
            presenter.updateBlockInfo(baseID, unitInfoBean);
        }
        return super.onOptionsItemSelected(item);
    }

    private class OnItemTypeClickListener implements ManageUnitInfoAdapter.OnItemTypeClickListener {

        @Override
        public void onItemClick(View v, final int position, ManageUnitInfoEntity entity, boolean isGreen) {
            switch (entity.getItemType()) {
                case ManageUnitInfoEntity.TYPE_UNIT_NAME://修改名称
                {
                    new ErayicEditDialog.Builder(UnitInfoActivity.this)
                            .setMessage(TextUtils.isEmpty(entity.getSubName()) ? "" : entity.getSubName(), null)
                            .setTitle("设置管理单元名称")
                            .setButton1("取消", new ErayicEditDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which, CharSequence s) {
                                    dialog.dismiss();
                                }
                            })
                            .setButton2("确定", new ErayicEditDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which, CharSequence s) {
                                    if (!TextUtils.isEmpty(s)) {
                                        unitInfoBean.setName(s.toString());
                                        adapter.getData().get(position).setSubName(s.toString());
                                        adapter.notifyItemChanged(position);
                                    } else {
                                        showToast("名称不能为空");
                                    }
                                    dialog.dismiss();
                                }
                            }).show();
                }
                break;
                case ManageUnitInfoEntity.TYPE_UNIT_AREA://修改面积
                    new ErayicEditDialog.Builder(UnitInfoActivity.this)
                            .setMessage(TextUtils.isEmpty(entity.getSubName()) ? "" : entity.getSubName(), null)
                            .setTitle("设置管理单元面积（亩）")
                            .setButton1("取消", new ErayicEditDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which, CharSequence s) {
                                    dialog.dismiss();
                                }
                            })
                            .setButton2("确定", new ErayicEditDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which, CharSequence s) {
                                    if (!TextUtils.isEmpty(s)) {
                                        double doubleBuffer;
                                        try {
                                            doubleBuffer = Double.valueOf(s.toString()).doubleValue();
                                        } catch (Exception e) {
                                            showToast("类型错误，请输入字符");
                                            return;
                                        }
                                        unitInfoBean.setArea(doubleBuffer);
                                        adapter.getData().get(position).setSubName(s.toString());
                                        adapter.notifyItemChanged(position);
                                    } else {
                                        showToast("名称不能为空");
                                    }
                                    dialog.dismiss();
                                }
                            }).show();
                    break;
                case ManageUnitInfoEntity.TYPE_UNIT_REGIN:
                    showToast("未实现");
                    break;
                case ManageUnitInfoEntity.TYPE_UNIT_WORK://选择负责人
                    ARouter.getInstance().build("/manage/activity/SelectUserActivity").withString("baseID", baseID).navigation();
                    break;
                case ManageUnitInfoEntity.TYPE_UNIT_GREEN://类型
                {
                    unitInfoBean.setGreenhouse(isGreen);
                    adapter.getData().get(position).setGreenhouse(isGreen);
                    adapter.notifyItemChanged(position);
                }
                break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解绑EventBus
    }
}
