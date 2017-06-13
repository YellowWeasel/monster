package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.back.img.CommonRequestImage;
import com.erayic.agr.common.net.http.IHttpIndexService;
import com.erayic.agr.common.net.http.HttpRetrofit;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpIndexManager {

    private static HttpIndexManager manager;

    private static IHttpIndexService serviceReceivedCookies, service;

    private HttpIndexManager() {
    }

    public static HttpIndexManager getInstance() {
        if (manager == null) {
            synchronized (HttpIndexManager.class) {
                if (manager == null) {
                    manager = new HttpIndexManager();
                    serviceReceivedCookies = HttpRetrofit.getReceivedCookiesRetrofit().create(IHttpIndexService.class);
                    service = HttpRetrofit.getDefaultRetrofit().create(IHttpIndexService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 1、登录
     */
    public Flowable login(String appID, String tel, String pass, String phoneCode) {
        return serviceReceivedCookies.login(appID, tel, pass, phoneCode);
    }

    /**
     * 1、企业注册（新用户）
     */
    public Flowable firstRegister(String baseName, String name, String pass, String tel, String appID, String phoneCode, String verifyNum) {
        return service.firstRegister(baseName, name, pass, tel, appID, phoneCode, verifyNum);
    }

    /**
     * 2、企业注册（用户存在）
     */
    public Flowable entRegister(String baseName, String userID, String appID, String phoneCode, String verifyNum) {
        return service.entRegister(baseName, userID, appID, phoneCode, verifyNum);
    }

    /**
     * 4、邀请码注册（用户不存在）
     */
    public Flowable userInviteByTel(String appID, String pass, String tel, String code, String phoneCode, String verifyNum) {
        return service.userInviteByTel(appID, pass, tel, code, phoneCode, verifyNum);
    }

    /**
     * 5、邀请码注册（用户存在）
     */
    public Flowable userInviteByUserID(String appID, String userID, String tel, String code, String phoneCode, String verifyNum) {
        return service.userInviteByUserID(appID, userID, tel, code, phoneCode, verifyNum);
    }

    /**
     * 6、通过手机进行用户验证（注册时验证用户）
     */
    public Flowable userVerify(String tel, String pass) {
        return service.userVerify(tel, pass);
    }

    /**
     * 发送验证码
     */
    public Flowable sendTelVerify(String tel) {
        return service.sendTelVerify(tel);
    }

    /**
     * 校验验证码
     */
    public Flowable checkTelVerify(String appID, String tel, String code, String verifyNum) {
        return service.checkTelVerify(appID, tel, code, verifyNum);
    }

    /**
     * 上传图片
     */
    public Flowable uploadImg(String img) {
        CommonRequestImage image = new CommonRequestImage();
        image.setImg(img);
        return service.uploadImg(image);
    }

}