package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.CommonLocalMedia;
import com.erayic.agr.common.base.IBaseView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IAddLogView extends IBaseView {

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 上传图片成功
     */
    void uploadImageResult(List<CommonLocalMedia> selectMedia);

    /**
     * 增加日志成功
     */
    void addLogSure();

}
