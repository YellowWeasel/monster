package com.erayic.agr.common.model.impl;

import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.IndexLoginBean;
import com.erayic.agr.common.net.http.manager.HttpIndexManager;
import com.erayic.agr.common.util.ErayicGson;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

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
                        System.out.println(ErayicGson.getJsonString(objectDataBack));
                        if (objectDataBack.isSucess()) {

                            listener.success("登录成功");
                        } else {
                            listener.fail(objectDataBack.getErrMsg());
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
                        listener.fail(throwable.getMessage());
                        //System.out.println(throwable);
                    }

                    @Override
                    public void onNext(DataBack<Object> objectDataBack) {

                    }
                });
    }
}
