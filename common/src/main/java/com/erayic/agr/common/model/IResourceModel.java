package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonFertilizerBean;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.net.back.CommonProduceInfoBean;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.common.net.back.CommonResourceBean;
import com.erayic.agr.common.net.back.CommonSeedBean;
import com.erayic.agr.common.net.back.manage.CommonProduceTypeBean;

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
     * 得到产品详情
     */
    void getProductDetail(String proID, OnDataListener<CommonProduceInfoBean> listener);

    /**
     * 更新产品信息
     */
    void updateProduct(String proID, String productName, int classicID, String descript, OnDataListener<Object> listener);

    /**
     * 删除一个产品
     */
    void deleteProduct(String proID, OnDataListener<Object> listener);

    /**
     * 更新产品标题图片
     */
    void updateProductIcon(String proID, String icon, OnDataListener<Object> listener);

    /**
     * 增加一个产品照片
     */
    void addProductPhoto(String proID, String photo, OnDataListener<Object> listener);

    /**
     * 删除一个产品照片
     */
    void delProductPhoto(String imgID, String imgPath, OnDataListener<Object> listener);

    /**
     * 得到企业指定行业的所有产品
     */
    void getAllProductClassic(int type, OnDataListener<List<CommonProduceTypeBean>> listener);

    /**
     * 通过肥料登记号进行真伪检验
     */
    void fertilizerCheck(String pID, OnDataListener<CommonFertilizerBean> listener);

    /**
     * 通过农药登记号进行真伪检验
     */
    void pestilizerCheck(String pID, OnDataListener<CommonPesticideBean> listener);

    /**
     * 得到指定类型的生产资料集合
     */
    void getResourceByType(int type, OnDataListener<List<CommonResourceBean>> listener);

    /**
     * 得到指定类型的生产资料(农药)
     */
    void getSpecifyResourcesByPesticide(String resID, int type, OnDataListener<CommonPesticideBean> listener);

    /**
     * 得到指定类型的生产资料(肥料)
     */
    void getSpecifyResourcesByFertilizer(String resID, int type, OnDataListener<CommonFertilizerBean> listener);

    /**
     * 得到指定类型的生产资料(种子)
     */
    void getSpecifyResourcesBySeed(String resID, int type, OnDataListener<CommonSeedBean> listener);

    /**
     * 删除一个生产资料
     */
    void deleteResource(String resID, int type, OnDataListener<Object> listener);

    /**
     * 保存种子
     */
    void saveSeed(CommonSeedBean bean, OnDataListener<Object> listener);

    /**
     * 保存肥料
     */
    void saveFertilizer(CommonFertilizerBean bean, OnDataListener<Object> listener);

    /**
     * 保存一个自定义肥料
     */
    void saveFertilizerByUserDefine(CommonFertilizerBean bean, OnDataListener<Object> listener);

    /**
     * 保存农药
     */
    void savePesticide(CommonPesticideBean bean, OnDataListener<Object> listener);

    /**
     * 保存一个自定义农药
     */
    void savePesticideByUserDefine(CommonPesticideBean bean, OnDataListener<Object> listener);

}
