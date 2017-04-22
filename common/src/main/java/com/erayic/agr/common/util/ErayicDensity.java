package com.erayic.agr.common.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：单位转换工具类
 */
public class ErayicDensity {

    /**
     * 禁止实例化
     */
    private ErayicDensity() {
        throw new UnsupportedOperationException("禁止实例化");
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpValue
     * @return px 类型:int
     */
    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spValue
     * @return px 类型:int
     */
    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxValue
     * @return dp 类型:int
     */
    public static float px2dp(Context context, int pxValue) {
        return (pxValue / context.getResources().getDisplayMetrics().density);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxValue
     * @return sp 类型:int
     */
    public static float px2sp(Context context, float pxValue) {
        return (pxValue / context.getResources().getDisplayMetrics().scaledDensity);
    }

}
