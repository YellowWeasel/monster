package com.erayic.agr.common.service;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;

import com.erayic.agr.common.emqtt.MqttService;
import com.erayic.agr.common.util.ErayicApp;
import com.erayic.agr.common.util.ErayicLog;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：JobService 存活（5.0以上）
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ErayicService extends JobService {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ErayicLog.i("ErayicService", "ErayicService启动");
        return START_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        ErayicLog.i("ErayicService", "执行了onStartJob方法");
        boolean isLocalServiceWork = ErayicApp.isServiceExisted(this, "com.erayic.agr.common.emqtt.MqttService");
        boolean isRemoteServiceWork = ErayicApp.isServiceExisted(this, "com.marswin89.marsdaemon.demo.Service2");
        if (!isLocalServiceWork) {
//            this.startService(new Intent(this, MqttService.class));
//            this.startService(new Intent(this,Service2.class));
//            Toast.makeText(this, "进程启动", Toast.LENGTH_SHORT).show();
//            ErayicLog.i("ErayicService", "启动MqttService");
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        ErayicLog.i("ErayicService", "执行了onStopJob方法");
        return true;
    }
}
