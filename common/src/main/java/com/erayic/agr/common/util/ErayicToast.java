package com.erayic.agr.common.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：Toast
 */
public class ErayicToast {

    /**
     * 禁止实例化
     */
    private ErayicToast() {
        throw new UnsupportedOperationException("禁止实例化");
    }

    public static boolean isShow = true;//是否需要显示Toast，可以在application的onCreate函数里面初始化

    private static Toast toast = null;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;
    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;

    public static final int CENTER = Gravity.CENTER;
    public static final int TOP = Gravity.TOP;
    public static final int BOTTOM = Gravity.BOTTOM;

    /**
     * 文本Toast
     *
     * @param context Context
     * @param message 消息 类型:CharSequence
     */
    public static void TextToast(Context context, CharSequence message) {
        TextToast(context, message, LENGTH_SHORT, BOTTOM);
    }

    /**
     * 文本Toast
     *
     * @param context  Context
     * @param message  消息 类型:CharSequence
     * @param duration 持续时间  LENGTH_LONG(长)或者LENGTH_SHORT(短)
     */
    public static void TextToast(Context context, CharSequence message, int duration) {
        TextToast(context, message, duration, BOTTOM);
    }

    /**
     * 文本Toast
     *
     * @param context  Context
     * @param message  消息 类型:CharSequence
     * @param duration 持续时间  LENGTH_LONG(长)、LENGTH_SHORT(短)
     * @param position 位置 CENTER(居中)、TOP(顶部)、BOTTOM(底部) 默认BOTTOM
     */
    public static void TextToast(Context context, CharSequence message, int duration, int position) {
        int yOffset;//Y偏移量
        switch (position) {
            case BOTTOM:
                yOffset = 200;
                break;
            case TOP:
                yOffset = 200;
                break;
            default:
                yOffset = 0;
                break;
        }
        if (isShow) {
            if (toast != null) {//避免多次造成Toast延时现象的出现
                toast.cancel();
            }
            toast = Toast.makeText(context, message, duration);
            toast.setGravity(position, 0, yOffset);
            toast.show();
        }
    }
}
