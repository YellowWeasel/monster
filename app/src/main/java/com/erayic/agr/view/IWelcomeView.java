package com.erayic.agr.view;

import com.erayic.agr.common.base.IBaseView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IWelcomeView extends IBaseView{

    void toMainActivity();

    /**
     * 延时销毁
     */
    void delayFinish();

}
