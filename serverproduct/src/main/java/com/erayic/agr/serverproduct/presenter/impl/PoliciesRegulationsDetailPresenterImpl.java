package com.erayic.agr.serverproduct.presenter.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsDetailBean;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsDetailDatas;
import com.erayic.agr.serverproduct.presenter.IPoliciesRegulationsDetailPresenter;
import com.erayic.agr.serverproduct.presenter.IPoliciesRegulationsPresenter;
import com.erayic.agr.serverproduct.view.IPoliciesRegulationDetailView;

/**
 * Created by 23060 on 2017/5/12.
 */

public class PoliciesRegulationsDetailPresenterImpl implements IPoliciesRegulationsDetailPresenter {
    private IPoliciesRegulationDetailView context;
    @Autowired
    IApiModel apiModel;

    public PoliciesRegulationsDetailPresenterImpl(IPoliciesRegulationDetailView context) {
        this.context = context;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getPoliciesRegulationDetailDatas(int Id) {
        context.showLoading();
        apiModel.getPoliciesRegulationsDetail(Id, new OnDataListener<CommonPoliciesRegulationsDetailBean>() {
            @Override
            public void success(final CommonPoliciesRegulationsDetailBean response) {
                MainLooperManage.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null)
                            context.refreshPoliciesRegulationDatas(new PoliciesRegulationsDetailDatas(response));
                        else
                            context.showToast("未检测到数据");
                        context.dismissLoading();
                    }
                });
            }

            @Override
            public void fail(int errCode, String msg) {
                context.showToast("错误代码：" + errCode + "\n描述：" + msg);
                context.dismissLoading();
            }
        });
    }
}
