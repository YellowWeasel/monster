package com.erayic.agr.common.model.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBindServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBuyServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchCycleBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchLogsBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchResumeBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchSaveLogBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchSuggestBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListByBaseBean;
import com.erayic.agr.common.net.http.manager.HttpUnitManager;
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
@Route(path = "/common/model/unit", name = "管理单元相关数据接口实现")
public class UnitModelImpl implements IUnitModel {
    @SuppressWarnings("unchecked")
    @Override
    public void getAllUnit(int type, final OnDataListener<List<CommonUnitListBean>> listener) {

        HttpUnitManager.getInstance().getAllUnit(type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonUnitListBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonUnitListBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getAllUnit", ErayicGson.getJsonString(objectDataBack));
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
    public void getAllUnitByBase(int type, final OnDataListener<List<CommonUnitListByBaseBean>> listener) {

        HttpUnitManager.getInstance().getAllUnitByBase(type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonUnitListByBaseBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonUnitListByBaseBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getAllUnitByBase", ErayicGson.getJsonString(objectDataBack));
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
    public void createBatch(String proID, String seedID, String seedName, String quantity, int unit, String stTime, String ope, String unitID, int unitType, final OnDataListener<Object> listener) {

        HttpUnitManager.getInstance().createBatch(proID, seedID, seedName, quantity, unit, stTime, ope, unitID, unitType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("createBatch", ErayicGson.getJsonString(objectDataBack));
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
    public void bindServiceByBatch(String unitID, int unitType, String batchID, int serType, String serviceID, final OnDataListener<Object> listener) {
        HttpUnitManager.getInstance().bindServiceByBatch(unitID, unitType, batchID, serType, serviceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("bindServiceByBatch", ErayicGson.getJsonString(objectDataBack));
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
    public void getSingleSeviceList(int serType, final OnDataListener<List<CommonUnitBatchServiceBean>> listener) {
        HttpUnitManager.getInstance().getSingleSeviceList(serType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonUnitBatchServiceBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonUnitBatchServiceBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getSingleSeviceList", ErayicGson.getJsonString(objectDataBack));
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
    public void getBindService(String batchID, int serType, final OnDataListener<CommonUnitBatchBindServiceBean> listener) {
        HttpUnitManager.getInstance().getBindService(batchID, serType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonUnitBatchBindServiceBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonUnitBatchBindServiceBean> objectDataBack) throws Exception {
                        ErayicLog.i("getBindService", ErayicGson.getJsonString(objectDataBack));
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
    public void cancelServieBind(String batchID, int serType, final OnDataListener<Object> listener) {
        HttpUnitManager.getInstance().cancelServieBind(batchID, serType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("cancelServieBind", ErayicGson.getJsonString(objectDataBack));
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
    public void getBindServiceOfSubject(int serType, final OnDataListener<CommonUnitBatchBuyServiceBean> listener) {
        HttpUnitManager.getInstance().getBindServiceOfSubject(serType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonUnitBatchBuyServiceBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonUnitBatchBuyServiceBean> objectDataBack) throws Exception {
                        ErayicLog.i("getBindServiceOfSubject", ErayicGson.getJsonString(objectDataBack));
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
    public void getPorduceHistoryByBatch(String batchID, int pageNum, int pageSize, final OnDataListener<List<CommonUnitBatchResumeBean>> listener) {
        HttpUnitManager.getInstance().getPorduceHistoryByBatch(batchID, pageNum, pageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonUnitBatchResumeBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonUnitBatchResumeBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getPorduceHistoryByBatch", ErayicGson.getJsonString(objectDataBack));
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
    public void saveWorkLog(CommonUnitBatchSaveLogBean bean, final OnDataListener<Object> listener) {
        HttpUnitManager.getInstance().saveWorkLog(bean)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("saveWorkLog", ErayicGson.getJsonString(objectDataBack));
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
    public void getWorkLogByBatch(String batchID, int pageNum, int pageSize, final OnDataListener<List<CommonUnitBatchLogsBean>> listener) {
        HttpUnitManager.getInstance().getWorkLogByBatch(batchID, pageNum, pageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonUnitBatchLogsBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonUnitBatchLogsBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getWorkLogByBatch", ErayicGson.getJsonString(objectDataBack));
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
    public void getBatchDetail(String unitID, int type, String batchID, final OnDataListener<CommonUnitBatchInfoBean> listener) {
        HttpUnitManager.getInstance().getBatchDetail(unitID, type, batchID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonUnitBatchInfoBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonUnitBatchInfoBean> objectDataBack) throws Exception {
                        ErayicLog.i("getBatchDetail", ErayicGson.getJsonString(objectDataBack));
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
    public void getCycleDetailByBatch(String batchID, String unitID, int type, final OnDataListener<CommonUnitBatchCycleBean> listener) {
        HttpUnitManager.getInstance().getCycleDetailByBatch(batchID, unitID, type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonUnitBatchCycleBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonUnitBatchCycleBean> objectDataBack) throws Exception {
                        ErayicLog.i("getCycleDetailByBatch", ErayicGson.getJsonString(objectDataBack));
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
    public void getCycleByBatch(String batchID, final OnDataListener<Object> listener) {
        HttpUnitManager.getInstance().getCycleByBatch(batchID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("getCycleByBatch", ErayicGson.getJsonString(objectDataBack));
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
    public void getSuggestDetail(String unitID, int type, String batchID, final OnDataListener<CommonUnitBatchSuggestBean> listener) {
        HttpUnitManager.getInstance().getSuggestDetail(unitID, type, batchID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonUnitBatchSuggestBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonUnitBatchSuggestBean> objectDataBack) throws Exception {
                        ErayicLog.i("getSuggestDetail", ErayicGson.getJsonString(objectDataBack));
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
    public void updateBatch(String batchID, String proID, String seedID, String seedName, float quantity, int unit, String stTime, String unitID, final OnDataListener<Object> listener) {
        HttpUnitManager.getInstance().updateBatch(batchID, proID, seedID, seedName, quantity, unit, stTime, unitID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("updateBatch", ErayicGson.getJsonString(objectDataBack));
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
    public void deleteBatch(String batchID, final OnDataListener<Object> listener) {
        HttpUnitManager.getInstance().deleteBatch(batchID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("deleteBatch", ErayicGson.getJsonString(objectDataBack));
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
    public void finishBatch(String batchID, String finishTime, final OnDataListener<Object> listener) {
        HttpUnitManager.getInstance().finishBatch(batchID, finishTime)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("finishBatch", ErayicGson.getJsonString(objectDataBack));
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
    public void getAllBatchByHistory(String unitID, int type, int pageNum, int pageSize, final OnDataListener<List<CommonUnitBatchInfoBean.Batch>> listener) {
        HttpUnitManager.getInstance().getAllBatchByHistory(unitID, type, pageNum, pageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonUnitBatchInfoBean.Batch>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonUnitBatchInfoBean.Batch>> objectDataBack) throws Exception {
                        ErayicLog.i("getAllBatchByHistory", ErayicGson.getJsonString(objectDataBack));
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