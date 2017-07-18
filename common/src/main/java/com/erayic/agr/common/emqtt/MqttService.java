package com.erayic.agr.common.emqtt;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.util.ErayicLog;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.HexSupport;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.fusesource.mqtt.client.Tracer;
import org.fusesource.mqtt.codec.MQTTFrame;

import java.net.URISyntaxException;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class MqttService extends Service {

    private boolean isUnSubscribe = true;//是否未订阅
    private boolean isConnection = false;
    private CallbackConnection callbackConnection;

    private MQTT mqtt;

    private HandlerThread MSGhandlerThread = new HandlerThread(
            "ERAYIC_MQTT_MSG");//建立mqtt消息传送线程
    private Handler MSGHandler = null;
    private final static String SEND_MSG_KEY = "SEND_MSG_KEY";
    private final static String SEND_MSG_TOPIC = "SEND_MSG_TOPIC";
    private String ReceiveClassName = "com.erayic.agr.common.emqtt.MsgReceive";// 回调处理类

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //接受传递过来的intent的数据等
        ErayicLog.i("MqttService", "onStartCommand");
        initMqtt();
        return START_STICKY;
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: //EMQTT连接失败或异常断开连接
                {
                    isUnSubscribe = true;
                }
                break;
                case 1: //EMQTT连接成功
                {
                    isUnSubscribe = false;
                }
                break;
                case 2://消息提示
                {

                }
                break;
                default:
                    break;
            }
        }
    };

    private void initMqtt() {

        if (isConnection){
            return;
        }

        // 消息接收处理线程
        if (!MSGhandlerThread.isAlive()) {
            MSGhandlerThread.start();
            if (MSGHandler == null) {
                MSGHandler = new Handler(MSGhandlerThread.getLooper(),
                        new Handler.Callback() {
                            @Override
                            public boolean handleMessage(Message msg) {
                                String receiveTopic = msg.getData().getString(
                                        SEND_MSG_TOPIC);
                                String receiveMsg = msg.getData().getString(
                                        SEND_MSG_KEY);

                                try {
                                    AbsMQTTReceive absMqttReceive = (AbsMQTTReceive) ClassHelper
                                            .newInstance(ReceiveClassName);

                                    absMqttReceive.MsgReceive(MqttService.this, receiveTopic, receiveMsg);

                                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                        });
            }
        }

        if (!isUnSubscribe) {//退订
            try {
                //退订监听
                callbackConnection.disconnect(new Callback<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Message message = new Message();
                        message.what = 0;
                        message.obj = "退订成功！";
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Message message = new Message();
                        message.what = 0;
                        message.obj = "退订失败！";
                        mHandler.sendMessage(message);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    mqtt = new MQTT();
                    mqtt.setHost("tcp://120.24.183.53:1883");//tcp连接
                    mqtt.setClientId("ErayicAgicultureTest");//客户端ID
                    mqtt.setCleanSession(false);//要求mqtt存储离线消息
                    mqtt.setKeepAlive((short) 30);
                    mqtt.setUserName("ErayicAgr");
                    mqtt.setPassword("erayic68592260");
                    mqtt.setVersion("3.1.1");

                    //设置跟踪器
                    mqtt.setTracer(new Tracer() {
                        @Override
                        public void onReceive(MQTTFrame frame) {
                            ErayicLog.i("MQTT-Tracer", frame);
                        }

                        @Override
                        public void onSend(MQTTFrame frame) {
                            ErayicLog.i("MQTT-Tracer", frame);
                        }

                        @Override
                        public void debug(String message, Object... args) {
                            ErayicLog.i("MQTT-Tracer", String.format("debug: " + message, args));
                        }
                    });

                    //连接监听
                    callbackConnection = mqtt.callbackConnection();
                    callbackConnection.listener(new MqttListener());//连接监听
                    callbackConnection.connect(new Callback<Void>() {//连接
                        @Override
                        public void onSuccess(Void aVoid) {
                            Message message = new Message();
                            message.what = 1;
                            message.obj = "连接成功！";
                            mHandler.sendMessage(message);
//                            topics = new Topic[]{new Topic(MqttTopic.mapTopic.get("client") + strTopic, QoS.EXACTLY_ONCE)};
                            //设置订阅主题
                            callbackConnection.subscribe(MqttTopic.getTopic(), new Callback<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    ErayicLog.i("callbackConnection-connect：", "订阅主题成功");
                                }

                                @Override
                                public void onFailure(Throwable throwable) {
                                    ErayicLog.e("callbackConnection-connect：", "订阅主题失败");
                                }
                            });
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            ErayicLog.e("callbackConnection-connect：", "连接失败:" + throwable.getLocalizedMessage());
                            Message message = new Message();
                            message.what = 0;
                            message.obj = "请求连接失败！";
                            mHandler.sendMessage(message);
                        }
                    });
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //连接监听
    private class MqttListener implements Listener {
        @Override
        public void onConnected() {
            isConnection = true;
            ErayicLog.i("MqttListener", "连接成功");
        }

        @Override
        public void onDisconnected() {
            isConnection = false;
            ErayicLog.e("MqttListener", "连接断开");
            Message message = new Message();
            message.what = 0;
            message.obj = "断开连接";
        }

        @Override
        public void onPublish(final UTF8Buffer utf8Buffer, final Buffer buffer, Runnable runnable) {
            //utf8Buffer 主题 buffer 内容
            runnable.run();//告诉服务端接收到消息

            //传送接收到的数据
            Message msg = MSGHandler.obtainMessage();
            Bundle b = new Bundle();
            b.putString(SEND_MSG_KEY, Buffer.utf8(buffer).toString());
            b.putString(SEND_MSG_TOPIC, utf8Buffer.toString());

            msg.setData(b);
            msg.sendToTarget();
        }

        @Override
        public void onFailure(Throwable throwable) {
            //连接失败
            ErayicLog.e("MqttListener", "连接失败");
            callbackConnection.disconnect(null);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initMqtt();
                }
            }, 5000);
        }
    }


    @Override
    public void onDestroy() {
        callbackConnection.disconnect(null);
    }
}
