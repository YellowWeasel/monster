package com.erayic.agr.index.view;

import com.erayic.agr.common.base.IBaseView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface ICodeView extends IBaseView {

    /**
     * 验证码发送成功
     */
    void verSendSure();

    /**
     * 校验成功
     */
    void checkSure();

}
