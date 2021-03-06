package com.erayic.agr.common.net.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import com.erayic.agr.common.util.ErayicLog;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：向请求中添加cookie 拦截器
 */

public class RequestCookiesInterceptor implements Interceptor {

    private Context context;

    public RequestCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        //RxJava
//        Observable.just(sharedPreferences.getString("cookie", ""))
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String cookie) {
//                        //添加cookie
//                        builder.addHeader("Cookie", cookie);
//                    }
//                });
        Flowable.just(sharedPreferences.getString("cookie", ""))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String cookie) throws Exception {
                        //添加cookie
                        builder.addHeader("Cookie", cookie);
                    }
                });
        return chain.proceed(builder.build());
    }

}
