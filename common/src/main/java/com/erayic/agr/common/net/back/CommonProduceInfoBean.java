package com.erayic.agr.common.net.back;

import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.base.CommonImagesEntity;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonProduceInfoBean {

    private String ProID = AgrConstant.GUID_Empty;//产品ID
    private String ProductName;//产品名称
    private int ClassifyID;//产品类别
    private String ClassifyName;//产品类别名称
    private String Descript;//产品描述
    private String Icon;//产品图片
    private int Status;//产品状态
    private List<CommonImagesEntity> Photos;//产品图片集

    public String getProID() {
        return ProID;
    }

    public void setProID(String proID) {
        ProID = proID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

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

    public String getDescript() {
        return Descript;
    }

    public void setDescript(String descript) {
        Descript = descript;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public List<CommonImagesEntity> getPhotos() {
        return Photos;
    }

    public void setPhotos(List<CommonImagesEntity> photos) {
        Photos = photos;
    }
}
