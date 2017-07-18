package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：用户权限
 */

public class EnumUserRole {

    public static final int Role_Admin = 0;//基地管理者
    public static final int Role_Manager = 1;//生产管理者
    public static final int Role_Usage = 9;//生产者

    public static String getRoleDes(int role) {
        switch (role) {
            case Role_Admin:
                return "基地管理员";
            case Role_Manager:
                return "生产管理员";
            case Role_Usage:
                return "基地生产者";
            default:
                return "未知用户";
        }
    }

}
