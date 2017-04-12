package com.erayic.agr.common.net.back;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：头部图片Bean
 */

public class ImageHeadBean {

    private String ImgID;//图片ID
    private String PicturePath;//图片路径
    private String Thumbnail;//缩略图路径
    private int DisIndex;//索引
    private String UrlID;//跳转ID
    private int JumpType;//跳转类型

    public String getImgID() {
        return ImgID;
    }

    public void setImgID(String imgID) {
        ImgID = imgID;
    }

    public String getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public int getDisIndex() {
        return DisIndex;
    }

    public void setDisIndex(int disIndex) {
        DisIndex = disIndex;
    }

    public String getUrlID() {
        return UrlID;
    }

    public void setUrlID(String urlID) {
        UrlID = urlID;
    }

    public int getJumpType() {
        return JumpType;
    }

    public void setJumpType(int jumpType) {
        JumpType = jumpType;
    }
}
