package com.erayic.agr.common.config;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
public class PreferenceUtils {
    private static Preference PREFERENCE;

    private PreferenceUtils() {
    }

    /**
     * 只初始化一次就可以了，建议在 baseApplication 初始化
     *
     * @param baseApplicationContent c
     */
    public static void initialize(Context baseApplicationContent) {
        initialize(baseApplicationContent, getDefaultSharedPreferencesName(baseApplicationContent));
    }

    /**
     * 只初始化一次就可以了，建议在 baseApplication 初始化
     *
     * @param baseApplicationContent c
     */
    public static void initialize(Context baseApplicationContent, String defaultName) {
        if (PREFERENCE == null) {
            PREFERENCE = new Preference(baseApplicationContent, defaultName);
        }
    }

    private static String getDefaultSharedPreferencesName(Context context) {
        return context.getPackageName() + "_preferences";
    }

    public static boolean putParam(String key, Object value) {
        if (PREFERENCE == null) {
            throw new NullPointerException("not initialize ---  PreferenceUtils.initialize(context, Constants.SP_NAME);");
        }
        return PREFERENCE.putParam(key, value);
    }

    public static void putParam(Map<String, Object> params) {
        if (PREFERENCE == null) {
            throw new NullPointerException("not initialize ---  PreferenceUtils.initialize(context, Constants.SP_NAME);");
        }
        PREFERENCE.putParam(params);
    }

    public static String getParam(String key) {
        return PREFERENCE.getParam(key);
    }

    public static String getParam(String key, String defValue) {
        return PREFERENCE.getParam(key, defValue);
    }

    public static int getParam(String key, int defValue) {
        return PREFERENCE.getParam(key, defValue);
    }

    public static boolean getParam(String key, boolean defValue) {
        return PREFERENCE.getParam(key, defValue);
    }

    public static float getParam(String key, float defValue) {
        return PREFERENCE.getParam(key, defValue);
    }

    public static long getParam(String key, long defValue) {
        return PREFERENCE.getParam(key, defValue);
    }

    /**
     * 删除 key --value
     *
     * @param key
     */
    public static void remove(String key) {
        PREFERENCE.remove(key);
    }

    public static boolean clearData() {
        return PREFERENCE.clearData();
    }

    /**
     * 删除 sharedPreferences 文件
     *
     * @return
     */
    public boolean clearData(Context context, String spf_name) {
        SharedPreferences spf = context.getSharedPreferences(spf_name, Context.MODE_PRIVATE);
        return spf.edit().clear().commit();
    }
}
