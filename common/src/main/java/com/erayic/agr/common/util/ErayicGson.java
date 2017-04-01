package com.erayic.agr.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：Gson 泛型封装
 */

public class ErayicGson {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 禁止实例化
     */
    private ErayicGson() {
        throw new UnsupportedOperationException("禁止实例化");
    }

    /**
     * 将传入的json字符串按类模板解析成对象
     *
     * @param json 需要解析的json字符串
     * @param cls  cls 类模板
     * @param <T>
     * @return Bean
     */
    public static <T> T GsonToBean(String json, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, cls);
        }
        return t;
    }

    /**
     * 将传入的json字符串按类模板解析成List
     *
     * @param json 需要解析的json字符串
     * @param cls  cls 类模板
     * @param <T>
     * @return List<Bean>
     */
    public static <T> List<T> GsonToList(String json, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 将传入的json字符串按类模板解析成Map
     *
     * @param json 需要解析的json字符串
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String json) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 将传入的json字符串按类模板解析成List (List中有Map)
     *
     * @param json 需要解析的json字符串
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String json) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(json,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }


    /**
     * 将传入的对象构成成json字符串
     *
     * @param object 构成对象
     * @return 构成完成的json字符串
     */
    public static String getJsonString(Object object) {
        String json = null;
        if (gson != null) {
            json = gson.toJson(object);
        }
        return json;
    }
}
