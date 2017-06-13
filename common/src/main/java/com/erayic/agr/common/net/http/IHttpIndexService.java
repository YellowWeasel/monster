package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.IndexLoginBean;
import com.erayic.agr.common.net.back.IndexRegisterUserBean;
import com.erayic.agr.common.net.back.img.CommonRequestImage;
import com.erayic.agr.common.net.back.img.CommonResultImage;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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
    Flowable<DataBack<IndexLoginBean>> login(
            @Query("appID") String appID,
            @Query("tel") String tel,
            @Query("pass") String pass,
            @Query("phoneCode") String phoneCode
    );

    /**
     * 1、企业注册（用户不存在）
     *
     * @param baseName  企业名称
     * @param name      真实姓名
     * @param pass      登录密码
     * @param tel       电话号码
     * @param appID     客户端ID
     * @param phoneCode 手机唯一识别码
     * @param verifyNum 短信校验码
     * @return DataBack
     */
    @POST("Basic/FristRegister")
    Flowable<DataBack<Object>> firstRegister(
            @Query("baseName") String baseName,
            @Query("name") String name,
            @Query("pass") String pass,
            @Query("tel") String tel,
            @Query("appID") String appID,
            @Query("phoneCode") String phoneCode,
            @Query("verifyNum") String verifyNum
    );

    /**
     * 2、企业注册（用户存在）
     *
     * @param baseName  基地名称
     * @param userID    用户ID
     * @param appID     应用ID
     * @param phoneCode 手机唯一识别码
     * @param verifyNum 短信校验码
     * @return DataBack
     */
    @POST("EntBase/EntRegister")
    Flowable<DataBack<Object>> entRegister(
            @Query("baseName") String baseName,
            @Query("userID") String userID,
            @Query("appID") String appID,
            @Query("phoneCode") String phoneCode,
            @Query("verifyNum") String verifyNum
    );

    /**
     * 4、以邀请方式进行用户注册（用户不存在）
     *
     * @param appID     应用系统ID
     * @param pass      用户密码
     * @param tel       被邀请的手机号
     * @param code      邀请码
     * @param phoneCode 手机唯一识别码
     * @param verifyNum 短信校验码
     * @return DataBack
     */
    @POST("Basic/UserInviteByTel")
    Flowable<DataBack<Object>> userInviteByTel(
            @Query("appID") String appID,
            @Query("pass") String pass,
            @Query("tel") String tel,
            @Query("code") String code,
            @Query("phoneCode") String phoneCode,
            @Query("verifyNum") String verifyNum
    );

    /**
     * 5、以邀请方式进行用户注册（用户存在）
     *
     * @param appID     应用系统ID
     * @param userID    用户ID
     * @param tel       被邀请的手机号
     * @param code      邀请码
     * @param phoneCode 手机唯一识别码
     * @param verifyNum 短信校验码
     * @return DataBack
     */
    @POST("Basic/UserInviteByUserID")
    Flowable<DataBack<Object>> userInviteByUserID(
            @Query("appID") String appID,
            @Query("userID") String userID,
            @Query("tel") String tel,
            @Query("code") String code,
            @Query("phoneCode") String phoneCode,
            @Query("verifyNum") String verifyNum
    );

    /**
     * 6、通过手机进行用户验证（注册时验证用户）
     *
     * @param tel  电话号码
     * @param pass 登录密码
     * @return DataBack
     */
    @GET("Basic/UserVerify")
    Flowable<DataBack<IndexRegisterUserBean>> userVerify(
            @Query("tel") String tel,
            @Query("pass") String pass
    );

    /**
     * 发送验证码
     *
     * @param tel 手机号码
     * @return DataBack
     */
    @GET("Basic/SendTelVerify")
    Flowable<DataBack<Object>> sendTelVerify(
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
    @GET("Basic/TelVerify")
    Flowable<DataBack<Object>> checkTelVerify(
            @Query("appID") String appID,
            @Query("tel") String tel,
            @Query("code") String code,
            @Query("verifyNum") String verifyNum
    );

    /**
     * 上传图片
     *
     * @return DataBack
     */
    @POST("Basic/UploadImg")
    Flowable<DataBack<CommonResultImage>> uploadImg(
            @Body CommonRequestImage image
    );


}
