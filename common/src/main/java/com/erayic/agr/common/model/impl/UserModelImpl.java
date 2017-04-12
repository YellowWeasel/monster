package com.erayic.agr.common.model.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.model.IUserModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.UserInfoBean;
import com.erayic.agr.common.net.http.manager.HttpIndexManager;
import com.erayic.agr.common.net.http.manager.HttpUserManager;
import com.erayic.agr.common.util.ErayicGson;
import com.erayic.agr.common.util.ErayicLog;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/common/model/user", name = "用户相关数据接口实现")
public class UserModelImpl implements IUserModel {

    @SuppressWarnings("unchecked")
    @Override
    public void getUserInfo(final OnDataListener<Object> listener) {
        HttpUserManager.getInstance().getUserInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<DataBack<UserInfoBean>>() {
                    @Override
                    public void call(DataBack<UserInfoBean> objectDataBack) {
                        ErayicLog.i("getUserInfo", ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {
                            PreferenceUtils.putParam("UserID", objectDataBack.getResult().getUserID());
                            PreferenceUtils.putParam("ActiveBaseID", objectDataBack.getResult().getActiveBaseID());
                            PreferenceUtils.putParam("BaseName", objectDataBack.getResult().getBaseName());
                            PreferenceUtils.putParam("EntID", objectDataBack.getResult().getEntID());
                            PreferenceUtils.putParam("EntName", objectDataBack.getResult().getEntName());
                            PreferenceUtils.putParam("UserName", objectDataBack.getResult().getBasic().getName());
                            PreferenceUtils.putParam("UserRole", objectDataBack.getResult().getBasic().getRole());
                            PreferenceUtils.putParam("ExpireTime", objectDataBack.getResult().getAPP().getExpireTime());
                            PreferenceUtils.putParam("RegisterTime", objectDataBack.getResult().getAPP().getRegisterTime());
                            PreferenceUtils.putParam("Status", objectDataBack.getResult().getAPP().getStatus());
                            listener.success("");
                        } else {
                            listener.fail(objectDataBack.getErrCode(), objectDataBack.getErrMsg());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBack<UserInfoBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(ErrorCode.ERROR_APP_BASE, throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<UserInfoBean> objectDataBack) {

                    }
                });
    }

    @Override
    public void init(Context context) {

    }
}
