package com.erayic.agr.common.config;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：重写LinearLayoutManager禁止RecyclerView滑动事件
 * 用法：
 * CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(mContext);
 * linearLayoutManager.setScrollEnabled(false);
 * mDevicesRV.setLayoutManager(linearLayoutManager);
 */

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }


}
