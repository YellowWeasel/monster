package com.erayic.agr.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：解析C#传过来的时间 /Date(1488124800000)/
 */

public class ErayicNetDate {

    private static Calendar calendar = Calendar.getInstance();

    /**
     SimpleDateFormat函数语法：

     G 年代标志符
     y 年
     M 月
     d 日
     h 时 在上午或下午 (1~12)
     H 时 在一天中 (0~23)
     m 分
     s 秒
     S 毫秒
     E 星期
     D 一年中的第几天
     F 一月中第几个星期几
     w 一年中第几个星期
     W 一月中第几个星期
     a 上午 / 下午 标记符
     k 时 在一天中 (1~24)
     K 时 在上午或下午 (0~11)
     z 时区
     */

    /**
     * 将.net服务器的时间转为Long
     */
    public static long getLongDates(String netDate) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(netDate);
        return Long.parseLong(m.replaceAll("").trim());
    }

    /**
     * 将服务器时间/Date(XXXXXXXXXXX)/转换为年月日时分秒
     */
    public static String getStringDates(String arg) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(arg);

        calendar.setTimeInMillis(Long.parseLong(m.replaceAll("").trim()));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(calendar.getTimeInMillis());
    }

    /**
     * 将服务器时间/Date(XXXXXXXXXXX)/转换为年月日
     */
    public static String getStringDate(String arg) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(arg);

        calendar.setTimeInMillis(Long.parseLong(m.replaceAll("").trim()));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(calendar.getTimeInMillis());
    }

    /**
     * 将服务器时间/Date(XXXXXXXXXXX)/转换为年月日时分
     */
    public static String getStringDateYMDHM(String arg) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(arg);

        calendar.setTimeInMillis(Long.parseLong(m.replaceAll("").trim()));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return format.format(calendar.getTimeInMillis());
    }

    /**
     * 将服务器时间/Date(XXXXXXXXXXX)/转换为字符串(时分秒)
     */
    public static String getStringDateHms(String arg) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(arg);

        calendar.setTimeInMillis(Long.parseLong(m.replaceAll("").trim()));

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);

        return format.format(calendar.getTimeInMillis());
    }

    /**
     * 将服务器时间/Date(XXXXXXXXXXX)/转换为字符串(时分秒)并减去一个小时
     */
    public static String getStringDateHmsRollOneH(String arg) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(arg);

        calendar.setTimeInMillis(Long.parseLong(m.replaceAll("").trim()));
        calendar.add(Calendar.MINUTE, -60);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);

        return format.format(calendar.getTimeInMillis());
    }

    /**
     * 将服务器时间/Date(XXXXXXXXXXX)/转换为字符串(只带小时)
     */
    public static String getStringDateHour(String arg) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(arg);

        calendar.setTimeInMillis(Long.parseLong(m.replaceAll("").trim()));

        SimpleDateFormat format = new SimpleDateFormat("HH", Locale.CHINA);
        return format.format(calendar.getTimeInMillis());
    }

    /**
     * 将服务器时间/Date(XXXXXXXXXXX)/转换为字符串(只带天)
     */
    public static String getStringDateDay(String arg) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(arg);

        calendar.setTimeInMillis(Long.parseLong(m.replaceAll("").trim()));

        SimpleDateFormat format = new SimpleDateFormat("dd", Locale.CHINA);
        return format.format(calendar.getTimeInMillis());
    }

    /**
     * 将服务器时间/Date(XXXXXXXXXXX)/转换为字符串(只带月)
     */
    public static String getStringDateMonth(String arg) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(arg);

        calendar.setTimeInMillis(Long.parseLong(m.replaceAll("").trim()));

        SimpleDateFormat format = new SimpleDateFormat("MM", Locale.CHINA);
        return format.format(calendar.getTimeInMillis());
    }

}
