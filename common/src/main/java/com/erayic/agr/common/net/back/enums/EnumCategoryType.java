package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：设备类型
 */

public class EnumCategoryType {

    public static final int TYPE_Irrigation = 1;//灌溉控制
    public static final int TYPE_Fan = 2;//风机控制
    public static final int TYPE_Spray = 3;//雨帘控制
    public static final int TYPE_Sunshade = 4;//遮阳控制
    public static final int TYPE_Ventilate = 5;//通风控制
    public static final int TYPE_Auxiliary = 6;//辅助控制

    public static String getStatueDes(int statue) {
        switch (statue) {
            case TYPE_Irrigation:
                return "灌溉控制";
            case TYPE_Fan:
                return "风机控制 ";
            case TYPE_Spray:
                return "雨帘控制";
            case TYPE_Sunshade:
                return "遮阳控制";
            case TYPE_Ventilate:
                return "通风控制";
            case TYPE_Auxiliary:
                return "辅助控制";
            default:
                return "未知";
        }
    }


}
