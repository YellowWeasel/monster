package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：生产操作类型
 */

public class EnumOperationType {

    public static final int TYPE_Sowing = 1;//播种
    public static final int TYPE_Fertilize = 2;//施肥
    public static final int TYPE_Pesticide = 3;//打药
    public static final int TYPE_Pick = 4;//采摘
    public static final int TYPE_Feeding = 5;//投料
    public static final int TYPE_Disinfection = 6;//消毒
    public static final int TYPE_Daily = 0;//日常操作
    public static final int TYPE_Other = 999;//其他

    public static String getStatueDes(int statue) {
        switch (statue) {
            case TYPE_Sowing:
                return "播种";
            case TYPE_Fertilize:
                return "施肥";
            case TYPE_Pesticide:
                return "打药";
            case TYPE_Pick:
                return "采摘";
            case TYPE_Feeding:
                return "投料";
            case TYPE_Disinfection:
                return "消毒";
            case TYPE_Daily:
                return "日常操作";
            case TYPE_Other:
                return "其他";
            default:
                return "未知操作";
        }
    }

}
