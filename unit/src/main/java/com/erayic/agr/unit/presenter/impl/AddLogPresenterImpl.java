package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.CommonLocalMedia;
import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.img.CommonResultImage;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchSaveLogBean;
import com.erayic.agr.unit.presenter.IAddLogPresenter;
import com.erayic.agr.unit.view.IAddLogView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class AddLogPresenterImpl implements IAddLogPresenter {

    private IAddLogView logView;
    @Autowired
    IUnitModel unitModel;
    @Autowired
    IIndexModel indexModel;

    int surePosition = 0;//成功图片
    int failPosition = 0;//失败图片

    public AddLogPresenterImpl(IAddLogView logView){
        this.logView = logView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void submitImage(final List<CommonLocalMedia> selectMedia) {
        logView.showLoading();
        final int size = selectMedia.size();
        surePosition = 0;
        failPosition = 0;
        for (final CommonLocalMedia media : selectMedia) {
            if (!media.isUpload())
                indexModel.uploadImg(media.getFinalPath(), new OnDataListener<CommonResultImage>() {
                    @Override
                    public void success(CommonResultImage response) {
                        surePosition++;
                        media.setUpload(true);
                        media.setResultImage(response);
                        if (surePosition + failPosition == size) {
                            logView.dismissLoading();
                            logView.uploadImageResult(selectMedia);
//                        ErayicLog.e("上传图片：", "成功" + surePosition + "张，失败" + failPosition + "张");
//                        workModel.executeDayWork(schID,unitID,);
                        }
                    }

                    @Override
                    public void fail(int errCode, String msg) {
                        failPosition++;
//                        jobsInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                        media.setUpload(false);
                        if (surePosition + failPosition == size) {
                            logView.dismissLoading();
                            logView.uploadImageResult(selectMedia);
//                        ErayicLog.e("上传图片：", "成功" + surePosition + "张，失败" + failPosition + "张");
                        }
                    }
                });
            else
                surePosition++;
        }
        if (surePosition == selectMedia.size()){
            logView.dismissLoading();
            logView.uploadImageResult(selectMedia);
        }
    }

    @Override
    public void saveWorkLog(CommonUnitBatchSaveLogBean bean) {
        logView.showLoading();
        unitModel.saveWorkLog(bean, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                logView.dismissLoading();
                logView.addLogSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                logView.dismissLoading();
                logView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
