package com.erayic.agr.service.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.SectionedSpanSizeLookup;
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
    Toolbar toolbar;
    @BindView(R2.id.mine_service_RecyclerView)
    RecyclerView mineServiceRecyclerView;
    @BindView(R2.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R2.id.toolbar_title_name)
    TextView toolbarTitleName;
    @BindView(R2.id.toolbar_title_img)
    ImageView toolbarTitleImg;
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
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbarTitleName.setText(titleName);
        if (PreferenceUtils.getParam("UserRole", 0) == 1) {
            toolbarTitleImg.setVisibility(View.VISIBLE);
        } else {
            toolbarTitleImg.setVisibility(View.GONE);
        }

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

    @OnClick(R2.id.toolbar_title_img)
    public void onToolbarTitleImgClick() {
        ARouter.getInstance().build("/service/activity/ServiceListByEntActivity").navigation();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServiceEntranceEventThread(ServiceEntranceEvent event) {
        //接受从设置服务中传回来的数据
        ErayicLog.e(event.getServiceID());
        if (adapter.getList() == null)
            return;
        for (ServiceBuyByUserBean beanBuffer : adapter.getList()) {
            if (beanBuffer.getType() == EnumServiceType.Subject) {
                if (!beanBuffer.isOwner())
                    beanBuffer.setOwner(true);
                for (ServiceBuyByUserBean.SpecifysInfo specifysInfo : beanBuffer.getSpecifys()) {
                    if (specifysInfo.getServiceID().equals(event.getServiceID())) {
                        specifysInfo.setOwner(event.isOwner());
                    }
                }
            } else {
                if (beanBuffer.getServiceID().equals(event.getServiceID())) {
                    beanBuffer.setOwner(event.isOwner());
                }
            }
        }
        adapter.notifyDataSetChanged();
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
        public void onClick(View v, String serviceID) {
            switch (serviceID) {
                case "b759c79e-b365-4932-aab0-99ca72a35e04":
                    ARouter.getInstance().build("/serverproduct/activity/ReportingActivity").withString("serviceID", serviceID).navigation();
                    break;
                default:
                    break;
            }
        }
    }

}
