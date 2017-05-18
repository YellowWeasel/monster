package com.erayic.agr.serverproduct.presenter.impl;

import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsBean;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsDetailDatas;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsTitleDatas;
import com.erayic.agr.serverproduct.presenter.IPoliciesRegulationsPresenter;
import com.erayic.agr.serverproduct.view.IPoliciesRegulartionsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxk on 2017/5/11.
 */

public class PoliciesRegulationsPresenterImpl implements IPoliciesRegulationsPresenter {
    private IPoliciesRegulartionsView context;
    private IApiModel apiModel;

    public PoliciesRegulationsPresenterImpl(IPoliciesRegulartionsView context) {
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
                       List<PoliciesRegulationsTitleDatas> datas=new ArrayList<>();
                       for (CommonPoliciesRegulationsBean bean:response){
                           datas.add(new PoliciesRegulationsTitleDatas(bean));
                       }
                      context.loadMoreSure(datas);
                   }
               });
           }
           @Override
           public void fail(int errCode, String msg) {
                context.showToast("错误代码：" + errCode + "\n描述：" + msg);
                context.loadMoreFailure();
           }
       });
    }

    @Override
    public void initPoliciesRegulationsDatas(int pageSize) {
        context.openRefresh();
        apiModel.getPoliciesRegulations(1,pageSize,new OnDataListener<List<CommonPoliciesRegulationsBean>>() {
            @Override
            public void success(final List<CommonPoliciesRegulationsBean> response) {
                MainLooperManage.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        context.clearRefresh();
                        List<PoliciesRegulationsTitleDatas> datas=new ArrayList<>();
                        for (CommonPoliciesRegulationsBean bean:response){
                            datas.add(new PoliciesRegulationsTitleDatas(bean));
                        }
                        context.refreshPoliciesRegulartionsView(datas);
                    }
                });
            }
            @Override
            public void fail(int errCode, String msg) {
                context.clearRefresh();
                context.showToast("错误代码：" + errCode + "\n描述：" + msg);
                context.refreshPoliciesRegulartionsView(null);
            }
        });
    }

    @Override
    public List<PoliciesRegulationsTitleDatas> sortPoliciesRegulationsDatasByProvince(String province, List<PoliciesRegulationsTitleDatas> titleDatasList,int sort) {
        if (titleDatasList==null){
            context.showToast("无网络，请检查网络连接!");
            return null;
        }
        List<PoliciesRegulationsTitleDatas> datases=new ArrayList<>();
        switch (sort){
            case 0:
                datases=titleDatasList;
                break;
            case 1:
                for (PoliciesRegulationsTitleDatas titleDatas: titleDatasList){
                    if (titleDatas.getInfoSource().contains(province)){
                        datases.add(titleDatas);
                    }
                }
                break;
            case 2:
                for (PoliciesRegulationsTitleDatas titleDatas: titleDatasList){
                    if (!titleDatas.getInfoSource().contains(province)){
                        datases.add(titleDatas);
                    }
                }
                break;
        }
        return  datases;
    }
}
