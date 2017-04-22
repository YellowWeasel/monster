package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class EnumPayType {

    public static final int PAY_DEFAULT = -1;//初始化
    public static final int PAY_BANK = 0;//银行
    public static final int PAY_WEIXIN = 1;//微信
    public static final int PAY_ALPAY = 2;//支付宝

    public static String getStatueDes(int statue) {
        switch (statue) {
            case PAY_BANK:
                return "银行转账";
            case PAY_WEIXIN:
                return "微信支付 ";
            case PAY_ALPAY:
                return "支付宝支付";
            default:
                return "未知";
        }
    }

}
