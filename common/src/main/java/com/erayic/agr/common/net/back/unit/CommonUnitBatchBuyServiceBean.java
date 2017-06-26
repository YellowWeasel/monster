package com.erayic.agr.common.net.back.unit;

import com.erayic.agr.common.net.back.CommonPriceBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonUnitBatchBuyServiceBean {

    private String ServiceID;//服务ID
    private String ServiceName;//服务名称
    private int Type;//服务类型
    private String Descript;//服务描述
    private CommonPriceBean MasterPrice;//价格信息
    private String Icon;//服务图标


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

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getDescript() {
        return Descript;
    }

    public void setDescript(String descript) {
        Descript = descript;
    }

    public CommonPriceBean getMasterPrice() {
        return MasterPrice;
    }

    public void setMasterPrice(CommonPriceBean masterPrice) {
        MasterPrice = masterPrice;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }
}
