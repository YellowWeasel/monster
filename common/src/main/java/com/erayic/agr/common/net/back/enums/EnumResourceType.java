package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class EnumResourceType {

    public static final int TYPE_PESTICIDE = 1;//农药
    public static final int TYPE_FERTILIZER = 2;//化肥
    public static final int TYPE_FEED = 3;//饲料
    public static final int TYPE_SEED = 4;//种子

    public static String getTypeDes(int type) {
        switch (type) {
            case TYPE_PESTICIDE:
                return "农药";
            case TYPE_FERTILIZER:
                return "化肥";
            case TYPE_FEED:
                return "饲料";
            case TYPE_SEED:
                return "种子";
            default:
                return "未定义";
        }
    }
}
