package com.erayic.agr.index.view;

import com.erayic.agr.common.base.IBaseView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IRegisterByUserView extends IBaseView{

    /**
     * 验证码发送成功
     */
    void verSendSure();

    /**
     * 注册成功
     */
    void registerSure();

}
