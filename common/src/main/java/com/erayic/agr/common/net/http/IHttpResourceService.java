package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.CommonBytePhotoBean;
import com.erayic.agr.common.net.back.CommonFertilizerBean;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.net.back.CommonProduceInfoBean;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.common.net.back.CommonResourceBean;
import com.erayic.agr.common.net.back.CommonSeedBean;
import com.erayic.agr.common.net.back.manage.CommonProduceTypeBean;
import com.erayic.agr.common.net.back.user.CommonUserIconBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：产品、生产资料相关网络请求接口
 */

public interface IHttpResourceService {

    /**
     * 创建一个企业产品
     *
     * @param productName 欲创建产品名称
     * @return DataBack
     */
    @GET("Produce/CreateProduct")
    Flowable<DataBack<Object>> createProduct(
            @Query("productName") String productName
    );

    /**
     * 得到一个企业的所有产品
     *
     * @return DataBack
     */
    @GET("Produce/GetAllProduct")
    Flowable<DataBack<List<CommonProduceListBean>>> getAllProduct();

    /**
     * 得到指定行业产品分类
     *
     * @param type 行业类型（1：种植业，2：林业，3：畜牧业，4：渔业）
     * @return DataBack
     */
    @GET("Produce/GetAllProductClassic")
    Flowable<DataBack<List<CommonProduceTypeBean>>> getAllProductClassic(
            @Query("type") int type
    );

    /**
     * 得到产品详情
     *
     * @param proID 产品ID
     * @return DataBack
     */
    @GET("Produce/GetProductDetail")
    Flowable<DataBack<CommonProduceInfoBean>> getProductDetail(
            @Query("proID") String proID
    );

    /**
     * 更新产品信息
     *
     * @param proID       产品ID（Guid）
     * @param productName 产品名称
     * @param classicID   产品类型ID
     * @param descript    产品
     * @return DataBack
     */
    @GET("Produce/UpdateProduct")
    Flowable<DataBack<Object>> updateProduct(
            @Query("proID") String proID,
            @Query("productName") String productName,
            @Query("classicID") int classicID,
            @Query("descript") String descript
    );

    /**
     * 删除一个产品
     *
     * @param proID 产品ID
     * @return DataBack
     */
    @GET("Produce/DeleteProduct")
    Flowable<DataBack<Object>> deleteProduct(
            @Query("proID") String proID
    );

    /**
     * 更新产品标题图像
     *
     * @param proID 产品ID
     * @param bean  产品标题图像（byte[]）
     * @return DataBack
     */
    @POST("Produce/UpdateProductIcon")
    Flowable<DataBack<Object>> updateProductIcon(
            @Query("proID") String proID,
            @Body CommonUserIconBean bean
    );

    /**
     * 增加一个产品照片
     *
     * @param proID 产品ID（Guid）
     * @param bean  产品照片（byte[]）
     * @return DataBack
     */
    @POST("Produce/AddProductPhoto")
    Flowable<DataBack<Object>> addProductPhoto(
            @Query("proID") String proID,
            @Body CommonBytePhotoBean bean
    );

    /**
     * 删除一个产品图片
     *
     * @param imgID   产品图片ID（Guid）
     * @param imgPath 产品照片路径（string）
     * @return DataBack
     */
    @GET("Produce/DelProductPhoto")
    Flowable<DataBack<Object>> delProductPhoto(
            @Query("imgID") String imgID,
            @Query("imgPath") String imgPath
    );


    /**
     * 通过肥料登记号进行真伪检验
     *
     * @param pID 肥料登记号
     * @return DataBack
     */
    @GET("Resource/FertilizerCheck")
    Flowable<DataBack<CommonFertilizerBean>> fertilizerCheck(
            @Query("pID") String pID
    );

    /**
     * 通过农药登记号进行真伪检验
     *
     * @param pID 农药登记号
     * @return DataBack
     */
    @GET("Resource/PestilizerCheck")
    Flowable<DataBack<CommonPesticideBean>> pestilizerCheck(
            @Query("pID") String pID
    );

    /**
     * 得到指定类型的生产资料集合
     *
     * @param type 生产资料类型（1：农药，2：肥料，3：饲料，4：种子）
     * @return DataBack
     */
    @GET("Resource/GetResourceByType")
    Flowable<DataBack<List<CommonResourceBean>>> getResourceByType(
            @Query("type") int type
    );

    /**
     * 删除一个生产资料
     *
     * @param resID 生产资料ID
     * @param type  生产资料类型（1：农药，2：肥料，3：饲料，4：种子）
     * @return DataBack
     */
    @GET("Resource/DeleteResource")
    Flowable<DataBack<CommonPesticideBean>> deleteResource(
            @Query("resID") String resID,
            @Query("type") int type
    );


    /**
     * 得到指定类型的生产资料详情(农药)
     *
     * @param resID 生产资料ID
     * @param type  生产资料类型（1：农药，2：肥料，3：饲料，4：种子）
     * @return DataBack
     */
    @GET("Resource/GetSpecifyResources")
    Flowable<DataBack<CommonPesticideBean>> getSpecifyResourcesByPesticide(
            @Query("resID") String resID,
            @Query("type") int type
    );


    /**
     * 得到指定类型的生产资料详情(肥料)
     *
     * @param resID 生产资料ID
     * @param type  生产资料类型（1：农药，2：肥料，3：饲料，4：种子）
     * @return DataBack
     */
    @GET("Resource/GetSpecifyResources")
    Flowable<DataBack<CommonFertilizerBean>> getSpecifyResourcesByFertilizer(
            @Query("resID") String resID,
            @Query("type") int type
    );

    /**
     * 得到指定类型的生产资料详情(种子)
     *
     * @param resID 生产资料ID
     * @param type  生产资料类型（1：农药，2：肥料，3：饲料，4：种子）
     * @return DataBack
     */
    @GET("Resource/GetSpecifyResources")
    Flowable<DataBack<CommonSeedBean>> getSpecifyResourcesBySeed(
            @Query("resID") String resID,
            @Query("type") int type
    );

    /**
     * 保存一个种子
     *
     * @param resID        资源ID
     * @param seedName     种子名称
     * @param pID          登记证号
     * @param manufacturer 生产厂家
     * @return DataBack
     */
    @POST("Resource/SaveSeed")
    Flowable<DataBack<Object>> saveSeed(
            @Query("resID") String resID,
            @Query("seedname") String seedName,
            @Query("pID") String pID,
            @Query("manufacturer") String manufacturer
    );

    /**
     * 保存一个肥料
     *
     * @param fert 肥料实体
     * @return DataBack
     */
    @POST("Resource/SaveFertilizer")
    Flowable<DataBack<Object>> saveFertilizer(
            @Body CommonFertilizerBean fert
    );

    /**
     * 保存一个自定义化肥
     *
     * @param resID        生产资料ID
     * @param fertName     肥料名称
     * @param manufacturer 生产厂家
     * @return DataBack
     */
    @POST("Resource/SaveFertilizerByUserDefine")
    Flowable<DataBack<Object>> saveFertilizerByUserDefine(
            @Query("resID") String resID,
            @Query("fertname") String fertName,
            @Query("manufacturer") String manufacturer
    );

    /**
     * 保存一个农药
     *
     * @param pest 农药（CommonPesticideBean）
     * @return DataBack
     */
    @POST("Resource/SavePesticide")
    Flowable<DataBack<Object>> savePesticide(
            @Body CommonPesticideBean pest
    );

    /**
     * 保存一个自定义农药
     *
     * @param resID        生产资料ID（新建农药为null)
     * @param pestname     农药名称
     * @param manufacturer 生产厂家
     * @param toxicity     毒性
     * @return DataBack
     */
    @POST("Resource/SavePesticideByUserDefine")
    Flowable<DataBack<Object>> savePesticideByUserDefine(
            @Query("resID") String resID,
            @Query("pestname") String pestname,
            @Query("manufacturer") String manufacturer,
            @Query("toxicity") String toxicity
    );

}
