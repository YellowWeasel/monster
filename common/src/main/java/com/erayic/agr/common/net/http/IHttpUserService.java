package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.CommonUserInfoBean;
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
 * 注解：
 */

public interface IHttpUserService {

    /**
     * 获取用户信息
     *
     * @return DataBack
     */
    @GET("User/GetUserInfo")
    Flowable<DataBack<CommonUserInfoBean>> getUserInfo();

    /**
     * 更新用户姓名
     *
     * @param newName 用户姓名
     * @return DataBack
     */
    @GET("User/UpdateUserName")
    Flowable<DataBack<Object>> updateUserName(
            @Query("newName") String newName
    );

    /**
     * 更新用户头像
     *
     * @param bean 头像
     * @return DataBack
     */
    @POST("User/UpdateUserIcon")
    Flowable<DataBack<Object>> updateUserIcon(
            @Body CommonUserIconBean bean
    );

    /**
     * 重设用户密码
     *
     * @param pass 用户密码
     * @return DataBack
     */
    @GET("User/SetPassword")
    Flowable<DataBack<Object>> setPassword(
            @Query("pass") String pass
    );

    /**
     * 得到基地所有用户
     *
     * @return DataBack
     */
    @GET("User/GetAllUserByBase")
    Flowable<DataBack<List<CommonPersonnelBean>>> GetAllUserByBase();

    /**
     * 根据基地ID得到基地的所有用户
     */
    @GET("User/GetAllUserBySpecifyBase")
    Flowable<DataBack<List<CommonPersonnelBean>>> getAllUserBySpecifyBase(
            @Query("baseID") String baseID
    );

    /**
     * 更新电话号码
     *
     * @param newTel    新电话号码
     * @param oriTel    原始电话号码
     * @param verifyNum 校验码
     * @return DataBack
     */
    @GET("User/UpdateTel")
    Flowable<DataBack<Object>> UpdateTel(
            @Query("newTel") String newTel,
            @Query("oriTel") String oriTel,
            @Query("verifyNum") String verifyNum
    );

    /**
     * 更新用户信息
     *
     * @param userID 用户ID
     * @param name   用户名称
     * @param tel    用户电话
     * @param role   用户权限
     * @return DataBack
     */
    @GET("User/UpdateUserInfo")
    Flowable<DataBack<Object>> updateUserInfo(
            @Query("userID") String userID,
            @Query("name") String name,
            @Query("tel") String tel,
            @Query("role") int role
    );

    /**
     * 删除用户信息
     *
     * @param userID 用户ID
     * @return DataBack
     */
    @GET("User/DeleteUser")
    Flowable<DataBack<Object>> deleteUser(
            @Query("userID") String userID
    );

    /**
     * 发送邀请
     *
     * @param tel  被邀请的手机号
     * @param name 被邀请用户的真实姓名
     * @param role 被邀请的指定的权限(0：基地管理者,1：生产管理者，9:生产者)
     * @return
     */
    @GET("User/SendInvite")
    Flowable<DataBack<Object>> sendInvite(
            @Query("tel") String tel,
            @Query("name") String name,
            @Query("role") int role
    );


    /**
     * 得到一个用户的所有基地
     */
    @GET("EntBase/GetBaseListByUser")
    Flowable<DataBack<List<CommonBaseListBean>>> getBaseListByUser(
    );

    /**
     * 用户变更所属基地
     *
     * @param newBaseID 欲变更基地ID
     * @return DataBack
     */
    @GET("EntBase/ChangeBase")
    Flowable<DataBack<Object>> changeBase(
            @Query("newBaseID") String newBaseID
    );

}
