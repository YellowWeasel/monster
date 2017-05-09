package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.back.CommonFertilizerBean;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.net.back.CommonSeedBean;
import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpResourceService;

import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpResourceManager {

    private static HttpResourceManager manager;

    private static IHttpResourceService resourceService;

    private HttpResourceManager() {
    }

    public static HttpResourceManager getInstance() {
        if (manager == null) {
            synchronized (IHttpResourceService.class) {
                if (manager == null) {
                    manager = new HttpResourceManager();
                    resourceService = HttpRetrofit.getRequestCookiesRetrofit().create(IHttpResourceService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 创建一个企业产品
     */
    public Observable createProduct(String productName) {
        return resourceService.createProduct(productName);
    }

    /**
     * 得到一个企业的所有产品
     */
    public Observable getAllProduct() {
        return resourceService.getAllProduct();
    }

    /**
     * 得到指定行业产品分类
     */
    public Observable getAllProductClassic(int type) {
        return resourceService.getAllProductClassic(type);
    }

    /**
     * 得到产品详情
     */
    public Observable getProductDetail(String proID) {
        return resourceService.getProductDetail(proID);
    }

    /**
     * 更新产品信息
     */
    public Observable updateProduct(String proID, String productName, int classicID, String descript) {
        return resourceService.updateProduct(proID, productName, classicID, descript);
    }

    /**
     * 删除一个产品
     */
    public Observable deleteProduct(String proID) {
        return resourceService.deleteProduct(proID);
    }


    /**
     * 通过肥料登记号进行真伪检验
     */
    public Observable fertilizerCheck(String pID) {
        return resourceService.fertilizerCheck(pID);
    }

    /**
     * 通过农药登记号进行真伪检验
     */
    public Observable pestilizerCheck(String pID) {
        return resourceService.pestilizerCheck(pID);
    }

    /**
     * 增加一个肥料
     */
    public Observable addFertilizer(CommonFertilizerBean bean) {
        return resourceService.addFertilizer(bean);
    }

    /**
     * 增加一个农药
     */
    public Observable addPesticide(CommonPesticideBean bean) {
        return resourceService.addPesticide(bean);
    }

    /**
     * 增加一个种子
     */
    public Observable addSeed(CommonSeedBean bean) {
        return resourceService.addSeed(bean);
    }

    /**
     * 得到指定类型的生产资料集合
     */
    public Observable getResourceByType(int type) {
        return resourceService.getResourceByType(type);
    }

    /**
     * 得到指定类型的生产资料(农药)
     */
    public Observable getSpecifyResourcesByPesticide(String resID, int type) {
        return resourceService.getSpecifyResourcesByPesticide(resID, type);
    }

    /**
     * 得到指定类型的生产资料(肥料)
     */
    public Observable getSpecifyResourcesByFertilizer(String resID, int type) {
        return resourceService.getSpecifyResourcesByFertilizer(resID, type);
    }

    /**
     * 删除一个生产资料
     */
    public Observable deleteResource(String resID, int type) {
        return resourceService.deleteResource(resID, type);
    }

    /**
     * 更新一个肥料
     */
    public Observable updateFertilizer(CommonFertilizerBean bean){
        return resourceService.updateFertilizer(bean);
    }

}
