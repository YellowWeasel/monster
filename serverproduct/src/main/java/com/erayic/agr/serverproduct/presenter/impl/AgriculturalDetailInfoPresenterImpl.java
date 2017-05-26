package com.erayic.agr.serverproduct.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonAgriculturalInfoBean;
import com.erayic.agr.common.net.back.api.CommonAgriculturalinfoDetailBean;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalDetailInfoDatas;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalInfoDatas;
import com.erayic.agr.serverproduct.presenter.IAgriculturalDetailInfoPresenter;
import com.erayic.agr.serverproduct.presenter.IAgriculturalInfoPresenter;
import com.erayic.agr.serverproduct.view.IAgriculturalDetailInfoView;
import com.erayic.agr.serverproduct.view.IAgriculturalInfoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxk on 2017/5/19.
 */

public class AgriculturalDetailInfoPresenterImpl implements IAgriculturalDetailInfoPresenter {
    private IAgriculturalDetailInfoView context;
    @Autowired
    IApiModel apiModel;

    public AgriculturalDetailInfoPresenterImpl(IAgriculturalDetailInfoView mContext) {
        this.context = mContext;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getAgriculturalDetailInfo(int Id) {
        context.openRefresh();
        apiModel.getAgriculturalDetailInfos(Id, new OnDataListener<CommonAgriculturalinfoDetailBean>() {
            @Override
            public void success(final CommonAgriculturalinfoDetailBean response) {
                MainLooperManage.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<AgriculturalDetailInfoDatas> datasList=new ArrayList<>();
                        datasList.add(new AgriculturalDetailInfoDatas(response));
                        context.refreshAgriculturatlDetailInfoView(datasList);
                    }
                });
                context.clearRefresh();
            }

            @Override
            public void fail(int errCode, String msg) {
                context.clearRefresh();
                context.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
