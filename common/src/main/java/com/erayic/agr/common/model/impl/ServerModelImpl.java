package com.erayic.agr.common.model.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;
import com.erayic.agr.common.net.http.manager.HttpServerManager;
import com.erayic.agr.common.util.ErayicGson;
import com.erayic.agr.common.util.ErayicLog;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/common/model/server", name = "服务相关数据接口实现")
public class ServerModelImpl implements IServerModel {

    @SuppressWarnings("unchecked")
    @Override
    public void getAllServiceByUser(final OnDataListener<List<ServiceBuyByUserBean>> listener) {
        HttpServerManager.getInstance().getAllServiceByUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<List<ServiceBuyByUserBean>>>() {
                    @Override
                    public void call(DataBack<List<ServiceBuyByUserBean>> objectDataBack) {
                        ErayicLog.i("getAllServiceByUser", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {

                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<List<ServiceBuyByUserBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<List<ServiceBuyByUserBean>> objectDataBack) {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getAllSystemServiceByEnt(final OnDataListener<ServiceSystemBean> listener) {
        HttpServerManager.getInstance().getAllSystemServiceByEnt()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<ServiceSystemBean>>() {
                    @Override
                    public void call(DataBack<ServiceSystemBean> objectDataBack) {
                        ErayicLog.i("getAllSystemServiceByEnt", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<ServiceSystemBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<ServiceSystemBean> objectDataBack) {

                    }
                });
    }

    @Override
    public void init(Context context) {

    }
}
