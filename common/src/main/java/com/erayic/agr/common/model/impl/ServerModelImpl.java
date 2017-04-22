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

import java.util.List;
import java.util.Map;

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
    public void getAllSystemServiceByUser(final OnDataListener<List<ServiceBuyByUserBean>> listener) {
        HttpServerManager.getInstance().getAllSystemServiceByUser()
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

    @SuppressWarnings("unchecked")
    @Override
    public void getSpecifyServiceByEnt(String serviceID, final OnDataListener<ServiceInfoByEntBean> listener) {
        HttpServerManager.getInstance().getSpecifyServiceByEnt(serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<ServiceInfoByEntBean>>() {
                    @Override
                    public void call(DataBack<ServiceInfoByEntBean> objectDataBack) {
                        ErayicLog.i("getSpecifyServiceByEnt", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<ServiceInfoByEntBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<ServiceInfoByEntBean> objectDataBack) {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getAllPriceByService(String serviceID, final OnDataListener<List<CommonPriceBean>> listener) {
        HttpServerManager.getInstance().getAllPriceByService(serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<List<CommonPriceBean>>>() {
                    @Override
                    public void call(DataBack<List<CommonPriceBean>> objectDataBack) {
                        ErayicLog.i("getAllPriceByService", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<List<CommonPriceBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<List<CommonPriceBean>> objectDataBack) {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getBelongSubService(String serviceID, final OnDataListener<List<Object>> listener) {
        HttpServerManager.getInstance().getBelongSubService(serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<List<Object>>>() {
                    @Override
                    public void call(DataBack<List<Object>> objectDataBack) {
                        ErayicLog.i("getBelongSubService", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<List<Object>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<List<Object>> objectDataBack) {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void orderServiceByBuyOfEnt(String serviceID, int priceID, List<CommonSubServiceBean> subServiceIDs, int payMode, final OnDataListener<Object> listener) {
        HttpServerManager.getInstance().orderServiceByBuyOfEnt(serviceID, priceID, subServiceIDs, payMode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<Object>>() {
                    @Override
                    public void call(DataBack<Object> objectDataBack) {
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
    public void orderServiceByBuyOfEntUser(String serviceID, final OnDataListener<Object> listener) {
        HttpServerManager.getInstance().orderServiceByBuyOfEntUser(serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<Object>>() {
                    @Override
                    public void call(DataBack<Object> objectDataBack) {
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
    public void cancelUserService(String serviceID, final OnDataListener<Object> listener) {
        HttpServerManager.getInstance().cancelUserService(serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<Object>>() {
                    @Override
                    public void call(DataBack<Object> objectDataBack) {
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
    public void getUnPayOrderListByUser(final OnDataListener<List<CommonOrderBean>> listener) {
        HttpServerManager.getInstance().getUnPayOrderListByUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<List<CommonOrderBean>>>() {
                    @Override
                    public void call(DataBack<List<CommonOrderBean>> objectDataBack) {
                        ErayicLog.i("orderServiceByBuyOfEnt", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<List<CommonOrderBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<List<CommonOrderBean>> objectDataBack) {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getRderDetailBySubInfo(String subServiceID, final OnDataListener<List<CommonSubServiceBean>> listener) {
        HttpServerManager.getInstance().getRderDetailBySubInfo(subServiceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<List<CommonSubServiceBean>>>() {
                    @Override
                    public void call(DataBack<List<CommonSubServiceBean>> objectDataBack) {
                        ErayicLog.i("getRderDetailBySubInfo", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<List<CommonSubServiceBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<List<CommonSubServiceBean>> objectDataBack) {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getInvoiceTitleInfo(final OnDataListener<CommonInvoiceBean> listener) {
        HttpServerManager.getInstance().getInvoiceTitleInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<CommonInvoiceBean>>() {
                    @Override
                    public void call(DataBack<CommonInvoiceBean> objectDataBack) {
                        ErayicLog.i("getInvoiceTitleInfo", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<CommonInvoiceBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<CommonInvoiceBean> objectDataBack) {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateInvoiceInfo(CommonInvoiceBean bean, final OnDataListener<Object> listener) {
        HttpServerManager.getInstance().updateInvoiceInfo(bean)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<Object>>() {
                    @Override
                    public void call(DataBack<Object> objectDataBack) {
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
    public void getSpecifyOrderDetail(String orderID, final OnDataListener<CommonOrderBean> listener) {
        HttpServerManager.getInstance().getSpecifyOrderDetail(orderID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<CommonOrderBean>>() {
                    @Override
                    public void call(DataBack<CommonOrderBean> objectDataBack) {
                        ErayicLog.i("getSpecifyOrderDetail", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<CommonOrderBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<CommonOrderBean> objectDataBack) {

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void cancelOrderByEntUser(String orderID, final OnDataListener<Object> listener) {
        HttpServerManager.getInstance().cancelOrderByEntUser(orderID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<Object>>() {
                    @Override
                    public void call(DataBack<Object> objectDataBack) {
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
    public void requestInvoice(CommonWcfInvoiceBean orderIDs, final OnDataListener<Object> listener) {
        HttpServerManager.getInstance().requestInvoice(orderIDs)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<Object>>() {
                    @Override
                    public void call(DataBack<Object> objectDataBack) {
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
    public void getHistoryOrderListByUser(int pageNum, int pageSize, final OnDataListener<List<CommonOrderBean>> listener) {
        HttpServerManager.getInstance().getHistoryOrderListByUser(pageNum,pageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<List<CommonOrderBean>>>() {
                    @Override
                    public void call(DataBack<List<CommonOrderBean>> objectDataBack) {
                        ErayicLog.i("getHistoryOrderListByUser", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<List<CommonOrderBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<List<CommonOrderBean>> objectDataBack) {

                    }
                });
    }

    @Override
    public void init(Context context) {

    }
}
