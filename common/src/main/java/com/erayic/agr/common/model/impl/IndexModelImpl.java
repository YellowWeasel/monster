package com.erayic.agr.common.model.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.IndexLoginBean;
import com.erayic.agr.common.net.back.IndexRegisterUserBean;
import com.erayic.agr.common.net.http.manager.HttpIndexManager;
import com.erayic.agr.common.util.ErayicGson;
import com.erayic.agr.common.util.ErayicLog;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
                .doOnNext(new Action1<DataBack<IndexLoginBean>>() {
                    @Override
                    public void call(DataBack<IndexLoginBean> objectDataBack) {
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
    public void enterpriseRegister(final String baseName, final String name, final String pass, final String tel, final String appID, final String phoneCode, final String verifyNum, final OnDataListener<Object> listener) {
        HttpIndexManager.getInstance().userVerify(tel, pass)
                .flatMap(new Func1<DataBack<IndexRegisterUserBean>, Observable>() {
                    @Override
                    public Observable call(DataBack<IndexRegisterUserBean> objectDataBack) {
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
                .doOnNext(new Action1<DataBack<String>>() {
                    @Override
                    public void call(DataBack<String> objectDataBack) {
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
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        //请求失败
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(DataBack<Object> objectDataBack) {
                        //请求成功

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void userByInvite(final String appID, final String pass, final String tel, final String code, final String phoneCode, final String verifyNum, final OnDataListener<Object> listener) {
        HttpIndexManager.getInstance().userVerify(tel, pass)
                .flatMap(new Func1<DataBack<IndexRegisterUserBean>, Observable>() {
                    @Override
                    public Observable call(DataBack<IndexRegisterUserBean> objectDataBack) {
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
                .doOnNext(new Action1<DataBack<String>>() {
                    @Override
                    public void call(DataBack<String> objectDataBack) {
                        ErayicLog.i("userByInvite", ErayicGson.getJsonString(objectDataBack));
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
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        //请求失败
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(DataBack<Object> objectDataBack) {
                        //请求成功

                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sendTelVerify(String tel, final OnDataListener<Object> listener) {
        HttpIndexManager.getInstance().sendTelVerify(tel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<Object>>() {
                    @Override
                    public void call(DataBack<Object> objectDataBack) {
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
    public void checkTelVerify(String appID, String tel, String code, String verifyCode, final OnDataListener<Object> listener) {
        HttpIndexManager.getInstance().checkTelVerify(appID, tel, code, verifyCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<Object>>() {
                    @Override
                    public void call(DataBack<Object> objectDataBack) {
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
