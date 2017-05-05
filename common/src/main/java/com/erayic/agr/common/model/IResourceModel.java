package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonFertilizerBean;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.common.net.back.CommonResourceBean;
import com.erayic.agr.common.net.back.CommonSeedBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IResourceModel extends IProvider {

    /**
     * 创建一个企业产品
     */
    void createProduct(String productName, OnDataListener<Object> listener);

    /**
     * 得到一个企业的所有产品
     */
    void getAllProduct(OnDataListener<List<CommonProduceListBean>> listener);

    /**
     * 得到企业指定行业的所有产品
     */
    void getAllProductClassic(int type, OnDataListener<List<CommonProduceListBean>> listener);

    /**
     * 通过肥料登记号进行真伪检验
     */
    void fertilizerCheck(String pID, OnDataListener<CommonFertilizerBean> listener);

    /**
     * 通过农药登记号进行真伪检验
     */
    void pestilizerCheck(String pID, OnDataListener<CommonPesticideBean> listener);

    /**
     * 增加一个肥料
     */
    void addFertilizer(CommonFertilizerBean bean, OnDataListener<Object> listener);

    /**
     * 增加一个农药
     */
    void addPesticide(CommonPesticideBean bean, OnDataListener<Object> listener);

    /**
     * 增加一个种子
     */
    void addSeed(CommonSeedBean bean, OnDataListener<Object> listener);

    /**
     * 得到指定类型的生产资料集合
     */
    void getResourceByType(int type, OnDataListener<List<CommonResourceBean>> listener);

    /**
     * 得到指定类型的生产资料
     */
    void getSpecifyResources(String resID, int type, OnDataListener<Object> listener);

    /**
     * 删除一个生产资料
     */
    void deleteResource(String resID,int type,OnDataListener<Object> listener);
}
