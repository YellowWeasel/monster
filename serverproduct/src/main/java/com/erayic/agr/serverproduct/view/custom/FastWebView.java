package com.erayic.agr.serverproduct.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by wxk on 2017/7/17.
 * 防止WebView内存泄漏
 */

public class FastWebView extends WebView {

    private boolean is_gone = false;

    public FastWebView(Context context) {
        super(context);
    }

    public FastWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {

        super.onWindowVisibilityChanged(visibility);

        if (visibility == View.GONE) {
            try
            {
                WebView.class.getMethod("onPause").invoke(this);//停止刷新

            } catch(Exception e) {
            }
            this.pauseTimers();
            this.is_gone = true;
        } else if(visibility == View.VISIBLE) {
            try
            {
                WebView.class.getMethod("onResume").invoke(this);//继续刷新
            } catch(Exception e) {
            }
            this.resumeTimers();
            this.is_gone = false;
        }
    }

    @Override
    protected void  onDetachedFromWindow() {

        if(this.is_gone) {
            try
            {
                this.destroy();
            } catch(Exception e) {

            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }
}
