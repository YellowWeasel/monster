package com.erayic.agr.service.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;
import com.erayic.agr.common.net.back.enums.EnumServiceType;
import com.erayic.agr.common.net.back.enums.EnumUserRole;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.SectionedSpanSizeLookup;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.ServiceEntranceAdapter;
import com.erayic.agr.service.event.ServiceEntranceEvent;
import com.erayic.agr.service.presenter.IServiceEntrancePresenter;
import com.erayic.agr.service.presenter.impl.ServiceEntrancePresenterImpl;
import com.erayic.agr.service.view.IServiceEntranceView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：服务入口
 */
@Route(path = "/service/fragment/ServiceEntranceFragment", name = "我的服务")
public class ServiceEntranceFragment extends BaseFragment implements IServiceEntranceView {

    //    /* 标题栏 */
    @Autowired
    String titleName;

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.mine_service_RecyclerView)
    RecyclerView mineServiceRecyclerView;
    @BindView(R2.id.fake_status_bar)
    View fakeStatusBar;
    //    @BindView(R2.id.toolbar_title_img)
//    ImageView toolbarTitleImg;
    @BindView(R2.id.service_entrance_switch)
    TextView serviceEntranceSwitch;
    @BindView(R2.id.service_entrance_manager)
    TableRow serviceEntranceManager;

    private IServiceEntrancePresenter presenter;
    private ServiceEntranceAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service_entrance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);// 注册EventBus
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(titleName);
        if (PreferenceUtils.getParam("UserRole", -1) == EnumUserRole.Role_Manager || PreferenceUtils.getParam("UserRole", -1) == EnumUserRole.Role_Admin) {
            toolbar.inflateMenu(R.menu.service_entrance_admin); //加载菜单
        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //点击事件
                if (item.getItemId() == R.id.action_service_entrance) {
                    ARouter.getInstance().build("/service/activity/ServiceListByEntActivity").navigation();
                }
                return true;
            }
        });


        adapter = new ServiceEntranceAdapter(getActivity());
        adapter.setOnItemClickListener(new OnItemClickListener());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(adapter, manager));
        mineServiceRecyclerView.setLayoutManager(manager);
        mineServiceRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter = new ServiceEntrancePresenterImpl(this);
        presenter.getAllSystemServiceByUser();
    }


    @OnClick(R2.id.service_entrance_switch)
    public void onServiceEntranceSwitchClicked() {
        //布局切换
        showToast("暂未实现");
    }

    @OnClick(R2.id.service_entrance_manager)
    public void onServiceEntranceManagerClicked() {
        //应用管理
        ARouter.getInstance().build("/service/activity/ServiceManageActivity").navigation();
    }
//
//    @OnClick(R2.id.toolbar_title_img)
//    public void onToolbarTitleImgClick() {
//
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServiceEntranceEventThread(ServiceEntranceEvent event) {
        //接受从设置服务中传回来的数据
        ErayicLog.e(event.getServiceID());
        presenter.getAllSystemServiceByUser();
//        if (adapter.getList() == null)
//            return;
//        for (ServiceBuyByUserBean beanBuffer : adapter.getList()) {
//            if (beanBuffer.getType() == EnumServiceType.Subject) {
//                if (!beanBuffer.isOwner())
//                    beanBuffer.setOwner(true);
//                for (ServiceBuyByUserBean.SpecifysInfo specifysInfo : beanBuffer.getSpecifys()) {
//                    if (specifysInfo.getServiceID().equals(event.getServiceID())) {
//                        specifysInfo.setOwner(event.isOwner());
//                    }
//                }
//            } else {
//                if (beanBuffer.getServiceID().equals(event.getServiceID())) {
//                    beanBuffer.setOwner(event.isOwner());
//                }
//            }
//        }
//        adapter.notifyDataSetChanged();
    }


    @Override
    public void refreshView(final List<ServiceBuyByUserBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setList(list);

                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getActivity(), msg);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解绑EventBus
    }

    private class OnItemClickListener implements ServiceEntranceAdapter.OnItemClickListener {

        @Override
        public void onClick(View v, String serviceID, String subServiceID, int cropID, String cropName) {
            switch (serviceID) {
                case "b759c79e-b365-4932-aab0-99ca72a35e04":
                    ARouter.getInstance().build("/serverproduct/activity/ReportingActivity").withString("serviceID", serviceID).navigation();
                    break;
                case "2e205e96-89c3-4370-ab65-d117fdb4c12e":
                    ARouter.getInstance().build("/serverproduct/activity/WeatherTenDayReportingActivity").withString("serviceID", serviceID).navigation();
                    break;
                case "3d8508bf-9b94-4b2c-86cb-d4e62663d25f"://价格动态服务
                    if (TextUtils.isEmpty(subServiceID))
                        showToast("未支持的服务类型");
                    else
                        ARouter.getInstance().build("/serverproduct/activity/DynamicPriceActivity")
                                .withString("serviceID", subServiceID)
                                .withInt("cropId", cropID)
                                .withString("cropName", cropName)
                                .navigation();
                    break;
                case "3fabad22-5e5f-4d76-9ddf-d3af850019de"://政策法规
                    ARouter.getInstance().build("/serverproduct/activity/PoliciesRegulationsActivity").withString("serviceID", serviceID).navigation();
                    break;
                case "c74cb478-10b0-4973-8756-c26e89570b8b"://农业资讯
                    ARouter.getInstance().build("/serverproduct/activity/AgriculturalInfoActivity").withString("serviceID", serviceID).navigation();
                    break;
                default:
                    showToast("未支持的服务类型");
                    break;
            }
        }

        @Override
        public void toServiceInfo(String serviceName, String serviceID, String serviceIcon, int serviceType) {//服务详情
            if (PreferenceUtils.getParam("UserRole", -1) == EnumUserRole.Role_Manager || PreferenceUtils.getParam("UserRole", -1) == EnumUserRole.Role_Admin) {//管理员权限
                ARouter.getInstance().build("/service/activity/ServiceInfoByEntActivity").withString("serviceName", serviceName)
                        .withString("serviceID", serviceID).withString("serviceIcon", serviceIcon)
                        .withInt("serviceType", serviceType).navigation();
            } else {
                showToast("无权限购买，请联系管理员");
            }
        }

    }

}
