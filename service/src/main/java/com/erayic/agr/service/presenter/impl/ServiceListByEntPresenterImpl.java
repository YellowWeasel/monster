package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.service.adapter.entity.ServiceListByEntEntity;
import com.erayic.agr.service.presenter.IServiceListByEntPresenter;
import com.erayic.agr.service.view.IServiceListByEntView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceListByEntPresenterImpl implements IServiceListByEntPresenter {

    private IServiceListByEntView marketView;
    @Autowired
    IServerModel serverModel;

    public ServiceListByEntPresenterImpl(IServiceListByEntView marketView) {
        this.marketView = marketView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getAllSystemServiceByEnt() {
        marketView.openRefresh();
        serverModel.getAllSystemServiceByEnt(new OnDataListener<ServiceSystemBean>() {
            @Override
            public void success(ServiceSystemBean response) {
                marketView.clearRefresh();
                List<ServiceListByEntEntity> list = new ArrayList<>();
                for (int i = 0; i < response.getServices().size() + 1; i++) {
                    ServiceListByEntEntity entity = new ServiceListByEntEntity();
                    if (i == 0) {
                        entity.setItemType(ServiceListByEntEntity.TYPE_BANNER);
                        entity.setHeadBeanList(response.getHead());
                        list.add(entity);
                    } else {
                        entity.setItemType(ServiceListByEntEntity.TYPE_ITEM);
                        entity.setServicesInfo(response.getServices().get(i - 1));
                        list.add(entity);
                    }
                }
                //测试数据
                if (list.size()>2){
                    list.add(list.get(1));
                    list.add(list.get(1));
                    list.add(list.get(1));
                    list.add(list.get(1));
                    list.add(list.get(1));
                    list.add(list.get(1));
                    list.add(list.get(1));
                    list.add(list.get(1));
                }
                marketView.refreshServiceView(list);
            }

            @Override
            public void fail(int errCode, String msg) {
                marketView.clearRefresh();
                marketView.refreshServiceView(null);
            }
        });
    }
}
