package com.erayic.agr.jobs.bean;

import com.erayic.agr.common.net.back.img.CommonResultImage;
import com.yalantis.ucrop.entity.LocalMedia;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsLocalMedia {

    private boolean isUpload;//是否上传成功
    private String finalPath;//本地最终路径
    private LocalMedia localMedia;//本地信息
    private CommonResultImage resultImage;//上传信息

    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    public String getFinalPath() {
        return finalPath;
    }

    public void setFinalPath(String finalPath) {
        this.finalPath = finalPath;
    }

    public LocalMedia getLocalMedia() {
        return localMedia;
    }

    public void setLocalMedia(LocalMedia localMedia) {
        this.localMedia = localMedia;
    }

    public CommonResultImage getResultImage() {
        return resultImage;
    }

    public void setResultImage(CommonResultImage resultImage) {
        this.resultImage = resultImage;
    }
}
