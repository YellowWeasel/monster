package com.erayic.agr.common.model.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonInvoiceBean;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.net.back.CommonSubServiceBean;
import com.erayic.agr.common.net.back.CommonWcfInvoiceBean;
import com.erayic.agr.common.net.back.ServiceInfoByEntBean;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;
import com.erayic.agr.common.net.http.manager.HttpServerManager;
import com.erayic.agr.common.util.ErayicGson;
import com.erayic.agr.common.util.ErayicLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/common/model/server", name = "服务相关数据接口实现")
public class ServerModelImpl implements IServerModel {

    @SuppressWarnings("unchecked")
    @Override
    public void getAllSystemServiceByUser(final OnDataListener<List<ServiceBuyByUserBean>> listener) {

        HttpServerManager.getInstance().getAllSystemServiceByUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<ServiceBuyByUserBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<ServiceBuyByUserBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getAllServiceByUser", ErayicGson.getJsonString(objectDataBack));
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
    public void getAllSystemServiceByEnt(final OnDataListener<ServiceSystemBean> listener) {

        HttpServerManager.getInstance().getAllSystemServiceByEnt()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<ServiceSystemBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<ServiceSystemBean> objectDataBack) throws Exception {
                        ErayicLog.i("getAllSystemServiceByEnt", ErayicGson.getJsonString(objectDataBack));
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
    public void getSpecifyServiceByEnt(String serviceID, final OnDataListener<ServiceInfoByEntBean> listener) {

        HttpServerManager.getInstance().getSpecifyServiceByEnt(serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<ServiceInfoByEntBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<ServiceInfoByEntBean> objectDataBack) throws Exception {
                        ErayicLog.i("getSpecifyServiceByEnt", ErayicGson.getJsonString(objectDataBack));
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
    public void getAllPriceByService(String serviceID, final OnDataListener<List<CommonPriceBean>> listener) {

        HttpServerManager.getInstance().getAllPriceByService(serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonPriceBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonPriceBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getAllPriceByService", ErayicGson.getJsonString(objectDataBack));
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
    public void getBelongSubService(String serviceID, final OnDataListener<List<Object>> listener) {

        HttpServerManager.getInstance().getBelongSubService(serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<Object>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<Object>> objectDataBack) throws Exception {
                        ErayicLog.i("getBelongSubService", ErayicGson.getJsonString(objectDataBack));
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
    public void orderServiceByBuyOfEnt(String serviceID, int priceID, List<CommonSubServiceBean> subServiceIDs, int payMode, final OnDataListener<Object> listener) {

        HttpServerManager.getInstance().orderServiceByBuyOfEnt(serviceID, priceID, subServiceIDs, payMode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("orderServiceByBuyOfEnt", ErayicGson.getJsonString(objectDataBack));
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
    public void orderServiceByBuyOfEntUser(String serviceID, final OnDataListener<Object> listener) {

        HttpServerManager.getInstance().orderServiceByBuyOfEntUser(serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("orderServiceByBuyOfEntUser", ErayicGson.getJsonString(objectDataBack));
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
    public void cancelUserService(String serviceID, final OnDataListener<Object> listener) {

        HttpServerManager.getInstance().cancelUserService(serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("cancelUserService", ErayicGson.getJsonString(objectDataBack));
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
    public void getUnPayOrderListByUser(final OnDataListener<List<CommonOrderBean>> listener) {
        HttpServerManager.getInstance().getUnPayOrderListByUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonOrderBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonOrderBean>> objectDataBack) throws Exception {
                        ErayicLog.i("orderServiceByBuyOfEnt", ErayicGson.getJsonString(objectDataBack));
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
    public void getRderDetailBySubInfo(String subServiceID, final OnDataListener<List<CommonSubServiceBean>> listener) {

        HttpServerManager.getInstance().getRderDetailBySubInfo(subServiceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonSubServiceBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonSubServiceBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getRderDetailBySubInfo", ErayicGson.getJsonString(objectDataBack));
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
    public void getInvoiceTitleInfo(final OnDataListener<CommonInvoiceBean> listener) {

        HttpServerManager.getInstance().getInvoiceTitleInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonInvoiceBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonInvoiceBean> objectDataBack) throws Exception {
                        ErayicLog.i("getInvoiceTitleInfo", ErayicGson.getJsonString(objectDataBack));
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
    public void updateInvoiceInfo(CommonInvoiceBean bean, final OnDataListener<Object> listener) {
        HttpServerManager.getInstance().updateInvoiceInfo(bean)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("updateInvoiceInfo", ErayicGson.getJsonString(objectDataBack));
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
    public void getSpecifyOrderDetail(String orderID, final OnDataListener<CommonOrderBean> listener) {

        HttpServerManager.getInstance().getSpecifyOrderDetail(orderID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonOrderBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonOrderBean> objectDataBack) throws Exception {
                        ErayicLog.i("getSpecifyOrderDetail", ErayicGson.getJsonString(objectDataBack));
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
    public void cancelOrderByEntUser(String orderID, final OnDataListener<Object> listener) {

        HttpServerManager.getInstance().cancelOrderByEntUser(orderID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("cancelOrderByEntUser", ErayicGson.getJsonString(objectDataBack));
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
    public void requestInvoice(CommonWcfInvoiceBean orderIDs, final OnDataListener<Object> listener) {

        HttpServerManager.getInstance().requestInvoice(orderIDs)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("requestInvoice", ErayicGson.getJsonString(objectDataBack));
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
    public void getHistoryOrderListByUser(int pageNum, int pageSize, final OnDataListener<List<CommonOrderBean>> listener) {

        HttpServerManager.getInstance().getHistoryOrderListByUser(pageNum,pageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonOrderBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonOrderBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getHistoryOrderListByUser", ErayicGson.getJsonString(objectDataBack));
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
