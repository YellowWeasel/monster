package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.CommonBaseInfoBean;
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.common.net.back.CommonEntInfoBean;
import com.erayic.agr.common.net.back.CommonUnitInfoBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IHttpManageService {


    /**
     * 获取企业信息
     *
     * @return DataBack
     */
    @GET("EntBase/GetEntInfo")
    Flowable<DataBack<CommonEntInfoBean>> getEntInfo();

    /**
     * 更新企业名称
     *
     * @param newEntName 欲更新企业名称
     * @return DataBack
     */
    @GET("EntBase/UpadteEntName")
    Flowable<DataBack<Object>> updateEntName(
            @Query("newEntName") String newEntName
    );

    /**
     * 得到一个企业的所有基地列表
     *
     * @return DataBack
     */
    @GET("EntBase/GetBaseByEnt")
    Flowable<DataBack<List<CommonBaseListBean>>> getBaseByEnt();

    /**
     * 增加一个企业基地
     *
     * @param baseName 基地名称
     * @return DataBack
     */
    @GET("EntBase/AddBaseByEnt")
    Flowable<DataBack<Object>> addBaseByEnt(
            @Query("baseName") String baseName
    );

    /**
     * 用户变更所属基地
     *
     * @param newBaseID 基地ID
     * @return DataBack
     */
    @GET("EntBase/ChangeBase")
    Flowable<DataBack<Object>> changeBase(
            @Query("newBaseID") String newBaseID
    );

    /**
     * 上传一个基地位置信息
     *
     * @return DataBack
     */
    @POST("EntBase/SetBasePostition")
    Flowable<DataBack<Object>> setBasePostition(
            @Query("baseID") String baseID,
            @Body Object object
    );

    /**
     * 得到基地信息
     *
     * @param type   管理单元类型（始终为UnitType.Block=2)
     * @param baseID 基地ID
     * @return DataBack
     */
    @GET("EntBase/GetBaseInfo")
    Flowable<DataBack<CommonBaseInfoBean>> getBaseInfo(
            @Query("type") int type,
            @Query("baseID") String baseID
    );

    /**
     * 新建一个管理单元
     *
     * @param baseID   基地ID
     * @param unitName 管理单元名称
     * @param type     管理单元类型 1：批次， 2：地块，4：塘
     * @return
     */
    @GET("Unit/AddUnit")
    Flowable<DataBack<Object>> addUnit(
            @Query("baseID") String baseID,
            @Query("unitName") String unitName,
            @Query("type") int type
    );

    /**
     * 更新基地信息
     *
     * @param baseID   基地ID
     * @param baseName 基地名称
     * @param descript 基地描述
     * @return DataBack
     */
    @GET("EntBase/UpdateBaseInfo")
    Flowable<DataBack<Object>> updateBaseInfo(
            @Query("baseID") String baseID,
            @Query("baseName") String baseName,
            @Query("descript") String descript
    );

    /**
     * 得到管理单元详情
     *
     * @param unitID 管理单元ID
     * @param type   管理单元类型 1：批次， 2：地块，4：塘
     * @return
     */
    @GET("Unit/GetUnitDetailInfo")
    Flowable<DataBack<CommonUnitInfoBean>> getUnitDetailInfo(
            @Query("unitID") String unitID,
            @Query("type") int type
    );

    /**
     * 更新地块信息
     *
     * @param unitID       管理单元ID
     * @param unitName     管理单元名称
     * @param area         管理单元面积
     * @param regions      管理单元位置
     * @param workes       管理单元负责人
     * @param isGreenhouse 是否为大棚
     * @return DataBack
     */
    @POST("Unit/UpdateBlockInfo")
    Flowable<DataBack<Object>> updateBlockInfo(
            @Query("unitID") String unitID,
            @Query("unitName") String unitName,
            @Query("area") double area,
            @Query("regions") List<String> regions,
            @Query("workes") List<String> workes,
            @Query("isGreenhouse") boolean isGreenhouse
    );

}
