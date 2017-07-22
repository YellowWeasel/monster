package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.back.user.CommonUserIconBean;
import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpUserService;

import io.reactivex.Flowable;


/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpUserManager {

    private static HttpUserManager manager;

    private static IHttpUserService serviceRequest, serviceReceivedCookies;

    private HttpUserManager() {
    }

    public static HttpUserManager getInstance() {
        if (manager == null) {
            synchronized (IHttpUserService.class) {
                if (manager == null) {
                    manager = new HttpUserManager();
                    serviceRequest = HttpRetrofit.getRequestCookiesRetrofit().create(IHttpUserService.class);
                    serviceReceivedCookies = HttpRetrofit.getRequestReceivedCookiesRetrofit().create(IHttpUserService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 获取用户信息
     */
    public Flowable getUserInfo() {
        return serviceRequest.getUserInfo();
    }

    /**
     * 更新用户姓名
     */
    public Flowable updateUserName(String newName) {
        return serviceRequest.updateUserName(newName);
    }

    /**
     * 更新用户头像
     */
    public Flowable updateUserIcon(String icon) {
        CommonUserIconBean bean = new CommonUserIconBean();
        bean.setIcon(icon);
        return serviceRequest.updateUserIcon(bean);
    }

    /**
     * 重设用户密码
     */
    public Flowable setPassword(String pass) {
        return serviceRequest.setPassword(pass);
    }

    /**
     * 得到基地所有用户
     */
    public Flowable GetAllUserByBase() {
        return serviceRequest.GetAllUserByBase();
    }

    /**
     * 得到指定基地的所有用户
     */
    public Flowable getAllUserBySpecifyBase(String baseID) {
        return serviceRequest.getAllUserBySpecifyBase(baseID);
    }

    /**
     * 更新电话号码
     */
    public Flowable UpdateTel(String newTel, String oriTel, String verifyNum) {
        return serviceRequest.UpdateTel(newTel, oriTel, verifyNum);
    }

    /**
     * 更新用户信息
     */
    public Flowable updateUserInfo(String userID, String name, String tel, int role) {
        return serviceRequest.updateUserInfo(userID, name, tel, role);
    }

    /**
     * 删除用户信息
     */
    public Flowable deleteUser(String userID) {
        return serviceRequest.deleteUser(userID);
    }

    /**
     * 发送邀请
     */
    public Flowable sendInvite(String tel, String name, int role) {
        return serviceRequest.sendInvite(tel, name, role);
    }

    /**
     * 得到一个用户的所有基地
     */
    public Flowable getBaseListByUser() {
        return serviceRequest.getBaseListByUser();
    }

    /**
     * 用户变更所属基地(重新保存cookies)
     */
    public Flowable changeBase(String newBaseID) {
        return serviceReceivedCookies.changeBase(newBaseID);
    }
}
