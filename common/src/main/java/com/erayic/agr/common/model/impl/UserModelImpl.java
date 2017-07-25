package com.erayic.agr.common.model.impl;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.model.IUserModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.CommonUserInfoBean;
import com.erayic.agr.common.net.http.manager.HttpUserManager;
import com.erayic.agr.common.util.ErayicGson;
import com.erayic.agr.common.util.ErayicImage;
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
@Route(path = "/common/model/user", name = "用户相关数据接口实现")
public class UserModelImpl implements IUserModel {

    @SuppressWarnings("unchecked")
    @Override
    public void getUserInfo(final OnDataListener<CommonUserInfoBean> listener) {

        HttpUserManager.getInstance().getUserInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonUserInfoBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonUserInfoBean> objectDataBack) throws Exception {
                        ErayicLog.i("getUserInfo", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            PreferenceUtils.putParam("UserID", objectDataBack.getResult().getUserID());
                            PreferenceUtils.putParam("ActiveBaseID", objectDataBack.getResult().getActiveBaseID());
                            PreferenceUtils.putParam("BaseID", objectDataBack.getResult().getActiveBaseID());
                            PreferenceUtils.putParam("BaseName", objectDataBack.getResult().getBaseName());
                            PreferenceUtils.putParam("EntID", objectDataBack.getResult().getEntID());
                            PreferenceUtils.putParam("EntName", objectDataBack.getResult().getEntName());
                            PreferenceUtils.putParam("UserName", objectDataBack.getResult().getName());
                            PreferenceUtils.putParam("UserTel", objectDataBack.getResult().getTelNum());
                            PreferenceUtils.putParam("UserRole", objectDataBack.getResult().getRole());
                            PreferenceUtils.putParam("isWeixin", objectDataBack.getResult().isWeixin());
                            PreferenceUtils.putParam("UserIcon", objectDataBack.getResult().getIcon());
                            PreferenceUtils.putParam("ExpireTime", objectDataBack.getResult().getAPP().getExpireTime());
                            PreferenceUtils.putParam("RegisterTime", objectDataBack.getResult().getAPP().getRegisterTime());
                            PreferenceUtils.putParam("Status", objectDataBack.getResult().getAPP().getStatus());
                            PreferenceUtils.putParam("BaseLon", String.valueOf(objectDataBack.getResult().getBasePos().getLon()));
                            PreferenceUtils.putParam("BaseLat", String.valueOf(objectDataBack.getResult().getBasePos().getLat()));
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
    public void updateUserName(final String newName, final OnDataListener<Object> listener) {

        HttpUserManager.getInstance().updateUserName(newName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("updateUserName", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                            PreferenceUtils.putParam("UserName", newName);
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
    public void updateUserIcon(String path, final OnDataListener<Object> listener) {

        HttpUserManager.getInstance().updateUserIcon(ErayicImage.bitmapToBase64(BitmapFactory.decodeFile(path)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("updateUserIcon", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success(objectDataBack.getResult());
                            PreferenceUtils.putParam("UserIcon", objectDataBack.getResult().toString());
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
    public void setPassword(String pass, final OnDataListener<Object> listener) {

        HttpUserManager.getInstance().setPassword(pass)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("setPassword", ErayicGson.getJsonString(objectDataBack));
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
    public void GetAllUserByBase(final OnDataListener<List<CommonPersonnelBean>> listener) {

        HttpUserManager.getInstance().GetAllUserByBase()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonPersonnelBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonPersonnelBean>> objectDataBack) throws Exception {
                        ErayicLog.i("GetAllUserByBase", ErayicGson.getJsonString(objectDataBack));
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
    public void getAllUserBySpecifyBase(String baseID, final OnDataListener<List<CommonPersonnelBean>> listener) {

        HttpUserManager.getInstance().getAllUserBySpecifyBase(baseID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonPersonnelBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonPersonnelBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getAllUserBySpecifyBase", ErayicGson.getJsonString(objectDataBack));
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
    public void UpdateTel(String newTel, String oriTel, String verifyNum, final OnDataListener<Object> listener) {

        HttpUserManager.getInstance().UpdateTel(newTel, oriTel, verifyNum)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("UpdateTel", ErayicGson.getJsonString(objectDataBack));
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
    public void updateUserInfo(String userID, String name, String tel, int role, final OnDataListener<Object> listener) {

        HttpUserManager.getInstance().updateUserInfo(userID, name, tel, role)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("updateUserInfo", ErayicGson.getJsonString(objectDataBack));
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
    public void deleteUser(String userID, final OnDataListener<Object> listener) {

        HttpUserManager.getInstance().deleteUser(userID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("UpdateTel", ErayicGson.getJsonString(objectDataBack));
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
    public void sendInvite(String tel, String name, int role, final OnDataListener<Object> listener) {

        HttpUserManager.getInstance().sendInvite(tel, name, role)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<Object>>() {
                    @Override
                    public void accept(@NonNull DataBack<Object> objectDataBack) throws Exception {
                        ErayicLog.i("sendInvite", ErayicGson.getJsonString(objectDataBack));
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
    public void getBaseListByUser(final OnDataListener<List<CommonBaseListBean>> listener) {
        HttpUserManager.getInstance().getBaseListByUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<List<CommonBaseListBean>>>() {
                    @Override
                    public void accept(@NonNull DataBack<List<CommonBaseListBean>> objectDataBack) throws Exception {
                        ErayicLog.i("getBaseListByUser", ErayicGson.getJsonString(objectDataBack));
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
        HttpUserManager.getInstance().changeBase(newBaseID)
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

    @Override
    public void init(Context context) {

    }
}
