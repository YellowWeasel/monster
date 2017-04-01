package com.erayic.agr.common.model;

import com.erayic.agr.common.net.OnDataListener;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IIndexModel {

    /**
     * 登录
     */
    void login(String appID, String tel, String pass, String phoneCode, OnDataListener<Object> listener);


}
