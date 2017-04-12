package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.UserInfoBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IHttpUserService {

    /**
     * 获取用户信息
     *
     * @return DataBack
     */
    @GET("User/GetUserInfo")
    Observable<DataBack<UserInfoBean>> getUserInfo( );

}
