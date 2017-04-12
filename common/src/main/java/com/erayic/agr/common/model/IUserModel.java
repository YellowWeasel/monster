package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IUserModel  extends IProvider {

    void getUserInfo(OnDataListener<Object> listener);

}
