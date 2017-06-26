package com.erayic.agr.common.net.back.base;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：基础图片库实体
 */

public class CommonImagesEntity {

    private String BelongID;//ID
    private int BelongType;//类型
    private String ImgPath = "";//原图地址
    private String Thumbnail = "";//缩略图地址

    public String getBelongID() {
        return BelongID;
    }

    public void setBelongID(String belongID) {
        BelongID = belongID;
    }

    public int getBelongType() {
        return BelongType;
    }

    public void setBelongType(int belongType) {
        BelongType = belongType;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
}
