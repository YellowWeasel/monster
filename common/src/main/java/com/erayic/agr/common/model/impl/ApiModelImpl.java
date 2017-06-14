package com.erayic.agr.common.model.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonReportsByMonthBean;
import com.erayic.agr.common.net.back.api.CommonAgriculturalInfoBean;
import com.erayic.agr.common.net.back.api.CommonAgriculturalinfoDetailBean;
import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;
import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonMarketDynamicPriceBean;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsDetailBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsBean;
import com.erayic.agr.common.net.http.manager.HttpApiManager;
import com.erayic.agr.common.util.ErayicGson;
import com.erayic.agr.common.util.ErayicLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import io.reactivex.schedulers.Schedulers;


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
                .doOnNext(new Consumer<DataBack<CommonRealTimeWeatherBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonRealTimeWeatherBean> objectDataBack) throws Exception {
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
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(DataBack<Object> o) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


//        HttpApiManager.getInstance().getRealTimeWeather()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.io())
//                .doOnNext(new Action1<DataBack<CommonRealTimeWeatherBean>>() {
//                    @Override
//                    public void call(DataBack<CommonRealTimeWeatherBean> objectDataBack) {
//                        ErayicLog.i("getRealTimeWeather", ErayicGson.getJsonString(objectDataBack));
//                        if (objectDataBack.isSucess()) {
//
//                            listener.success(objectDataBack.getResult());
//                        } else {
//                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
//                        }
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<DataBack<Object>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
//                        //System.out.println(throwable);
//                    }
//
//                    @Override
//                    public void onNext(DataBack<Object> objectDataBack) {
//
//                    }
//                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getFeatureWeather(final OnDataListener<List<CommonFutureWeatherBean>> listener) {
        HttpApiManager.getInstance().getFeatureWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonFutureWeatherBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonFutureWeatherBean>> objectDataBack) throws Exception {
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
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(DataBack<Object> o) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getWeatherTenDayReportsByMonth(int year, int month, final OnDataListener<List<CommonReportsByMonthBean>> listener) {

        HttpApiManager.getInstance().getWeatherTenDayReportsByMonth(year, month)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonReportsByMonthBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonReportsByMonthBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getWeatherTenDayReportsByMonth", ErayicGson.getJsonString(objectDataBack));
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
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(DataBack<Object> o) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getDynamicPrice(int cropId, String start, String end, String serviceId, final OnDataListener<CommonDynamicPriceBean> listener) {
        HttpApiManager.getInstance().getDynamicPrices(cropId, start, end, serviceId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonDynamicPriceBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonDynamicPriceBean> objectDataBack) throws Exception {
                        ErayicLog.i("getDynamicPrice", ErayicGson.getJsonString(objectDataBack));
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
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(DataBack<Object> o) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @SuppressWarnings("unchecked")
    @Override
    public void getPoliciesRegulationsDetail(int Id, final OnDataListener<CommonPoliciesRegulationsDetailBean> listener) {
        HttpApiManager.getInstance().getPoliciesRegulationsDetail(Id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonPoliciesRegulationsDetailBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonPoliciesRegulationsDetailBean> objectDataBack) throws Exception {
                        ErayicLog.i("getPoliciesRegulationsDetail", ErayicGson.getJsonString(objectDataBack));
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
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(DataBack<Object> o) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getPoliciesRegulations(int pageIndex, int pageSize, final OnDataListener<List<CommonPoliciesRegulationsBean>> listener) {
        HttpApiManager.getInstance().getPoliciesRegulations(pageIndex, pageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonPoliciesRegulationsBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonPoliciesRegulationsBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getPoliciesRegulations", ErayicGson.getJsonString(objectDataBack));
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
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(DataBack<Object> o) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getDesignatedMarketDynamicPrices(int cropId, String marketName, String start, String end, String serviceId, final OnDataListener<List<CommonMarketDynamicPriceBean>> listener) {
        HttpApiManager.getInstance().getDesignatedMarketDynamicPrices(cropId, marketName, start, end, serviceId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonMarketDynamicPriceBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonMarketDynamicPriceBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getDesignatedMarketDynamicPrices", ErayicGson.getJsonString(objectDataBack));
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
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(DataBack<Object> o) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getAgriculturalDetailInfos(int Id, final OnDataListener<CommonAgriculturalinfoDetailBean> listener) {
        HttpApiManager.getInstance().getAgriculturalDetailInfos(Id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonAgriculturalinfoDetailBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonAgriculturalinfoDetailBean> objectDataBack) throws Exception {
                        ErayicLog.i("getAgriculturalDetailInfos", ErayicGson.getJsonString(objectDataBack));
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
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(DataBack<Object> o) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getAgriculturalInfos(int pageIndex, int pageSize, final OnDataListener<List<CommonAgriculturalInfoBean>> listener) {

        HttpApiManager.getInstance().getAgriculturalInfos(pageIndex, pageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonAgriculturalInfoBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonAgriculturalInfoBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getAgriculturalInfos", ErayicGson.getJsonString(objectDataBack));
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
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(DataBack<Object> o) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void init(Context context) {

    }
}
