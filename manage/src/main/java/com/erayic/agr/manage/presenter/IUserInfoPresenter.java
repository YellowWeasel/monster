package com.erayic.agr.manage.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IUserInfoPresenter {

    /**
     * 获取用户信息
     */
    void getUserInfo();

    /**
     * 更新用户姓名
     */
    void updateUserName(String userName);

    /**
     * 更新用户头像
     */
    void updateUserIcon(String path);

}
