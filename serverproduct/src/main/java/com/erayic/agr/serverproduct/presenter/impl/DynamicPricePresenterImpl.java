package com.erayic.agr.serverproduct.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;
import com.erayic.agr.serverproduct.presenter.IDynamicPricePresenter;
import com.erayic.agr.serverproduct.view.IDynamicPriceView;

import java.util.List;

/**
 * Created by wxk on 2017/5/11.
 */

public class DynamicPricePresenterImpl implements IDynamicPricePresenter {
    IDynamicPriceView context;
    @Autowired
    IApiModel apiModel;

    public DynamicPricePresenterImpl(IDynamicPriceView context) {
        this.context = context;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getDynamicPricedatas(int cropId, String start, String end,String serviceId) {
        context.showLoading();
        apiModel.getDynamicPrice(cropId, start, end,serviceId, new OnDataListener<CommonDynamicPriceBean>() {
            @Override
            public void success(CommonDynamicPriceBean response) {
                context.refreshDynamicPrice(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                context.dismissLoading();
                context.showToast(msg);
            }
        });
    }
}
