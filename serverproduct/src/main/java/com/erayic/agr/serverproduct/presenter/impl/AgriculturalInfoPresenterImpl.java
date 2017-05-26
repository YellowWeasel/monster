package com.erayic.agr.serverproduct.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonAgriculturalInfoBean;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalInfoDatas;
import com.erayic.agr.serverproduct.presenter.IAgriculturalInfoPresenter;
import com.erayic.agr.serverproduct.view.IAgriculturalInfoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 23060 on 2017/5/19.
 */

public class AgriculturalInfoPresenterImpl implements IAgriculturalInfoPresenter {
    private IAgriculturalInfoView context;
    @Autowired
    IApiModel apiModel;

    public AgriculturalInfoPresenterImpl(IAgriculturalInfoView mContext) {
        this.context = mContext;
        ARouter.getInstance().inject(this);
    }
    @Override
    public void getAgriculturalInfo(int pageIndex, int pageSize) {
            apiModel.getAgriculturalInfos(pageIndex, pageSize, new OnDataListener<List<CommonAgriculturalInfoBean>>() {
                @Override
                public void success(final List<CommonAgriculturalInfoBean> response) {
                    MainLooperManage.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<AgriculturalInfoDatas> infoDatases=new ArrayList<AgriculturalInfoDatas>();
                            for (CommonAgriculturalInfoBean bean:response){
                                infoDatases.add(new AgriculturalInfoDatas(bean));
                            }
                            context.refreshAgriculturatlInfoView(infoDatases);
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

    @Override
    public void initAgriculturalInfo(int pageSize) {
        context.openRefresh();
        apiModel.getAgriculturalInfos(1, pageSize, new OnDataListener<List<CommonAgriculturalInfoBean>>() {
            @Override
            public void success(final List<CommonAgriculturalInfoBean> response) {
                MainLooperManage.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<AgriculturalInfoDatas> infoDatases=new ArrayList<AgriculturalInfoDatas>();
                        for (CommonAgriculturalInfoBean bean:response){
                            infoDatases.add(new AgriculturalInfoDatas(bean));
                        }
                        context.refreshAgriculturatlInfoView(infoDatases);
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
