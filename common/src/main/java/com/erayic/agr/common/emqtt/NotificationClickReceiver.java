package com.erayic.agr.common.emqtt;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class NotificationClickReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //todo 跳转之前要处理的逻辑
//        Intent newIntent = new Intent(context, ActivityMain.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(newIntent);
        ARouter.getInstance().build("/main/Activity/MainActivity").withFlags(Intent.FLAG_ACTIVITY_NEW_TASK).navigation();
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }
}