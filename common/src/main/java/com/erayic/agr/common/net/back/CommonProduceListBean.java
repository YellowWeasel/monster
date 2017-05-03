package com.erayic.agr.common.net.back;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonProduceListBean {

    private String ProID;//产品ID
    private String Icon;//产品图片
    private String ProductName;//产品名称
    private String ClassifyName;//产品类别

    public String getProID() {
        return ProID;
    }

    public void setProID(String proID) {
        ProID = proID;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getClassifyName() {
        return ClassifyName;
    }

    public void setClassifyName(String classifyName) {
        ClassifyName = classifyName;
    }
}
