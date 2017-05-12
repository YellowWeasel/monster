package com.erayic.agr.manage.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonBaseInfoBean;
import com.erayic.agr.common.net.back.CommonMapArrayBean;
import com.erayic.agr.common.net.back.CommonResUnitListBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicEditDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageBaseInfoAdapter;
import com.erayic.agr.manage.adapter.entity.ManageBaseInfoEntity;
import com.erayic.agr.manage.presenter.IBaseInfoPresenter;
import com.erayic.agr.manage.presenter.impl.BaseInfoPresenterImpl;
import com.erayic.agr.manage.view.IBaseInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/BaseInfoActivity", name = "基地详情")
public class BaseInfoActivity extends BaseActivity implements IBaseInfoView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.manage_base_RecyclerView)
    RecyclerView manageBaseRecyclerView;
    @BindView(R2.id.manage_base_swipe)
    SwipeRefreshLayout manageBaseSwipe;

    @Autowired
    String baseID;//基地ID
    @Autowired
    String baseName;//基地名称

    private CommonBaseInfoBean baseInfoBean;

    private ManageBaseInfoAdapter adapter;

    private LoadingDialog dialog;
    private IBaseInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_base_info);
    }

    @Override
    public void initView() {
        toolbar.setTitle(TextUtils.isEmpty(baseName) ? "未命名" : baseName);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        manageBaseSwipe.setOnRefreshListener(this);

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(BaseInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageBaseRecyclerView.setLayoutManager(manager);
        adapter = new ManageBaseInfoAdapter(BaseInfoActivity.this, null);
        adapter.setOnItemTypeClickListener(new OnItemTypeClickListener());
        manageBaseRecyclerView.setAdapter(adapter);
        manageBaseRecyclerView.addItemDecoration(new DividerItemDecoration(BaseInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new BaseInfoPresenterImpl(this);
        onRefresh();
    }


    @Override
    public void onRefresh() {
        presenter.getBaseInfo(baseID);
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageBaseSwipe != null && !manageBaseSwipe.isRefreshing())
                    manageBaseSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageBaseSwipe != null && manageBaseSwipe.isRefreshing())
                    manageBaseSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshBaseInfoView(final CommonBaseInfoBean bean) {
        baseInfoBean = bean;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(TextUtils.isEmpty(bean.getName()) ? "未定义" : bean.getName());
                List<ManageBaseInfoEntity> list = new ArrayList<>();
                //分割线
                ManageBaseInfoEntity entityDivider = new ManageBaseInfoEntity();
                entityDivider.setItemType(ManageBaseInfoEntity.TYPE_DIVIDER);
                list.add(entityDivider);
                //基地名称
                ManageBaseInfoEntity entityBaseName = new ManageBaseInfoEntity();
                entityBaseName.setItemType(ManageBaseInfoEntity.TYPE_BASE_NAME);
                entityBaseName.setName("基地名称");
                entityBaseName.setSubName(TextUtils.isEmpty(bean.getName()) ? "未命名" : bean.getName());
                list.add(entityBaseName);
                //基地图片
                ManageBaseInfoEntity entityBaseImage = new ManageBaseInfoEntity();
                entityBaseImage.setItemType(ManageBaseInfoEntity.TYPE_BASE_IMAGE);
                entityBaseImage.setName("基地图集");
                list.add(entityBaseImage);
                //基地位置
                ManageBaseInfoEntity entityBasePosition = new ManageBaseInfoEntity();
                entityBasePosition.setItemType(ManageBaseInfoEntity.TYPE_BASE_POSITION);
                entityBasePosition.setName("基地位置");
                if (bean.isRegion()) {
                    entityBasePosition.setSubName("已上传");
                } else {
                    entityBasePosition.setSubName("未上传");
                }
                list.add(entityBasePosition);
                //分割线
                list.add(entityDivider);
                //添加管理单元
                ManageBaseInfoEntity entityBaseAddUnit = new ManageBaseInfoEntity();
                entityBaseAddUnit.setItemType(ManageBaseInfoEntity.TYPE_BASE_ADD_UNIT);
                entityBaseAddUnit.setName("管理单元");
                entityBaseAddUnit.setSubName("添加");
                list.add(entityBaseAddUnit);
                //添加管理单元
                for (CommonResUnitListBean unitListBean : bean.getUnits()) {
                    ManageBaseInfoEntity entityUnit = new ManageBaseInfoEntity();
                    entityUnit.setItemType(ManageBaseInfoEntity.TYPE_BASE_UNIT_NAME);
                    entityUnit.setName(unitListBean.getName());
                    entityUnit.setUnitID(unitListBean.getUnitID());
                    for (CommonMapArrayBean mapArrayBean : unitListBean.getWorkers()) {
                        entityUnit.setSubName((TextUtils.isEmpty(entityUnit.getSubName()) ? "" : entityUnit.getSubName()) + " " + mapArrayBean.getValue());
                    }
                    list.add(entityUnit);
                }
                //分割线
                list.add(entityDivider);
                //添加基地描述
                ManageBaseInfoEntity entityBaseDepic = new ManageBaseInfoEntity();
                entityBaseDepic.setItemType(ManageBaseInfoEntity.TYPE_BASE_DEPICT);
                entityBaseDepic.setName("基地描述");
                entityBaseDepic.setSubName(bean.getDescript());
                list.add(entityBaseDepic);
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
                    dialog = new LoadingDialog(BaseInfoActivity.this);
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
                    dialog = new LoadingDialog(BaseInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateSure() {
        onRefresh();//测试用刷新，局部更新未实现
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

    private class OnItemTypeClickListener implements ManageBaseInfoAdapter.OnItemTypeClickListener {

        @Override
        public void onItemClick(View v, final int position, ManageBaseInfoEntity entity) {
            switch (entity.getItemType()) {
                case ManageBaseInfoEntity.TYPE_BASE_NAME://更改基地名称
                {
                    new ErayicEditDialog.Builder(BaseInfoActivity.this)
                            .setMessage(TextUtils.isEmpty(baseName) ? "" : baseName, null)
                            .setTitle("设置基地名称")
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
                                        presenter.updateBaseInfo(baseID, s.toString(), baseInfoBean.getDescript());
                                    } else {
                                        showToast("基地名称不能为空");
                                    }
                                    dialog.dismiss();
                                }
                            }).show();
                }
                break;
                case ManageBaseInfoEntity.TYPE_BASE_IMAGE://
                {
                    showToast("未实现");
                }
                break;
                case ManageBaseInfoEntity.TYPE_BASE_POSITION://
                {
                    showToast("未实现");

                }
                break;
                case ManageBaseInfoEntity.TYPE_BASE_ADD_UNIT://增加管理单元
                {
                    new ErayicEditDialog.Builder(BaseInfoActivity.this)
                            .setMessage("", null)
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
                                        presenter.addUnit(baseID, s.toString());
                                    } else {
                                        showToast("管理单元名称不能为空");
                                    }
                                    dialog.dismiss();
                                }
                            }).show();
                }
                break;
                case ManageBaseInfoEntity.TYPE_BASE_UNIT_NAME://管理单元详情
                {
                    ARouter.getInstance().build("/manage/activity/UnitInfoActivity")
                            .withString("baseID", baseID)
                            .withString("unitID", entity.getUnitID())
                            .withString("unitName", entity.getName()).navigation();
                }
                break;
                case ManageBaseInfoEntity.TYPE_BASE_DEPICT://基地描述
                {
                    showToast("未实现");
                }
                break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
