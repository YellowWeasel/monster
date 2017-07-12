package com.erayic.agr.service.presenter.impl;

import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonImageBean;
import com.erayic.agr.common.net.back.ServiceInfoByEntBean;
import com.erayic.agr.service.adapter.entity.ServiceInfoByEntEntity;
import com.erayic.agr.service.presenter.IServiceInfoByEntPresenter;
import com.erayic.agr.service.view.IServiceInfoByEntView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceInfoByEntPresenterImpl implements IServiceInfoByEntPresenter {
    private IServiceInfoByEntView infoByEntView;

    @Autowired
    IServerModel serverModel;

    public ServiceInfoByEntPresenterImpl(IServiceInfoByEntView infoByEntView) {
        this.infoByEntView = infoByEntView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getSpecifyServiceByEnt(String serviceID) {
        infoByEntView.openRefresh();
        serverModel.getSpecifyServiceByEnt(serviceID, new OnDataListener<ServiceInfoByEntBean>() {
            @Override
            public void success(ServiceInfoByEntBean response) {
                List<ServiceInfoByEntEntity> list = new ArrayList<>();
                //设置title
                ServiceInfoByEntEntity entEntityTitle = new ServiceInfoByEntEntity();
                entEntityTitle.setItemType(ServiceInfoByEntEntity.TYPE_TITLE);
                ServiceInfoByEntEntity.ServiceInfoByEntTitle title = new ServiceInfoByEntEntity.ServiceInfoByEntTitle();
                title.setServiceIcon(TextUtils.isEmpty(response.getService().getIcon()) ? "" : (AgrConstant.IMAGE_URL_PREFIX + response.getService().getIcon()));
                title.setServiceName(response.getService().getServiceName());
                entEntityTitle.setTitle(title);
                list.add(entEntityTitle);

                //设置descript
                ServiceInfoByEntEntity entEntityDescript = new ServiceInfoByEntEntity();
                entEntityDescript.setItemType(ServiceInfoByEntEntity.TYPE_DESCRIPT);
                ServiceInfoByEntEntity.ServiceInfoByEntDescript descript = new ServiceInfoByEntEntity.ServiceInfoByEntDescript();
                descript.setServiceDescript(response.getService().getDescript());

                entEntityDescript.setDescript(descript);
                list.add(entEntityDescript);

                //设置Price
                ServiceInfoByEntEntity entEntityPrice = new ServiceInfoByEntEntity();
                entEntityPrice.setItemType(ServiceInfoByEntEntity.TYPE_PRICE);
                ServiceInfoByEntEntity.ServiceInfoByEntPrice price = new ServiceInfoByEntEntity.ServiceInfoByEntPrice();
                price.setServicePrice(response.getService().getMasterPrice());
                price.setBuy(response.getService().isBuy());
                price.setExpire(response.getService().isExpire());
                price.setTrys(response.getService().isTry());
                price.setUserTry(response.getService().isUserIsTry());
                price.setDueDate(response.getService().getDueDate());
                entEntityPrice.setPrice(price);
                list.add(entEntityPrice);

                //设置IMAGES
                ServiceInfoByEntEntity entEntityImages = new ServiceInfoByEntEntity();
                entEntityImages.setItemType(ServiceInfoByEntEntity.TYPE_IMAGES);
                entEntityImages.setImageBeanList(response.getImages());

                List<CommonImageBean> imageList = new ArrayList<>();
                for (int i = 0; i < response.getImages().size(); i++) {
                    CommonImageBean bean = new CommonImageBean();
                    bean.setPicturePath(TextUtils.isEmpty(response.getImages().get(i).getPicturePath()) ? "" : (AgrConstant.IMAGE_URL_PREFIX + response.getImages().get(i).getPicturePath()));
                    imageList.add(bean);
                }
                entEntityImages.setImageBeanList(imageList);

                list.add(entEntityImages);
                infoByEntView.clearRefresh();
                infoByEntView.refreshServiceView(list);
            }

            @Override
            public void fail(int errCode, String msg) {
                infoByEntView.clearRefresh();
                infoByEntView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
