package com.erayic.agr.unit.presenter;

import com.erayic.agr.common.base.CommonLocalMedia;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchSaveLogBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IAddLogPresenter {

    /**
     * 上传图片
     */
    void submitImage(List<CommonLocalMedia> selectMedia);

    /**
     * 保存一个工作日志
     */
    void saveWorkLog(CommonUnitBatchSaveLogBean bean);

}
