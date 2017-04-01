package com.erayic.agr.common.config;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.alibaba.android.arouter.exception.InitException;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：用户信息存储单例SharedPreferences
 */

public class UserProgramConfigManage {

    // 单例模式类对象
    private static ContextWrapper mWrapper = null;
    private static UserProgramConfigManage m_ProgramConfig = null;
    private SharedPreferences mPreferences = null;

    //配置参数定义为成员全局变量
    private boolean isAutoLogin;//自动登录
    private String userID;//用户ID
    private String baseID;//活动基地ID
    private String baseName;//基地名称
    private String entID;//企业ID
    private String entName;//企业名称
    private String userName;//用户姓名
    private int userStatus;//用户状态
    private String userExpireTime;//到期时间
    private String userRegisterTime;//注册时间
    private int userRole;//用户权限

    // 单例模式获取实例
    public static UserProgramConfigManage getInstance() {
        if (mWrapper == null)
            throw new InitException("UserProgramConfigManage::Init::Invoke init(context) first!");
        else {
            if (m_ProgramConfig == null) {
                m_ProgramConfig = new UserProgramConfigManage(mWrapper);
                // 为了提高速度，在这 里读取配置
                m_ProgramConfig.isAutoLogin = m_ProgramConfig.mPreferences
                        .getBoolean("Config_IsAutoLogin", false);
                m_ProgramConfig.userID = m_ProgramConfig.mPreferences
                        .getString("Config_UserID", "");
                m_ProgramConfig.baseID = m_ProgramConfig.mPreferences
                        .getString("Config_BaseID", "");
                m_ProgramConfig.userName = m_ProgramConfig.mPreferences
                        .getString("Config_UserName", "");
                m_ProgramConfig.userStatus = m_ProgramConfig.mPreferences
                        .getInt("Config_UserStatus", -1);
                m_ProgramConfig.userExpireTime = m_ProgramConfig.mPreferences
                        .getString("Config_UserExpireTime", "");
                m_ProgramConfig.userRegisterTime = m_ProgramConfig.mPreferences
                        .getString("Config_UserRegisterTime", "");
                m_ProgramConfig.userRole = m_ProgramConfig.mPreferences
                        .getInt("Config_UserRole", -1);
                m_ProgramConfig.baseName = m_ProgramConfig.mPreferences
                        .getString("Config_BaseName", "");
                m_ProgramConfig.entID = m_ProgramConfig.mPreferences
                        .getString("Config_EntID", "");
                m_ProgramConfig.entName = m_ProgramConfig.mPreferences
                        .getString("Config_EntName", "");
            }
        }
        return m_ProgramConfig;
    }

    public static void init(Context context) {
        mWrapper = new ContextWrapper(context);
    }

    // 配置getSharedPreferences
    private UserProgramConfigManage(Context base) {
        // super(base);
        mPreferences = mWrapper.getSharedPreferences("ERAYIC_AGR_USER",
                ContextWrapper.MODE_PRIVATE);

    }

    //清空所有信息
    public void deleteAll() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.commit();
        this.isAutoLogin = false;
    }

    public void setAutoLogin(boolean isAutoLogin) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("Config_IsAutoLogin", isAutoLogin);
        editor.commit();
        this.isAutoLogin = isAutoLogin;
    }

    public void setUserID(String userID) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("Config_UserID", userID);
        editor.commit();
        this.userID = userID;
    }

    public void setBaseID(String baseID) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("Config_BaseID", baseID);
        editor.commit();
        this.baseID = baseID;
    }

    public void setBaseName(String baseName) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("Config_BaseName", baseID);
        editor.commit();
        this.baseName = baseName;
    }

    public void setEntID(String entID) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("Config_EntID", baseID);
        editor.commit();
        this.entID = entID;
    }

    public void setEntName(String entName) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("Config_EntName", baseID);
        editor.commit();
        this.entName = entName;
    }

    public void setUserName(String userName) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("Config_UserName", userName);
        editor.commit();
        this.userName = userName;
    }

    public void setUserStatus(int userStatus) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("Config_UserStatus", userStatus);
        editor.commit();
        this.userStatus = userStatus;
    }

    public void setUserExpireTime(String userExpireTime) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("Config_UserExpireTime", userExpireTime);
        editor.commit();
        this.userExpireTime = userExpireTime;
    }

    public void setUserRegisterTime(String userRegisterTime) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("Config_UserRegisterTime", userRegisterTime);
        editor.commit();
        this.userRegisterTime = userRegisterTime;
    }

    public void setUserRole(int userRole) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("Config_UserRole", userRole);
        editor.commit();
        this.userRole = userRole;
    }

    public boolean isAutoLogin() {
        return isAutoLogin;
    }

    public String getUserID() {
        return userID;
    }

    public String getBaseID() {
        return baseID;
    }

    public String getBaseName() {
        return baseName;
    }

    public String getEntID() {
        return entID;
    }

    public String getEntName() {
        return entName;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public String getUserExpireTime() {
        return userExpireTime;
    }

    public String getUserRegisterTime() {
        return userRegisterTime;
    }

    public int getUserRole() {
        return userRole;
    }

}
