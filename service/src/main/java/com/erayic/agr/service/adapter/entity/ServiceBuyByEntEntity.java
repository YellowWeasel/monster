package com.erayic.agr.service.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.net.back.CommonSubServiceBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceBuyByEntEntity implements MultiItemEntity {
    public static final int TYPE_CONTENT = 0;
    public static final int TYPE_PAY = 1;

    private int itemType;
    private BuyContent buyContent;//服务内容
    private int buyType = -1;//支持方式
    private TotalPrice totalPrice;//合计支付

    public static class BuyContent {
        private String serviceName;//服务名称
        private String serviceID;//服务ID
        private int serviceType;//服务类型
        private CommonPriceBean priceBean;//当前选中的价格
        private List<CommonSubServiceBean> topiceServices;//主题服务

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getServiceID() {
            return serviceID;
        }

        public void setServiceID(String serviceID) {
            this.serviceID = serviceID;
        }

        public int getServiceType() {
            return serviceType;
        }

        public void setServiceType(int serviceType) {
            this.serviceType = serviceType;
        }

        public CommonPriceBean getPriceBean() {
            return priceBean;
        }

        public void setPriceBean(CommonPriceBean priceBean) {
            this.priceBean = priceBean;
        }

        public List<CommonSubServiceBean> getTopiceServices() {
            return topiceServices;
        }

        public void setTopiceServices(List<CommonSubServiceBean> topiceServices) {
            this.topiceServices = topiceServices;
        }
    }

    public static class BuyPay {
        private int payType;//0 微信支付 1 支付宝支付 2 银行转账
        private String payName;

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getPayName() {
            return payName;
        }

        public void setPayName(String payName) {
            this.payName = payName;
        }
    }

    public static class TotalPrice {
        private double totalprice;//合计总价

        public double getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(double totalprice) {
            this.totalprice = totalprice;
        }
    }


    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public BuyContent getBuyContent() {
        return buyContent;
    }

    public void setBuyContent(BuyContent buyContent) {
        this.buyContent = buyContent;
    }


    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(TotalPrice totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getBuyType() {
        return buyType;
    }

    public void setBuyType(int buyType) {
        this.buyType = buyType;
    }
}
