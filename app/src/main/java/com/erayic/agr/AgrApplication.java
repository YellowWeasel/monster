package com.erayic.agr;

import com.erayic.agr.common.base.BaseApplication;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class AgrApplication extends BaseApplication {

    public static AgrApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

    }


}
