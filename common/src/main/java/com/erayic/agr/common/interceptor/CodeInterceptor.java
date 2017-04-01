package com.erayic.agr.common.interceptor;

import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.util.ErayicLog;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Interceptor(priority = 2, name = "验证码拦截器")
public class CodeInterceptor implements IInterceptor {
    private Context context;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getPath().equals("/main/MainActivity")) {
            Bundle bundle = postcard.getExtras();
            if (bundle.getBoolean("pass-code", false)) {
                ErayicLog.d("LoginInterceptor:::通过验证码拦截器");
                callback.onContinue(postcard);//处理完成 交还控制权
            } else {
                ErayicLog.e("LoginInterceptor:::未通过验证码拦截器，跳转验证码页面");
                ARouter.getInstance().build("/index/CodeActivity").navigation();//跳转到登陆页面
            }
        } else {
            callback.onContinue(postcard);//处理完成 交还控制权
        }
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
