package com.erayic.agr.common.net;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：数据返回统一接口
 */

public interface OnDataListener<T> {

    void success(T response); // 获取数据成功

    void fail(int errCode,String msg); // 获取数据失败
}
