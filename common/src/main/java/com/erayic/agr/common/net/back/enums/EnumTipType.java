package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：通知方式
 */

public class EnumTipType {

    public static final int TYPE_NoNotice = 0;//不通知
    public static final int TYPE_Syetem = 1;//系统APP推送
    public static final int TYPE_SMS = 3;//短信推送
    public static final int TYPE_Telphone = 5;//电话推送

    public static final int TYPE_Syetem_SMS = 4;//系统短信推送
    public static final int TYPE_Syetem_Telphone = 6;//系统电话推送
    public static final int TYPE_SMS_Telphone = 8;//短信电话推送
    public static final int TYPE_Syetem_SMS_Telphone = 9;//系统短信电话推送

    public static String getTypeDes(int type) {
        switch (type) {
            case TYPE_NoNotice:
                return "不通知";
            case TYPE_Syetem:
                return "系统";
            case TYPE_SMS:
                return "短信";
            case TYPE_Telphone:
                return "电话";
            case TYPE_Syetem_SMS:
                return "系统 短信";
            case TYPE_Syetem_Telphone:
                return "系统 电话";
            case TYPE_SMS_Telphone:
                return "短信 电话";
            case TYPE_Syetem_SMS_Telphone:
                return "系统 短信 电话";
            default:
                return "未知";
        }
    }

}
