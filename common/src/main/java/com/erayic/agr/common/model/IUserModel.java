package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.CommonUserInfoBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IUserModel extends IProvider {

    /**
     * 获取用户信息
     */
    void getUserInfo(OnDataListener<CommonUserInfoBean> listener);

    /**
     * 更新用户姓名
     */
    void updateUserName(String newName, OnDataListener<Object> listener);

    /**
     * 更新用户头像
     */
    void updateUserIcon(String path, OnDataListener<Object> listener);

    /**
     * 重设用户密码
     */
    void setPassword(String pass, OnDataListener<Object> listener);

    /**
     * 得到基地所有用户
     */
    void GetAllUserByBase(OnDataListener<List<CommonPersonnelBean>> listener);

    /**
     * 得到指定基地的所有用户
     */
    void getAllUserBySpecifyBase(String baseID, OnDataListener<List<CommonPersonnelBean>> listener);

    /**
     * 更新电话号码
     */
    void UpdateTel(String newTel, String oriTel, String verifyNum, OnDataListener<Object> listener);

    /**
     * 更新用户信息
     */
    void updateUserInfo(String userID, String name, String tel, int role, OnDataListener<Object> listener);

    /**
     * 删除用户信息
     */
    void deleteUser(String userID, OnDataListener<Object> listener);

    /**
     * 邀请用户
     */
    void sendInvite(String tel, String name, int role, OnDataListener<Object> listener);

    /**
     * 得到一个用户的所有基地
     */
    void getBaseListByUser(OnDataListener<List<CommonBaseListBean>> listener);

    /**
     * 用户变更所属基地(重新保存cookies)
     */
    void changeBase(String newBaseID, OnDataListener<Object> listener);


}
