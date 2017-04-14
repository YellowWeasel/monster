package com.erayic.agr.common.net.back.enums;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class EnumServiceType {

    public static final int KERNEL = 0;//核心服务
    public static final int InfoService = 1;//信息服务
    public static final int Subject = 2;//主题服务
    public static final int Specify = 3;//特定作物服务
    public static final int All = 9;//全部

    public static String getTypeDes(int type) {
        switch (type) {
            case KERNEL:
                return "核心服务";
            case InfoService:
                return "信息服务";
            case Subject:
                return "主题服务";
            case Specify:
                return "特定作物服务";
            case All:
                return "全部";
            default:
                return "未定义";
        }
    }
}
