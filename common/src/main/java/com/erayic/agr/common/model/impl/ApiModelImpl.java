package com.erayic.agr.common.model.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;
import com.erayic.agr.common.net.http.manager.HttpApiManager;
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

@Route(path = "/common/model/api", name = "应用相关数据接口实现")
public class ApiModelImpl implements IApiModel {

    @SuppressWarnings("unchecked")
    @Override
    public void getRealTimeWeather(final OnDataListener<CommonRealTimeWeatherBean> listener) {
        HttpApiManager.getInstance().getRealTimeWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<CommonRealTimeWeatherBean>>() {
                    @Override
                    public void call(DataBack<CommonRealTimeWeatherBean> objectDataBack) {
                        ErayicLog.i("getRealTimeWeather", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {

                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<Object>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<Object> objectDataBack) {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getFeatureWeather(final OnDataListener<List<CommonFutureWeatherBean>> listener) {
        HttpApiManager.getInstance().getFeatureWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<List<CommonFutureWeatherBean>>>() {
                    @Override
                    public void call(DataBack<List<CommonFutureWeatherBean>> objectDataBack) {
                        ErayicLog.i("getFeatureWeather", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {

                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<Object>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<Object> objectDataBack) {

                    }
                });
    }

    @Override
    public void init(Context context) {

    }
}
