    package com.erayic.agr.service.presenter.impl;

    import com.alibaba.android.arouter.facade.annotation.Autowired;
    import com.alibaba.android.arouter.launcher.ARouter;
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
                    title.setServiceIcon(response.getService().getIcon());
                    title.setServiceName(response.getService().getServiceName());
                    entEntityTitle.setTitle(title);
                    list.add(entEntityTitle);

                    //设置descript
                    ServiceInfoByEntEntity entEntityDescript = new ServiceInfoByEntEntity();
                    entEntityDescript.setItemType(ServiceInfoByEntEntity.TYPE_DESCRIPT);
                    ServiceInfoByEntEntity.ServiceInfoByEntDescript descript = new ServiceInfoByEntEntity.ServiceInfoByEntDescript();
                    descript.setServiceDescript(response.getService().getDescript());

                    //测试数据，正式版需要删除
                    descript.setServiceDescript("安卓从第一代系统开始，一直被人诟病的就是系统卡顿以及应用质量参差不齐。而系" +
                            "统卡顿问题是安卓的天生缺陷，在安卓刚诞生的几年里，由于处理器性能不足，一直限制着安卓系统的表现。" +
                            "还好安卓强劲的市场表现寄予了处理器厂商信心，在前几年，手机处理器核心数几乎每年翻一番，到了2013" +
                            "年八核处理器发布之后这股硬件竞赛的热潮才渐渐平息。硬件的追求是无穷无尽的，但当然，安卓系统也在不" +
                            "断优化。安卓从第一代系统开始，一直被人诟病的就是系统卡顿以及应用质量参差不齐。而系统卡顿问题是安" +
                            "卓的天生缺陷，在安卓刚诞生的几年里，由于处理器性能不足，一直限制着安卓系统的表现。还好安卓强劲" +
                            "的市场表现寄予了处理器厂商信心，在前几年，手机处理器核心数几乎每年翻一番，到了2013年八核处理器" +
                            "发布之后这股硬件竞赛的热潮才渐渐平息。硬件的追求是无穷无尽的，但当然，安卓系统也在不断优化。");

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

                    //测试数据，正式版需要删除
                    List<CommonImageBean> imageBeanListText = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        CommonImageBean bean = new CommonImageBean();
                        bean.setPicturePath("http://file.market.xiaomi.com/thumbnail/jpeg/l395/AppStore/0330495f7fedd4f9a312045ce82c27e8c5c7f7e7f");
                        imageBeanListText.add(bean);
                    }
                    entEntityImages.setImageBeanList(imageBeanListText);

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
