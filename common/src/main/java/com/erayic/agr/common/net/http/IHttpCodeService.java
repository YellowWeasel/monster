package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IHttpCodeService {

    /**
     * 发送验证码
     *
     * @param tel 手机号码
     * @return DataBack
     */
    @GET("Basic/SendTelVerify")
    Observable<DataBack<Object>> sendTelVerify(
            @Query("tel") String tel
    );

    /**
     * 校验验证码
     *
     * @param appID     应用ID
     * @param tel       手机号码
     * @param code      手机唯一标识码
     * @param verifyNum 验证码
     * @return
     */
    @GET("")
    Observable<DataBack<Object>> checkTelVerify(
            @Query("appID") String appID,
            @Query("tel") String tel,
            @Query("code") String code,
            @Query("verifyNum") String verifyNum
    );

}
