package com.erayic.agr.common.net.back.enums;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class EnumBatchStatus {
    public static final int TYPE_Doing = 0;//进行中
    public static final int TYPE_Finished = 1;//已结束

    public static String getStatueDes(int statue) {
        switch (statue) {
            case TYPE_Doing:
                return "生长中";
            case TYPE_Finished:
                return "结束 ";
            default:
                return "未知";
        }
    }

}
