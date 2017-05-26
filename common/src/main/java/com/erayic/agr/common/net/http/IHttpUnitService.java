package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListByBaseBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IHttpUnitService {

    /**
     * 得到管理单元列表（含所有信息）
     *
     * @param type 管理单元类型（UnitType）
     * @return DataBack
     */
    @GET("Unit/GetAllUnit")
    Observable<DataBack<List<CommonUnitListBean>>> getAllUnit(
            @Query("type") int type
    );

    /**
     * 得到管理单元列表（只有单元列表）
     *
     * @param type 管理单元类型（UnitType）
     * @return DataBack
     */
    @GET("Unit/GetAllUnitByBase")
    Observable<DataBack<List<CommonUnitListByBaseBean>>> getAllUnitByBase(
            @Query("type") int type
    );

    /**
     * 新建一个批次
     *
     * @param proID    产品ID
     * @param seedID   种苗ID
     * @param seedName 种苗名称
     * @param quantity 种植数量
     * @param unit     种植数量单位（1:亩,2;个数）
     * @param stTime   种植时间
     * @param ope      操作者用户ID
     * @param unitID   隶属管理单元ID
     * @param unitType 隶属类型（1：基地，2：地块，3：塘，4：栏）
     * @return DataBack
     */
    @GET("Unit/CreateBatch")
    Observable<DataBack<Object>> createBatch(
            @Query("proID") String proID,
            @Query("seedID") String seedID,
            @Query("seedName") String seedName,
            @Query("quantity") String quantity,
            @Query("unit") int unit,
            @Query("stTime") String stTime,
            @Query("ope") String ope,
            @Query("unitID") String unitID,
            @Query("unitType") int unitType
    );

}
