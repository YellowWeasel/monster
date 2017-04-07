package com.erayic.agr.common.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.util.ErayicLog;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Interceptor(priority = 1, name = "登陆拦截器")
public class LoginInterceptor implements IInterceptor {

    private Context context;

    @Override
    public void process(Postcard postcard, final InterceptorCallback callback) {
        ErayicLog.e(postcard.toString());
        if (postcard.getPath().equals("/main/Activity/MainActivity")) {
            if (PreferenceUtils.getParam("AutoLogin", false)) {
                ErayicLog.d("LoginInterceptor:::通过登陆拦截器");
                callback.onContinue(postcard);//处理完成 交还控制权
            } else {
                ErayicLog.e("LoginInterceptor:::未通过登陆拦截器，跳转登陆页面");
                ARouter.getInstance().build("/index/Activity/LoginActivity")
                        .navigation();//跳转到登陆页面
                callback.onInterrupt(null);
            }
        } else {
            callback.onContinue(postcard);//处理完成 交还控制权
            // callback.onInterrupt(new RuntimeException("我觉得有点异常"));//觉得有问题，中断路由流程
        }
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
