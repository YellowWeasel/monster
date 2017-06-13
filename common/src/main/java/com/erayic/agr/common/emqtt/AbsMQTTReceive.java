package com.erayic.agr.common.emqtt;

import android.content.Context;

public abstract class AbsMQTTReceive {

    /**
     * Mqtt消息处理
     */
    public abstract void MsgReceive(Context context, String topic, String msg);


}
