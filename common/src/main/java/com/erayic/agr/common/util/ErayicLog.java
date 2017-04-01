package com.erayic.agr.common.util;

import android.util.Log;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：Log统一管理工具类
 */

public class ErayicLog {

    /**
     * 禁止实例化
     */
    private ErayicLog() {
        throw new UnsupportedOperationException("禁止实例化");
    }

    private static boolean idDebug = true;//是否需要打印Bug，可以在application的onCreate函数里面初始化
    public static final String WHOLE_LOG_NAME = "hkceey";//全局日志打印名称

    /*自定义Tag的函数*/

    /**
     * Log.i的输出为绿色，一般提示性的消息information
     *
     * @param tag Log标签
     * @param msg 消息
     */
    public static void i(String tag, Object msg) {
        if (idDebug)
            Log.i(tag, msg.toString());
    }

    /**
     * Log.d的输出颜色是蓝色的，仅输出debug调试的意思
     *
     * @param tag Log标签
     * @param msg 消息
     */
    public static void d(String tag, Object msg) {
        if (idDebug)
            Log.d(tag, msg.toString());
    }

    /**
     * Log.e输出颜色是红色，一般错误性的消息
     *
     * @param tag Log标签
     * @param msg 消息
     */
    public static void e(String tag, Object msg) {
        if (idDebug)
            Log.e(tag, msg.toString());
    }

    /**
     * Log.v 的调试颜色为黑色的，任何消息都会输出
     *
     * @param tag Log标签
     * @param msg 消息
     */
    public static void v(String tag, Object msg) {
        if (idDebug)
            Log.v(tag, msg.toString());
    }

    /**
     * Log.w的意思为橙色，可以看作为warning警告
     *
     * @param tag Log标签
     * @param msg 消息
     */
    public static void w(String tag, Object msg) {
        if (idDebug)
            Log.w(tag, msg.toString());
    }


    /*默认Tag的函数*/

    /**
     * Log.i的输出为绿色，一般提示性的消息information
     *
     * @param msg 消息
     */
    public static void i(Object msg) {
        if (idDebug)
            Log.i(WHOLE_LOG_NAME, msg.toString());
    }

    /**
     * Log.d的输出颜色是蓝色的，仅输出debug调试的意思
     *
     * @param msg 消息
     */
    public static void d(Object msg) {
        if (idDebug)
            Log.d(WHOLE_LOG_NAME, msg.toString());
    }

    /**
     * Log.e输出颜色是红色，一般错误性的消息
     *
     * @param msg 消息
     */
    public static void e(Object msg) {
        if (idDebug)
            Log.e(WHOLE_LOG_NAME, msg.toString());
    }

    /**
     * Log.v 的调试颜色为黑色的，任何消息都会输出
     *
     * @param msg 消息
     */
    public static void v(Object msg) {
        if (idDebug)
            Log.v(WHOLE_LOG_NAME, msg.toString());
    }

    /**
     * Log.w的意思为橙色，可以看作为warning警告
     *
     * @param msg 消息
     */
    public static void w(Object msg) {
        if (idDebug)
            Log.w(WHOLE_LOG_NAME, msg.toString());
    }

}
