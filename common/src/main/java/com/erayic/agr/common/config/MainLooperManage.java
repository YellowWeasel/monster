package com.erayic.agr.common.config;

import android.os.Handler;
import android.os.Looper;

public class MainLooperManage extends Handler {
    private static MainLooperManage instance = new MainLooperManage(Looper.getMainLooper());

    protected MainLooperManage(Looper looper) {
        super(looper);
    }

    public static MainLooperManage getInstance() {
        return instance;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            runnable.run();
        } else {
            instance.post(runnable);
        }

    }
}
