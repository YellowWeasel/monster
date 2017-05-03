package com.erayic.agr.manage.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IPersonnelInfoPresenter {

    /**
     * 邀请用户
     */
    void sendInvite(String tel,String name,int role);
    /**
     * 更新用户信息
     */
    void updateUserInfo(String userID,String tel,String name,int role);

    /**
     * 删除用户信息
     */
    void deleteUser(String userID);

}
