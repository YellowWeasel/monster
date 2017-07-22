package com.erayic.agr.common.net.back.manage;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonProduceTypeBean {

    private int ClassifyID;//产品类别ID
    private String ClassifyName;//产品类别名称
    private int Type;//行业类型

    public int getClassifyID() {
        return ClassifyID;
    }

    public void setClassifyID(int classifyID) {
        ClassifyID = classifyID;
    }

    public String getClassifyName() {
        return ClassifyName;
    }

    public void setClassifyName(String classifyName) {
        ClassifyName = classifyName;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
