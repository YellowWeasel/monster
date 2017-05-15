package com.erayic.agr.serverproduct.view.custom;

import android.provider.Settings;
import android.view.View;

/**
 * Created by 23060 on 2017/5/13.
 */

public abstract class CustomOnclickListener implements View.OnClickListener {
    public static  final  long MIN_CLICK_DELAY_TIME=1000;
    private long  lastClickTime;
    protected abstract void  doWorkOnClick();
    @Override
    public void onClick(View v) {
            long currentTime=System.currentTimeMillis();
           if (currentTime-lastClickTime>MIN_CLICK_DELAY_TIME){
               lastClickTime=currentTime;
               doWorkOnClick();
           }
    }
}
