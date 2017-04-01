package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.IndexLoginBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IHttpIndexService {

    /**
     * 1、登录
     *
     * @param appID     应用系统ID
     * @param tel       电话号码
     * @param pass      用户密码
     * @param phoneCode 手机识别码
     * @return DataBack
     */
    @GET("Basic/Login")
    Observable<DataBack<IndexLoginBean>> login(
            @Query("appID") String appID,
            @Query("tel") String tel,
            @Query("pass") String pass,
            @Query("phoneCode") String phoneCode
    );

}
