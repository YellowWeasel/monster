package com.erayic.agr.manage.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonEntInfoBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicEditDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageEntItemAdapter;
import com.erayic.agr.manage.adapter.entity.ManageEntEntity;
import com.erayic.agr.manage.presenter.IEnterprisePresenter;
import com.erayic.agr.manage.presenter.impl.EnterprisePresenterImpl;
import com.erayic.agr.manage.view.IEnterpriseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/EnterpriseActivity", name = "企业信息")
public class EnterpriseActivity extends BaseActivity implements IEnterpriseView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.manage_enterprise_RecyclerView)
    RecyclerView manageEnterpriseRecyclerView;
    @BindView(R2.id.manage_enterprise_swipe)
    SwipeRefreshLayout manageEnterpriseSwipe;

    private IEnterprisePresenter presenter;
    private ManageEntItemAdapter adapter;
    private LoadingDialog dialog;

    private int positionBuffer = -1;
    private ManageEntEntity entityBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_enterprise);
    }

    @Override
    public void initView() {
        toolbar.setTitle("企业信息");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        manageEnterpriseSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(EnterpriseActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageEnterpriseRecyclerView.setLayoutManager(manager);
        adapter = new ManageEntItemAdapter(EnterpriseActivity.this, null);
        adapter.setOnItemTypeClickListener(new AdapterClickListener());
        manageEnterpriseRecyclerView.setAdapter(adapter);
        manageEnterpriseRecyclerView.addItemDecoration(new DividerItemDecoration(EnterpriseActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new EnterprisePresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getEntInfo();
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageEnterpriseSwipe != null && !manageEnterpriseSwipe.isRefreshing())
                    manageEnterpriseSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageEnterpriseSwipe != null && manageEnterpriseSwipe.isRefreshing())
                    manageEnterpriseSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(EnterpriseActivity.this);
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
                    dialog = new LoadingDialog(EnterpriseActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateSure(final String strBuffer) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                entityBuffer.setSubTitle(strBuffer);
                adapter.setData(positionBuffer, entityBuffer);
            }
        });
    }

    @Override
    public void refreshPersonnelView(final CommonEntInfoBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageEntEntity> list = new ArrayList<>();
                //分割线
                ManageEntEntity entityDivider = new ManageEntEntity();
                entityDivider.setItemType(ManageEntEntity.TYPE_DIVIDER);
                list.add(entityDivider);
                //企业名称
                ManageEntEntity entityEntName = new ManageEntEntity();
                entityEntName.setItemType(ManageEntEntity.TYPE_ENT_NEMA);
                entityEntName.setTitle("企业名称");
                entityEntName.setSubTitle(bean.getName());
                list.add(entityEntName);
                //营业执照
                ManageEntEntity entityBL = new ManageEntEntity();
                entityBL.setItemType(ManageEntEntity.TYPE_ENT_BL);
                entityBL.setTitle("营业执照");
                entityBL.setSubBool(bean.isLicense());
                entityBL.setSubTitle(bean.getLicense());
                list.add(entityBL);
                //组织机构代码证
                ManageEntEntity entityOCC = new ManageEntEntity();
                entityOCC.setItemType(ManageEntEntity.TYPE_ENT_OCC);
                entityOCC.setTitle("组织机构代码证");
                entityOCC.setSubBool(bean.isCredentials());
                entityOCC.setSubTitle(bean.getCredentials());
                list.add(entityOCC);
                //分割线
                list.add(entityDivider);
                //基础服务
                ManageEntEntity entityService = new ManageEntEntity();
                entityService.setItemType(ManageEntEntity.TYPE_ENT_SERVICE);
                entityService.setTitle("基础核心服务");
                entityService.setSubTitle(bean.getKenrel() + "");
                list.add(entityService);
                //分割线
                list.add(entityDivider);
                //联系人
                ManageEntEntity entityContact = new ManageEntEntity();
                entityContact.setItemType(ManageEntEntity.TYPE_ENT_CONTACTER);
                entityContact.setTitle("企业联系人");
                entityContact.setSubTitle(bean.getContacter());
                list.add(entityContact);
                //分割线
                list.add(entityDivider);
                //企业照片
                ManageEntEntity entityImage = new ManageEntEntity();
                entityImage.setItemType(ManageEntEntity.TYPE_ENT_IMAGE);
                entityImage.setTitle("企业照片");
                entityImage.setSubTitle("");
                list.add(entityImage);
                //其他资质
                ManageEntEntity entityOther = new ManageEntEntity();
                entityOther.setItemType(ManageEntEntity.TYPE_ENT_OTHER);
                entityOther.setTitle("其他资质");
                entityOther.setSubTitle("");
                list.add(entityOther);
                //企业描述
                ManageEntEntity entityDepict = new ManageEntEntity();
                entityDepict.setItemType(ManageEntEntity.TYPE_ENT_DEPICT);
                entityDepict.setTitle("企业描述");
                entityDepict.setSubTitle("海南晓晨科技有限公司于是一家拥有很强的创新能力研发型科技企业，海南省软件认定企业。于2006年7月在海南省工商局登记成立，公司一直坚持自主创新设计的理念，不仅仅把\"技术领先\"作为我们的经营宗旨，更把\"服务至上\"作为我们的目标，我们深知软件即服务(Software-as-a-Service）的理念，通过先进的市场营销理念；灵活多样的营销方案；务实准确的目标市场；可靠优质的技术产品；配套的系统解决方案；完善的服务网络体系，以赢得用户的肯定。");
                list.add(entityDepict);
                adapter.setNewData(list);
            }
        });
    }

    private class AdapterClickListener implements ManageEntItemAdapter.OnItemTypeClickListener {

        @Override
        public void onItemClick(View v, int position, ManageEntEntity entity) {
            positionBuffer = position;
            entityBuffer = entity;
            switch (entity.getItemType()) {
                case ManageEntEntity.TYPE_ENT_NEMA://修改企业名称
                {
                    new ErayicEditDialog.Builder(EnterpriseActivity.this)
                            .setMessage(entity.getSubTitle(), null)
                            .setTitle("设置名字")
                            .setButton1("取消", new ErayicEditDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which, CharSequence s) {
                                    dialog.dismiss();
                                }
                            })
                            .setButton2("确定", new ErayicEditDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which, CharSequence s) {
                                    presenter.updateEntName(s.toString());
                                    dialog.dismiss();
                                }
                            }).show();
                }
                break;
                case ManageEntEntity.TYPE_ENT_SERVICE://服务订购
                {
                    ARouter.getInstance().build("/service/activity/ServiceListByEntActivity").navigation();
                }
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_enterprise_delete) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_enterprise, menu);
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
