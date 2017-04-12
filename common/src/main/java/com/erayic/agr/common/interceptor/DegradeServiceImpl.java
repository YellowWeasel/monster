package com.erayic.agr.common.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.erayic.agr.common.util.ErayicToast;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：自定义全局降级策略
 * // 实现DegradeService接口，并加上一个Path内容任意的注解即可
 */

@Route(path = "/xxx/xxx/xxx")
public class DegradeServiceImpl implements DegradeService {

    private Context mContext;
    @Override
    public void onLost(Context context, Postcard postcard) {
        ErayicToast.TextToast(mContext, "找不到" + postcard.getPath());
    }

    @Override
    public void init(Context context) {
        this.mContext = context;
    }
}
