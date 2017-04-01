package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpCodeService;

import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpCodeManager {

    private static HttpCodeManager manager;

    private static IHttpCodeService service;

    private HttpCodeManager() {
    }

    public static HttpCodeManager getInstance() {
        if (manager == null) {
            synchronized (HttpIndexManager.class) {
                if (manager == null) {
                    manager = new HttpCodeManager();
                    service = HttpRetrofit.getNoCookiesRetrofit().create(IHttpCodeService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 发送验证码
     */
    public Observable sendTelVerify(String tel) {
        return service.sendTelVerify(tel);
    }

    /**
     * 校验验证码
     */
    public Observable checkTelVerify(String appID, String tel, String code, String verifyNum) {
        return service.checkTelVerify(appID, tel, code, verifyNum);
    }

}
