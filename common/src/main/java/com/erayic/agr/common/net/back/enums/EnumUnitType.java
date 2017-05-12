package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class EnumUnitType {

    public static final int TYPE_BASE = 1;//基地
    public static final int TYPE_PLOTS = 2;//地块
    public static final int TYPE_POND = 3;//塘
    public static final int TYPE_FENCE = 4;//栏

    public static String getStatueDes(int statue) {
        switch (statue) {
            case TYPE_BASE:
                return "基地";
            case TYPE_PLOTS:
                return "地块 ";
            case TYPE_POND:
                return "塘";
            case TYPE_FENCE:
                return "栏";
            default:
                return "未知";
        }
    }

}
