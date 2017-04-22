package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonSubServiceBean;
import com.erayic.agr.service.presenter.IServiceTopicByEntPresenter;
import com.erayic.agr.service.view.IServiceTopicView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceTopicByEntPresenterImpl implements IServiceTopicByEntPresenter {

    private IServiceTopicView topicView;
    @Autowired
    IServerModel serverModel;


    public ServiceTopicByEntPresenterImpl(IServiceTopicView topicView) {
        this.topicView = topicView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getRderDetailBySubInfo(String subServiceID) {
        topicView.openRefresh();
        serverModel.getRderDetailBySubInfo(subServiceID, new OnDataListener<List<CommonSubServiceBean>>() {
            @Override
            public void success(List<CommonSubServiceBean> response) {
                topicView.clearRefresh();
                topicView.refreshServiceView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                topicView.clearRefresh();
                topicView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                topicView.refreshServiceView(null);
            }
        });
    }
}
