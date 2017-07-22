package com.erayic.agr.serverproduct.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;
import com.erayic.agr.common.net.back.api.CommonMarketDynamicPriceBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.serverproduct.adapter.entity.DesignatedMarketDatas;
import com.erayic.agr.serverproduct.adapter.entity.MarketDynamicPriceDatas;
import com.erayic.agr.serverproduct.presenter.IDynamicPriceDetailPresenter;
import com.erayic.agr.serverproduct.presenter.IDynamicPricePresenter;
import com.erayic.agr.serverproduct.view.IDynamicPriceDetailView;
import com.erayic.agr.serverproduct.view.IDynamicPriceView;

import java.util.List;

/**
 * Created by wxk on 2017/5/16.
 */

public class DynamicPriceDetailPresenterImpl implements IDynamicPriceDetailPresenter {
    IDynamicPriceDetailView context;
    @Autowired
    IApiModel apiModel;

    public DynamicPriceDetailPresenterImpl(IDynamicPriceDetailView context) {
        this.context = context;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getMarketDatas(int cropId, final String marketName, String start, String end, String serviceId) {
        context.showLoading();
        apiModel.getDesignatedMarketDynamicPrices(cropId, marketName, start, end, serviceId, new OnDataListener<List<CommonMarketDynamicPriceBean>>() {
            @Override
            public void success(final List<CommonMarketDynamicPriceBean> response) {
                MainLooperManage.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null)
                            context.refreshMarketDynamicPrices(new MarketDynamicPriceDatas(response));
                        else
                            context.showToast("未检测到数据");
                        context.dismissLoading();
                    }
                });
            }

            @Override
            public void fail(final int errCode, final String msg) {
                MainLooperManage.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        context.dismissLoading();
                        context.showToast("错误代码：" + errCode + "\n描述：" + msg);
                    }
                });
            }
        });
    }
}
