package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBindServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBuyServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchServiceBean;
import com.erayic.agr.unit.presenter.IBatchBindServicePresenter;
import com.erayic.agr.unit.view.IBatchBindServiceView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BatchBindServicePresenterImpl implements IBatchBindServicePresenter {

    private IBatchBindServiceView bindServiceView;
    @Autowired
    IUnitModel unitModel;

    public BatchBindServicePresenterImpl(IBatchBindServiceView bindServiceView) {
        this.bindServiceView = bindServiceView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void bindServiceByBatch(String unitID,int unitType,String batchID, int serType, String serviceID) {
        bindServiceView.showLoading();
        unitModel.bindServiceByBatch(unitID,unitType,batchID, serType, serviceID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                bindServiceView.dismissLoading();
                bindServiceView.showToast("已绑定");
                bindServiceView.saveSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                bindServiceView.dismissLoading();
                bindServiceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getSingleSeviceList(int serType) {
        bindServiceView.showLoading();
        unitModel.getSingleSeviceList(serType, new OnDataListener<List<CommonUnitBatchServiceBean>>() {
            @Override
            public void success(List<CommonUnitBatchServiceBean> response) {
                bindServiceView.dismissLoading();
                bindServiceView.refreshServiceListView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                bindServiceView.dismissLoading();
                bindServiceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getBindService(String batchID, int serType) {
        bindServiceView.showLoading();
        unitModel.getBindService(batchID, serType, new OnDataListener<CommonUnitBatchBindServiceBean>() {
            @Override
            public void success(CommonUnitBatchBindServiceBean response) {
                bindServiceView.dismissLoading();
                bindServiceView.refreshServiceBindView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                bindServiceView.dismissLoading();
                bindServiceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                bindServiceView.saveSure();//关闭页面
            }
        });
    }

    @Override
    public void cancelServieBind(String batchID, int serType) {
        bindServiceView.showLoading();
        unitModel.cancelServieBind(batchID, serType, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                bindServiceView.dismissLoading();
                bindServiceView.showToast("已取消");
                bindServiceView.saveSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                bindServiceView.dismissLoading();
                bindServiceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getBindServiceOfSubject(int serType) {
        bindServiceView.showLoading();
        unitModel.getBindServiceOfSubject(serType, new OnDataListener<CommonUnitBatchBuyServiceBean>() {
            @Override
            public void success(CommonUnitBatchBuyServiceBean response) {
                bindServiceView.dismissLoading();
                bindServiceView.toServiceBuy(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                bindServiceView.dismissLoading();
                bindServiceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
