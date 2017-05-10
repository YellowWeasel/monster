package com.erayic.agr.serverproduct.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by wxk on 2017/5/10.
 */

public class ScrollListView extends ListView {
    public ScrollListView(Context context) {
        super(context);
    }

    public ScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expendSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expendSpec);
    }
}
