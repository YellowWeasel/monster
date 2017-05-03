package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.CommonByteArrayBean;
import com.erayic.agr.common.net.back.CommonFertilizerBean;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.common.net.back.CommonSeedBean;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

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
    Observable<DataBack<Object>> createProduct(
            @Query("productName") String productName
    );

    /**
     * 得到一个企业的所有产品
     *
     * @return DataBack
     */
    @GET("Produce/GetAllProduct")
    Observable<DataBack<List<CommonProduceListBean>>> getAllProduct();

    /**
     * 得到指定行业产品分类
     *
     * @param type 行业类型（1：种植业，2：林业，3：畜牧业，4：渔业）
     * @return DataBack
     */
    @GET("Produce/GetAllProductClassic")
    Observable<DataBack<List<CommonProduceListBean>>> getAllProductClassic(
            @Query("type") int type
    );

    /**
     * 得到产品详情
     *
     * @param proID 产品ID
     * @return DataBack
     */
    @GET("Produce/GetProductDetail")
    Observable<DataBack<Object>> getProductDetail(
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
    Observable<DataBack<Object>> updateProduct(
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
    Observable<DataBack<Object>> deleteProduct(
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
    Observable<DataBack<Object>> updateProductIcon(
            @Query("proID") String proID,
            @Body CommonByteArrayBean bean
    );

    /**
     * 通过肥料登记号进行真伪检验
     *
     * @param pID 肥料登记号
     * @return DataBack
     */
    @GET("Resource/FertilizerCheck")
    Observable<DataBack<CommonFertilizerBean>> fertilizerCheck(
            @Query("pID") String pID
    );

    /**
     * 通过农药登记号进行真伪检验
     *
     * @param pID 农药登记号
     * @return DataBack
     */
    @GET("Resource/PestilizerCheck")
    Observable<DataBack<CommonPesticideBean>> pestilizerCheck(
            @Query("pID") String pID
    );

    /**
     * 增加一个肥料
     *
     * @param bean 肥料实体类
     * @return DataBack
     */
    @POST("Resource/AddFertilizer")
    Observable<DataBack<Object>> addFertilizer(
            @Body CommonFertilizerBean bean
    );

    /**
     * 增加一个农药
     *
     * @param bean 农药实体类
     * @return DataBack
     */
    @POST("Resource/AddPesticide")
    Observable<DataBack<Object>> addPesticide(
            @Body CommonPesticideBean bean
    );

    /**
     * 增加一个种子
     *
     * @param bean 种子实体类
     * @return DataBack
     */
    @POST("Resource/AddSeed")
    Observable<DataBack<Object>> addSeed(
            @Body CommonSeedBean bean
    );

    /**
     * 得到指定类型的生产资料集合
     *
     * @param type 生产资料类型（1：农药，2：肥料，3：饲料，4：种子）
     * @return DataBack
     */
    @GET("Resource/GetResourceByType")
    Observable<DataBack<List<Object>>> getResourceByType(
            @Query("type") int type
    );

}
