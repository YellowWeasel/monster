package com.erayic.agr.common.emqtt;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.erayic.agr.common.R;
import com.erayic.agr.common.util.ErayicLog;

import org.fusesource.mqtt.client.Topic;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class MsgReceive extends AbsMQTTReceive {

    private static final Topic[] topics = MqttTopic.getTopic();
    private int notifyId = 1000;

    @Override
    public void MsgReceive(Context context, String topic, String msg) {
        ErayicLog.e("MQTT-MSG", String.format("topic:[%s]    msg:[%s]", topic, msg));

        if (TextUtils.equals(topic, topics[0].name().toString())) {//APP
            showNotification(context,"app",msg,"");
        } else if (TextUtils.equals(topic, topics[1].name().toString())) {//APP/BASIC
            showNotification(context,"app/basic",msg,"");
        } else if (TextUtils.equals(topic, topics[2].name().toString())) {//APP/BASIC/USER
            showNotification(context,"app/basic/user",msg,"");
        }

    }

    private void showNotification(Context context, String title, String content, String subContent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.app_base_android_1)
                .setContentTitle(title)//标题
                .setContentText(content)//内容
                .setSubText(subContent)//次要内容
                .setWhen(System.currentTimeMillis())//设置时间
                .setShowWhen(true)// 设置是否显示时间
                .setUsesChronometer(true)//设置是否显示时钟表示时间(count up)
                .setOngoing(true)//用户点击通知且设置了自动取消时会被删除
                ;

        Intent clickIntent = new Intent(context, NotificationClickReceiver.class); //点击通知之后要发送的广播
        int id = (int) (System.currentTimeMillis() / 1000);
        PendingIntent contentIntent = PendingIntent.getBroadcast(context.getApplicationContext(), id, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;//使用呼吸灯
        notification.flags |= Notification.FLAG_NO_CLEAR;//用户无法取消
        notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;//提醒(铃声/震动/滚动通知摘要)只执行一次
        notification.flags |= Notification.PRIORITY_MAX;//重要而紧急的通知
        notification.flags |= Notification.DEFAULT_SOUND;//	添加默认声音提醒
//        notification.flags |= Notification.DEFAULT_VIBRATE;//	添加默认震动提醒
        notification.flags |= Notification.DEFAULT_LIGHTS;//	添加默认呼吸灯提醒
//        notification.flags |= Notification.DEFAULT_ALL;//	同时添加以上三种默认提醒
        notification.flags |= Notification.VISIBILITY_PRIVATE;//锁定屏幕通知 显示通知图标和内容标题等基本信息，但是隐藏通知的完整内容。


        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(notifyId, notification);//显示通知
//        manager.cancel(notifyId);//取消notifyId关联的所有通知
//        manager.cancelAll();//取消所有通知


        //通知方式
//        Notification.FLAG_SHOW_LIGHTS	是否使用呼吸灯提醒
//        Notification.FLAG_INSISTENT	持续提醒(声音/振动)直到用户响应(点击/取消)
//        Notification.FLAG_ONLY_ALERT_ONCE	提醒(铃声/震动/滚动通知摘要)只执行一次
//        Notification.FLAG_ONGOING_EVENT	正在进行中通知
//        Notification.FLAG_AUTO_CANCEL	用户点击通知后自动取消
//        Notification.FLAG_NO_CLEAR	用户无法取消
//        Notification.FLAG_FOREGROUND_SERVICE	表示正在运行的服务


//        优先级
//        Notification.PRIORITY_MAX	重要而紧急的通知，通知用户这个事件是时间上紧迫的或者需要立即处理的。
//        Notification.PRIORITY_HIGH	高优先级用于重要的通信内容，例如短消息或者聊天，这些都是对用户来说比较有兴趣的
//        Notification.PRIORITY_DEFAULT	默认优先级用于没有特殊优先级分类的通知
//        Notification.PRIORITY_LOW	低优先级可以通知用户但又不是很紧急的事件。只显示状态栏图标
//        Notification.PRIORITY_MIN	用于后台消息 (例如天气或者位置信息)。只有用户下拉通知抽屉才能看到内容
    }


}
