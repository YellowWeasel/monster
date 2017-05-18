package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class EnumControlRelayStatus {

    public final static int TYPE_TurnOn = 1;//启动
    public final static int TYPE_TurnOff = 0;//停止
    public final static int TYPE_DirectTurnOn = 9;//正转启动
    public final static int TYPE_DirectOff = 3;//展开到头停止状态
    public final static int TYPE_ReverseTurnOn = 2;//反转启动
    public final static int TYPE_ReverseTurnOff = 4;//收拢到头
    public final static int TYPE_ErrorSta = 6;//错误
    public final static int TYPE_Unknow = 8;//未知


    public static String getStatueDes(int statue) {
        switch (statue) {
            case TYPE_TurnOn:
                return "已启动";
            case TYPE_TurnOff:
                return "已停止 ";
            case TYPE_DirectTurnOn:
                return "展开中";
            case TYPE_DirectOff:
                return "已展开";
            case TYPE_ReverseTurnOn:
                return "收拢中";
            case TYPE_ReverseTurnOff:
                return "已收拢";
            case TYPE_ErrorSta:
                return "故障";
            default:
                return "未知";
        }
    }
}
