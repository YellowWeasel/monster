package com.erayic.agr.common.net.back;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceInfoByEntBean {

    private CommonServiceBean Service;
    private List<CommonImageBean> Images;

    public CommonServiceBean getService() {
        return Service;
    }

    public void setService(CommonServiceBean service) {
        Service = service;
    }

    public List<CommonImageBean> getImages() {
        return Images;
    }

    public void setImages(List<CommonImageBean> images) {
        Images = images;
    }
}
