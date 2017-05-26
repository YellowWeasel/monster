package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.model.IUserModel;
import com.erayic.agr.common.model.IWorkModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.common.net.back.CommonResourceBean;
import com.erayic.agr.common.net.back.enums.EnumTipType;
import com.erayic.agr.common.net.back.enums.EnumUnitType;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListByBaseBean;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;
import com.erayic.agr.manage.adapter.entity.ManageNoticeEntity;
import com.erayic.agr.manage.presenter.ISelectPresenter;
import com.erayic.agr.manage.view.ISelectView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class SelectPresenterImpl implements ISelectPresenter {

    private ISelectView selectProduceView;
    @Autowired
    IResourceModel resourceModel;
    @Autowired
    IUserModel userModel;
    @Autowired
    IWorkModel workModel;
    @Autowired
    IUnitModel unitModel;

    public SelectPresenterImpl(ISelectView selectProduceView) {
        this.selectProduceView = selectProduceView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getAllProduct() {
        selectProduceView.openRefresh();
        resourceModel.getAllProduct(new OnDataListener<List<CommonProduceListBean>>() {
            @Override
            public void success(List<CommonProduceListBean> response) {
                selectProduceView.clearRefresh();
                selectProduceView.refreshProductView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                selectProduceView.clearRefresh();
                selectProduceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getResourceByType(int type) {
        selectProduceView.openRefresh();
        resourceModel.getResourceByType(type, new OnDataListener<List<CommonResourceBean>>() {
            @Override
            public void success(List<CommonResourceBean> response) {
                selectProduceView.clearRefresh();
                selectProduceView.refreshResourceView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                selectProduceView.clearRefresh();
                selectProduceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });

    }

    @Override
    public void getAllUserByBase(String baseID) {
        selectProduceView.openRefresh();
        userModel.getAllUserBySpecifyBase(baseID, new OnDataListener<List<CommonPersonnelBean>>() {
            @Override
            public void success(List<CommonPersonnelBean> response) {
                selectProduceView.clearRefresh();
                selectProduceView.refreshUserView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                selectProduceView.clearRefresh();
                selectProduceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getJobList() {
        selectProduceView.openRefresh();
        workModel.getJobList(new OnDataListener<List<CommonWorkListBean>>() {
            @Override
            public void success(List<CommonWorkListBean> response) {
                selectProduceView.clearRefresh();
                selectProduceView.refreshWorkView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                selectProduceView.clearRefresh();
                selectProduceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getUnitList() {
        selectProduceView.openRefresh();
        unitModel.getAllUnitByBase(EnumUnitType.TYPE_PLOTS, new OnDataListener<List<CommonUnitListByBaseBean>>() {
            @Override
            public void success(List<CommonUnitListByBaseBean> response) {
                selectProduceView.clearRefresh();
                selectProduceView.refreshUnitView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                selectProduceView.clearRefresh();
                selectProduceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getNoticeList() {
        List<ManageNoticeEntity> list = new ArrayList<>();
        //不通知
        {
            ManageNoticeEntity entity = new ManageNoticeEntity();
            entity.setType(EnumTipType.TYPE_NoNotice);
            entity.setName(EnumTipType.getTypeDes(entity.getType()));
            list.add(entity);
        }

        //系统
        {
            ManageNoticeEntity entity = new ManageNoticeEntity();
            entity.setType(EnumTipType.TYPE_Syetem);
            entity.setName(EnumTipType.getTypeDes(entity.getType()));
            list.add(entity);
        }

        //短信
        {
            ManageNoticeEntity entity = new ManageNoticeEntity();
            entity.setType(EnumTipType.TYPE_SMS);
            entity.setName(EnumTipType.getTypeDes(entity.getType()));
            list.add(entity);
        }

        //电话
        {
            ManageNoticeEntity entity = new ManageNoticeEntity();
            entity.setType(EnumTipType.TYPE_Telphone);
            entity.setName(EnumTipType.getTypeDes(entity.getType()));
            list.add(entity);
        }

        selectProduceView.refreshNoticeView(list);
    }
}
