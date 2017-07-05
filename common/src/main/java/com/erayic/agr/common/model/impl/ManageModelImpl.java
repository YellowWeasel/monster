package com.erayic.agr.common.model.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.model.IManageModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonBaseInfoBean;
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.common.net.back.CommonEntInfoBean;
import com.erayic.agr.common.net.back.CommonUnitInfoBean;
import com.erayic.agr.common.net.back.manage.CommonBasePositionBean;
import com.erayic.agr.common.net.http.manager.HttpManageManager;
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
@Route(path = "/common/model/manage", name = "我的所有相关网络访问接口实现")
public class ManageModelImpl implements IManageModel {


    @Override
    public void init(Context context) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public void getEntInfo(final OnDataListener<CommonEntInfoBean> listener) {

        HttpManageManager.getInstance().getEntInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonEntInfoBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonEntInfoBean> objectDataBack) throws Exception {
                        ErayicLog.i("getEntInfo", ErayicGson.getJsonString(objectDataBack));
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
    public void updateEntName(final String newEntName, final OnDataListener<Object> listener) {

        HttpManageManager.getInstance().updateEntName(newEntName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("updateEntName", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            PreferenceUtils.putParam("EntName", newEntName);
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
    public void getBaseByEnt(final OnDataListener<List<CommonBaseListBean>> listener) {

        HttpManageManager.getInstance().getBaseByEnt()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonBaseListBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonBaseListBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getBaseByEnt", ErayicGson.getJsonString(objectDataBack));
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
    public void addBaseByEnt(String newBaseName, final OnDataListener<Object> listener) {

        HttpManageManager.getInstance().addBaseByEnt(newBaseName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("addBaseByEnt", ErayicGson.getJsonString(objectDataBack));
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
    public void changeBase(String newBaseID, final OnDataListener<Object> listener) {
        HttpManageManager.getInstance().changeBase(newBaseID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("changeBase", ErayicGson.getJsonString(objectDataBack));
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
    public void setBasePosition(String baseID, CommonBasePositionBean bean, final OnDataListener<Object> listener) {
        HttpManageManager.getInstance().setBasePosition(baseID, bean.getLon(), bean.getLat(), bean.getProvinceName(), bean.getCityName(), bean.getRegionName(), bean.getRegionCode(),
                bean.getTownName(), bean.getTownCode(), bean.getVillage(), bean.getAddress())
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("setBasePosition", ErayicGson.getJsonString(objectDataBack));
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
    public void getBaseInfo(String baseID, final OnDataListener<CommonBaseInfoBean> listener) {
        HttpManageManager.getInstance().getBaseInfo(baseID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonBaseInfoBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonBaseInfoBean> objectDataBack) throws Exception {
                        ErayicLog.i("getBaseInfo", ErayicGson.getJsonString(objectDataBack));
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
    public void addUnit(String baseID, String unitName, final OnDataListener<Object> listener) {
        HttpManageManager.getInstance().addUnit(baseID, unitName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("addUnit", ErayicGson.getJsonString(objectDataBack));
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
    public void updateBaseInfo(String baseID, String baseName, String descript, final OnDataListener<Object> listener) {

        HttpManageManager.getInstance().updateBaseInfo(baseID, baseName, descript)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("updateBaseInfo", ErayicGson.getJsonString(objectDataBack));
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
    public void getUnitDetailInfo(String unitID, int type, final OnDataListener<CommonUnitInfoBean> listener) {

        HttpManageManager.getInstance().getUnitDetailInfo(unitID, type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonUnitInfoBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonUnitInfoBean> objectDataBack) throws Exception {
                        ErayicLog.i("getUnitDetailInfo", ErayicGson.getJsonString(objectDataBack));
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
    public void updateBlockInfo(String unitID, String unitName, double area, List<String> regions, List<String> workes, boolean isGreenhouse, final OnDataListener<Object> listener) {

        HttpManageManager.getInstance().updateBlockInfo(unitID, unitName, area, regions, workes, isGreenhouse)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("updateBlockInfo", ErayicGson.getJsonString(objectDataBack));
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

}
