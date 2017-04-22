package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class EnumInvoiceStatue {

    public static final int STATUE_NoRequest = 0;
    public static final int STATUE_Request = 1;
    public static final int STATUE_Send = 2;

    public static String getStatueDes(int statue) {
        switch (statue) {
            case STATUE_NoRequest:
                return "未索要";
            case STATUE_Request:
                return "已索要 ";
            case STATUE_Send:
                return "已开出";
            default:
                return "未知";
        }
    }

}
