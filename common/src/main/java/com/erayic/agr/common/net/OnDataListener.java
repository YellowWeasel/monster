package com.erayic.agr.common.net;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：数据返回统一接口
 */

public interface OnDataListener<T> {

    void success(T response); // 网络操作成功

    void fail(String msg); // 网络操作失败
}
