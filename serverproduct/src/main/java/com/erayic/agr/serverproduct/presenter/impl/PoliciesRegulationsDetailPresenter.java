package com.erayic.agr.serverproduct.presenter.impl;

import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.serverproduct.presenter.IPoliciesRegulationsDetailPresenter;
import com.erayic.agr.serverproduct.presenter.IPoliciesRegulationsPresenter;
import com.erayic.agr.serverproduct.view.IPoliciesRegulationDetailView;

/**
 * Created by 23060 on 2017/5/12.
 */

public class PoliciesRegulationsDetailPresenter implements IPoliciesRegulationsDetailPresenter {
    private IPoliciesRegulationDetailView context;
    private IApiModel apiModel;
    public PoliciesRegulationsDetailPresenter(IPoliciesRegulationDetailView context) {
        this.context = context;
        apiModel=new ApiModelImpl();
    }

    @Override
    public void getPoliciesRegulationDetailDatas(int Id) {

    }
}
