package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：请求activity带返回的功能码
 */

public class EnumRequestType {

    public static final int TYPE_RETURN_USER = 9901;//选择用户
    public static final int TYPE_RETURN_PRODUCT = 9902;//选择产品
    public static final int TYPE_RETURN_FERTILIZER = 9903;//选择肥料
    public static final int TYPE_RETURN_PESTICIDE = 9904;//选择农药
    public static final int TYPE_RETURN_SEED = 9905;//选择种苗

    public static String getTypeDes(int type) {
        switch (type) {
            case TYPE_RETURN_USER:
                return "选择用户";
            case TYPE_RETURN_PRODUCT:
                return "选择产品";
            case TYPE_RETURN_FERTILIZER:
                return "选择肥料";
            case TYPE_RETURN_PESTICIDE:
                return "选择农药";
            case TYPE_RETURN_SEED:
                return "选择种苗";
            default:
                return "未知";
        }
    }

}
