package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.img.CommonResultImage;

import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IIndexModel extends IProvider {

    /**
     * 登录
     */
    void login(String appID, String tel, String pass, String phoneCode, OnDataListener<Object> listener);

    /**
     * 企业注册
     */
    void enterpriseRegister(String baseName, String name, String pass, String tel, String appID, String phoneCode, String verifyNum, OnDataListener<Object> listener);

    /**
     * 邀请码注册
     */
    void userByInvite(String appID, String pass, String tel, String code, String phoneCode, String verifyNum, OnDataListener<Object> listener);

    /**
     * 发送验证码
     */
    void sendTelVerify(String tel, OnDataListener<Object> listener);

    /**
     * 校验验证码
     */
    void checkTelVerify(String appID, String tel, String code, String verifyCode, OnDataListener<Object> listener);

    /**
     * 上传图片
     */
    void uploadImg(String path, OnDataListener<CommonResultImage> listener);


}
