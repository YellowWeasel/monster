package com.erayic.agr.common.model.impl;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.IndexLoginBean;
import com.erayic.agr.common.net.back.IndexRegisterUserBean;
import com.erayic.agr.common.net.back.img.CommonResultImage;
import com.erayic.agr.common.net.http.manager.HttpIndexManager;
import com.erayic.agr.common.util.ErayicGson;
import com.erayic.agr.common.util.ErayicImage;
import com.erayic.agr.common.util.ErayicLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Map;

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
@Route(path = "/common/model/index", name = "应用相关数据接口实现")
public class IndexModelImpl implements IIndexModel {

    @SuppressWarnings("unchecked")
    @Override
    public void login(String appID, String tel, String pass, String phoneCode, final OnDataListener<Object> listener) {

        HttpIndexManager.getInstance().login(appID, tel, pass, phoneCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<IndexLoginBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<IndexLoginBean> objectDataBack) throws Exception {
                        ErayicLog.i("login", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            PreferenceUtils.putParam("AutoLogin", true);
                            PreferenceUtils.putParam("UserID", objectDataBack.getResult().getUserID());
                            PreferenceUtils.putParam("BaseID", objectDataBack.getResult().getBaseID());
                            listener.success("登录成功");
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
    public void enterpriseRegister(final String baseName, final String name, final String pass, final String tel, final String appID, final String phoneCode, final String verifyNum, final OnDataListener<Object> listener) {

        HttpIndexManager.getInstance().userVerify(tel, pass)
                .flatMap(new Function<DataBack<IndexRegisterUserBean>, Flowable>() {
                    @Override
                    public Flowable apply(@NonNull DataBack<IndexRegisterUserBean> objectDataBack) throws Exception {
                        ErayicLog.i("userByInvite", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            return HttpIndexManager.getInstance().entRegister(baseName, objectDataBack.getResult().getUserID(), appID, phoneCode, verifyNum);
                        } else {
                            switch (objectDataBack.getErrCode()) {
                                case ErrorCode.Error_UserNotRegister:
                                    return HttpIndexManager.getInstance().firstRegister(baseName, name, pass, tel, appID, phoneCode, verifyNum);
                                default:
                                    listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                                    return null;
                            }
                        }
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<String>>() {
                    @Override
                    public void accept(@NonNull DataBack<String> objectDataBack) throws Exception {
                        ErayicLog.i("enterpriseRegister", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success("注册成功");
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

//        HttpIndexManager.getInstance().userVerify(tel, pass)
//                .flatMap(new Func1<DataBack<IndexRegisterUserBean>, Observable>() {
//                    @Override
//                    public Observable call(DataBack<IndexRegisterUserBean> objectDataBack) {
//                        ErayicLog.i("userByInvite", ErayicGson.getJsonString(objectDataBack));
//                        if (objectDataBack.isSucess()) {
//                            return HttpIndexManager.getInstance().entRegister(baseName, objectDataBack.getResult().getUserID(), appID, phoneCode, verifyNum);
//                        } else {
//                            switch (objectDataBack.getErrCode()) {
//                                case ErrorCode.Error_UserNotRegister:
//                                    return HttpIndexManager.getInstance().firstRegister(baseName, name, pass, tel, appID, phoneCode, verifyNum);
//                                default:
//                                    listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
//                                    return null;
//                            }
//                        }
//                    }
//                })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.io())
//                .doOnNext(new Action1<DataBack<String>>() {
//                    @Override
//                    public void call(DataBack<String> objectDataBack) {
//                        ErayicLog.i("enterpriseRegister", ErayicGson.getJsonString(objectDataBack));
//                        if (objectDataBack.isSucess()) {
//                            listener.success("注册成功");
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
//                        //请求失败
//                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onNext(DataBack<Object> objectDataBack) {
//                        //请求成功
//
//                    }
//                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void userByInvite(final String appID, final String pass, final String tel, final String code, final String phoneCode, final String verifyNum, final OnDataListener<Object> listener) {


        HttpIndexManager.getInstance().userVerify(tel, pass)
                .flatMap(new Function<DataBack<IndexRegisterUserBean>, Flowable>() {
                    @Override
                    public Flowable apply(@NonNull DataBack<IndexRegisterUserBean> objectDataBack) throws Exception {
                        ErayicLog.i("userByInvite", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            return HttpIndexManager.getInstance().userInviteByUserID(appID, objectDataBack.getResult().getUserID(), tel, code, phoneCode, verifyNum);
                        } else {
                            switch (objectDataBack.getErrCode()) {
                                case ErrorCode.Error_UserNotRegister:
                                    return HttpIndexManager.getInstance().userInviteByTel(appID, pass, tel, code, phoneCode, verifyNum);
                                default:
                                    listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                                    return null;
                            }
                        }
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<String>>() {
                    @Override
                    public void accept(@NonNull DataBack<String> objectDataBack) throws Exception {
                        ErayicLog.i("enterpriseRegister", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success("注册成功");
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

//        HttpIndexManager.getInstance().userVerify(tel, pass)
//                .flatMap(new Func1<DataBack<IndexRegisterUserBean>, Observable>() {
//                    @Override
//                    public Observable call(DataBack<IndexRegisterUserBean> objectDataBack) {
//                        ErayicLog.i("userByInvite", ErayicGson.getJsonString(objectDataBack));
//                        if (objectDataBack.isSucess()) {
//                            return HttpIndexManager.getInstance().userInviteByUserID(appID, objectDataBack.getResult().getUserID(), tel, code, phoneCode, verifyNum);
//                        } else {
//                            switch (objectDataBack.getErrCode()) {
//                                case ErrorCode.Error_UserNotRegister:
//                                    return HttpIndexManager.getInstance().userInviteByTel(appID, pass, tel, code, phoneCode, verifyNum);
//                                default:
//                                    listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
//                                    return null;
//                            }
//                        }
//                    }
//                })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.io())
//                .doOnNext(new Action1<DataBack<String>>() {
//                    @Override
//                    public void call(DataBack<String> objectDataBack) {
//                        ErayicLog.i("userByInvite", ErayicGson.getJsonString(objectDataBack));
//                        if (objectDataBack.isSucess()) {
//                            listener.success("注册成功");
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
//                        //请求失败
//                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onNext(DataBack<Object> objectDataBack) {
//                        //请求成功
//
//                    }
//                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sendTelVerify(String tel, final OnDataListener<Object> listener) {

        HttpIndexManager.getInstance().sendTelVerify(tel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<IndexLoginBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<IndexLoginBean> objectDataBack) throws Exception {
                        ErayicLog.i("sendTelVerify", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success("验证码发送成功");
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
    public void checkTelVerify(String appID, String tel, String code, String verifyCode, final OnDataListener<Object> listener) {

        HttpIndexManager.getInstance().checkTelVerify(appID, tel, code, verifyCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<IndexLoginBean>>() {
                    @Override
                    public void accept(@NonNull DataBack<IndexLoginBean> objectDataBack) throws Exception {
                        ErayicLog.i("checkTelVerify", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            listener.success("验证成功！");
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
    public void uploadImg(String path, final OnDataListener<CommonResultImage> listener) {
        HttpIndexManager.getInstance().uploadImg(ErayicImage.bitmapToBase64(BitmapFactory.decodeFile(path)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<DataBack<CommonResultImage>>() {
                    @Override
                    public void accept(@NonNull DataBack<CommonResultImage> objectDataBack) throws Exception {
                        ErayicLog.i("uploadImg", ErayicGson.getJsonString(objectDataBack));
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
