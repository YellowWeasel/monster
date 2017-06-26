package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class EnumBatchByBindType {

    public static final int TYPE_Adaptability = 1;//生长适应性评价服务
    public static final int TYPE_cycle = 2;//生长周期预测服务
    public static final int TYPE_produce = 3;//产量预测服务
    public static final int TYPE_price = 4;//市场价格服务

    public static String getStatueDes(int statue) {
        switch (statue) {
            case TYPE_Adaptability:
                return "生长适应性评价服务";
            case TYPE_cycle:
                return "生长周期预测服务 ";
            case TYPE_produce:
                return "产量预测服务";
            case TYPE_price:
                return "市场价格服务";
            default:
                return "未知";
        }
    }


}
