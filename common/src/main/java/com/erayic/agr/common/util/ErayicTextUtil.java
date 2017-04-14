package com.erayic.agr.common.util;

import java.text.DecimalFormat;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ErayicTextUtil {

    /**
     * 禁止实例化
     */
    private ErayicTextUtil() {
        throw new UnsupportedOperationException("禁止实例化");
    }

    /**
     * 根据价格保留两位小数（返回String）
     */
    public static String getPrice(float price) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return decimalFormat.format(price);//format 返回的是字符串
    }

}
