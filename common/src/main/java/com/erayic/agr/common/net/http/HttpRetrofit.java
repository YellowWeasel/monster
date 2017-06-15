package com.erayic.agr.common.net.http;

import android.app.Application;

import com.alibaba.android.arouter.exception.InitException;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.interceptor.LoggingInterceptor;
import com.erayic.agr.common.net.interceptor.ReceivedCookiesInterceptor;
import com.erayic.agr.common.net.interceptor.RequestCookiesInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpRetrofit {

    private static Application application;
    private static HttpRetrofit httpService;
    private static Retrofit receivedCookiesRetrofit, sendCookiesRetrofit, noCookiesRetrofit;
    private volatile static boolean hasInit = false;

    private HttpRetrofit(Application application) {
        this.application = application;
    }

    // 提供一个全局的静态方法
    public static void init(Application application) {
        if (httpService == null) {
            synchronized (HttpRetrofit.class) {
                if (httpService == null) {
                    httpService = new HttpRetrofit(application);
                    hasInit = true;
                }
            }
        }
    }
    /**
     * 请求Retrofit（保存Cookies）
     */
    public static Retrofit getReceivedCookiesRetrofit() {
        if (!hasInit)
            throw new InitException("HttpRetrofit::Init::Invoke init(context) first!");
        else {
            if (receivedCookiesRetrofit == null) {
                synchronized (Retrofit.class) {
                    if (receivedCookiesRetrofit == null) {
                        receivedCookiesRetrofit = new Retrofit.Builder()
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .baseUrl(AgrConstant.WEB_SERVER_URL)
                                .client(new OkHttpClient.Builder()
                                        .retryOnConnectionFailure(true)
                                        .addInterceptor(new LoggingInterceptor())
                                        .addInterceptor(new ReceivedCookiesInterceptor(application))
                                            .connectTimeout(15, TimeUnit.SECONDS)
                                        .build())
                                .build();
                    }
                }
            }
            return receivedCookiesRetrofit;
        }
    }

    /**
     * 请求Retrofit（携带Cookies）
     */
    public static Retrofit getRequestCookiesRetrofit() {
        if (!hasInit)
            throw new InitException("HttpRetrofit::Init::Invoke init(context) first!");
        else {
            if (sendCookiesRetrofit == null) {
                synchronized (Retrofit.class) {
                    if (sendCookiesRetrofit == null) {
                        sendCookiesRetrofit = new Retrofit.Builder()
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .baseUrl(AgrConstant.WEB_SERVER_URL)
                                .client(new OkHttpClient.Builder()
                                        .retryOnConnectionFailure(true)
                                        .addInterceptor(new LoggingInterceptor())
                                        .addInterceptor(new RequestCookiesInterceptor(application))
                                        .connectTimeout(15, TimeUnit.SECONDS)
                                        .build())
                                .build();
                    }
                }
            }
            return sendCookiesRetrofit;
        }
    }

    /**
     * 获取默认Retrofit（无Cookies）
     */
    public static Retrofit getDefaultRetrofit() {
        if (!hasInit)
            throw new InitException("HttpRetrofit::Init::Invoke init(context) first!");
        else {
            if (noCookiesRetrofit == null) {
                synchronized (Retrofit.class) {
                    if (noCookiesRetrofit == null) {
                        noCookiesRetrofit = new Retrofit.Builder()
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .baseUrl(AgrConstant.WEB_SERVER_URL)
                                .client(new OkHttpClient.Builder()
                                        .retryOnConnectionFailure(true)
                                        .addInterceptor(new LoggingInterceptor())
                                        .connectTimeout(15, TimeUnit.SECONDS)
                                        .build())
                                .build();
                    }
                }
            }
            return noCookiesRetrofit;
        }

    }

}
