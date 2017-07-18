package com.erayic.agr.common;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class AgrConstant {

    public static final String WEB_SERVER_URL = "http://192.168.0.188:8000/";
    public static final String IMAGE_URL_PREFIX = "http://192.168.0.7";
    public static final String IMAGE_URL_IMAGE = "http://192.168.0.188:8020";

    public static final String GUID_Empty = "00000000-0000-0000-0000-000000000000";
    public static final String AGR_APPID = "7E550992-C157-4CB4-8048-9DCDF560A41D";//种植业APPID
    public static final String AGR_UPDATE_URL = "http://weixin.erayic.com/AgrTestApk/android/trial-version.json";//APP更新体验版配置文件地址


    /* 测试数据 */
//    public static final String Test_PHONE = "fa78e84d-1d81-45f2-91de-3c16307adb3p";


    public static final int ACTIVITY_REQUEST_ServiceBuyActivity = 9001;
    public static final int ACTIVITY_RESULT_Service_PriceByEntActivity = 9002;
    public static final int ACTIVITY_RESULT_Service_TopicByEntActivity = 9003;

    /**
     * Glide RequestOptions 图标
     */
    public static RequestOptions iconOptions = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.app_base_default_placeholder_icon)
            .error(R.drawable.app_base_default_error_icon)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL);


}
