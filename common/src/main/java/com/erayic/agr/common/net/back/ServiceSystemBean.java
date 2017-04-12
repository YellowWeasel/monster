package com.erayic.agr.common.net.back;

import java.util.List;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：系统提供的所有服务Bean
 */

public class ServiceSystemBean {

    private List<ServicesInfo> Services;//服务信息
    private List<ImageHeadBean> Head;//头部信息

    public static class ServicesInfo {
        private String ServiceID;//服务ID
        private String ServiceName;//服务名称
        private String Descript;//描述
        private int Classify;//类别
        private int Type;//类型
        private PriceBean MasterPrice;//主价格
        private String Icon;//图片地址

        public String getServiceID() {
            return ServiceID;
        }

        public void setServiceID(String serviceID) {
            ServiceID = serviceID;
        }

        public String getServiceName() {
            return ServiceName;
        }

        public void setServiceName(String serviceName) {
            ServiceName = serviceName;
        }

        public String getDescript() {
            return Descript;
        }

        public void setDescript(String descript) {
            Descript = descript;
        }

        public int getClassify() {
            return Classify;
        }

        public void setClassify(int classify) {
            Classify = classify;
        }

        public int getType() {
            return Type;
        }

        public void setType(int type) {
            Type = type;
        }

        public PriceBean getMasterPrice() {
            return MasterPrice;
        }

        public void setMasterPrice(PriceBean masterPrice) {
            MasterPrice = masterPrice;
        }

        public String getIcon() {
            return Icon;
        }

        public void setIcon(String icon) {
            Icon = icon;
        }
    }

    public List<ServicesInfo> getServices() {
        return Services;
    }

    public void setServices(List<ServicesInfo> services) {
        Services = services;
    }

    public List<ImageHeadBean> getHead() {
        return Head;
    }

    public void setHead(List<ImageHeadBean> head) {
        Head = head;
    }


}
