package com.erayic.agr.serverproduct.presenter.impl;

import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsBean;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsDetailDatas;
import com.erayic.agr.serverproduct.presenter.IPoliciesRegulationsPresenter;
import com.erayic.agr.serverproduct.view.IPoliciesRegulartionsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxk on 2017/5/11.
 */

public class PoliciesRegulationsPresenter implements IPoliciesRegulationsPresenter {
    private IPoliciesRegulartionsView context;
    private IApiModel apiModel;

    public PoliciesRegulationsPresenter(IPoliciesRegulartionsView context) {
        this.context = context;
        apiModel=new ApiModelImpl();
    }

    @Override
    public void getPoliciesRegulationsDatas(int pageIndex,int pageSize) {
       apiModel.getPoliciesRegulations(pageIndex,pageSize,new OnDataListener<List<CommonPoliciesRegulationsBean>>() {
           @Override
           public void success(final List<CommonPoliciesRegulationsBean> response) {
               MainLooperManage.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       List<PoliciesRegulationsDetailDatas> datas=new ArrayList<PoliciesRegulationsDetailDatas>();
                       for (CommonPoliciesRegulationsBean bean:response){
                           datas.add(new PoliciesRegulationsDetailDatas(bean));
                       }
                       context.refreshPoliciesRegulartionsDataView(datas);
                   }
               });
           }
           @Override
           public void fail(int errCode, String msg) {

           }
       });
    }
}
