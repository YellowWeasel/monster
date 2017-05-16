package com.erayic.agr.common.config;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

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

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        super.smoothScrollToPosition(recyclerView, state, position);
        LinearSmoothScroller linearSmoothScroller=new LinearSmoothScroller(recyclerView.getContext()){
            @Nullable
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return CustomLinearLayoutManager.this.computeScrollVectorForPosition(targetPosition);
            }
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return super.calculateSpeedPerPixel(displayMetrics);
            }
            @Override
            protected int calculateTimeForScrolling(int dx) {
                if (dx>3000)dx=3000;
                int time=super.calculateTimeForScrolling(dx);
                return time;
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}
