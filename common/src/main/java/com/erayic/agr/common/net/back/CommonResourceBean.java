package com.erayic.agr.common.net.back;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：生产资源bean
 */

public class CommonResourceBean {

    private String ResID;//资料ID
    private String Name;//资料名称
    private int Type;//资料类型（1：农药，2：肥料，3：饲料，4：种子）
    private String CommonDesc;//

    public String getResID() {
        return ResID;
    }

    public void setResID(String resID) {
        ResID = resID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getCommonDesc() {
        return CommonDesc;
    }

    public void setCommonDesc(String commonDesc) {
        CommonDesc = commonDesc;
    }
}
