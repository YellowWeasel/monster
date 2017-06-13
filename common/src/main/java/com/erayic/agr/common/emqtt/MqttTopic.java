package com.erayic.agr.common.emqtt;

import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.config.PreferenceUtils;

import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
public class MqttTopic {

    public static Topic[] getTopic() {
        return new Topic[]{new Topic(AgrConstant.AGR_APPID, QoS.EXACTLY_ONCE),
                new Topic(AgrConstant.AGR_APPID + "/" + PreferenceUtils.getParam("ActiveBaseID", AgrConstant.GUID_Empty), QoS.EXACTLY_ONCE),
                new Topic(AgrConstant.AGR_APPID + "/" + PreferenceUtils.getParam("ActiveBaseID", AgrConstant.GUID_Empty) + "/" + PreferenceUtils.getParam("UserID", AgrConstant.GUID_Empty), QoS.EXACTLY_ONCE)};
    }


}
