package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class EnumOrderType {

    public final static int ORDER_SUCCESS = 0;//订购成功
    public final static int ORDER_UNPAY = 1;//未支付
    public final static int ORDER_TRY = 3;//试用
    public final static int ORDER_FREE = 4;//免费
    public final static int ORDER_UpdateFailure = 5;//更新失效
    public final static int ORDER_Cancel = 8;//已取消
    public final static int ORDER_Failure = 9;//已失效

    public static String getStatueDes(int statue) {
        switch (statue) {
            case ORDER_SUCCESS:
                return "已完成";
            case ORDER_UNPAY:
                return "待支付";
            case ORDER_TRY:
                return "试用";
            case ORDER_FREE:
                return "免费";
            case ORDER_UpdateFailure:
                return "更新失效";
            case ORDER_Cancel:
                return "已取消";
            case ORDER_Failure:
                return "已失效";
            default:
                return "未知";
        }
    }

}
