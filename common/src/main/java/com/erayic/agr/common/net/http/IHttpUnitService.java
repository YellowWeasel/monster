package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;

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
     * 得到管理单元列表
     *
     * @param type 管理单元类型（UnitType）
     * @return DataBack
     */
    @GET("Unit/GetAllUnit")
    Observable<DataBack<List<CommonUnitListBean>>> getAllUnit(
            @Query("type") int type
    );

}
