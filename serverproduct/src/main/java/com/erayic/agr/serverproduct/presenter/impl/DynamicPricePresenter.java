package com.erayic.agr.serverproduct.presenter.impl;

import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;
import com.erayic.agr.serverproduct.presenter.IDynamicPricePresenter;
import com.erayic.agr.serverproduct.view.IDynamicPriceView;

import java.util.List;

/**
 * Created by 23060 on 2017/5/11.
 */

public class DynamicPricePresenter implements IDynamicPricePresenter {
    IDynamicPriceView context;
    IApiModel apiModel;
    public DynamicPricePresenter(IDynamicPriceView context) {
        this.context=context;
        apiModel=new ApiModelImpl();
    }

    @Override
    public void getDynamicPricePresenter(int cropId,String start,String end) {
         apiModel.getDynamicPrice(cropId, start, end, new OnDataListener<CommonDynamicPriceBean>() {
             @Override
             public void success(CommonDynamicPriceBean response) {
                 context.refreshDynamicPrice(response);
             }

             @Override
             public void fail(int errCode, String msg) {
                 context.showToast(msg);
             }
         });
    }
}
