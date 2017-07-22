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

    public static final String WEB_SERVER_URL = "http://agro2.erayic.com/";
    public static final String IMAGE_URL_IMAGE = "http://agro2.erayic.com";

    public static final String GUID_Empty = "00000000-0000-0000-0000-000000000000";
    public static final String AGR_APPID = "7E550992-C157-4CB4-8048-9DCDF560A41D";//种植业APPID
    public static final String AGR_UPDATE_URL = "http://agro2.erayic.com/App/android/inside/inside-version.json";//APP更新内部版配置文件地址
    public static final String AGR_UPDATE_HISTORY = "http://agro2.erayic.com/App/android/inside/inside-version-history.json";//APP历史更新内部版配置文件地址


    /* 测试数据 */
//    public static final String Test_PHONE = "fa78e84d-1d81-45f2-91de-3c16307adb3p";


    public static final int ACTIVITY_REQUEST_ServiceBuyActivity = 9001;
    public static final int ACTIVITY_RESULT_Service_PriceByEntActivity = 9002;
    public static final int ACTIVITY_RESULT_Service_TopicByEntActivity = 9003;

    /**
     * Glide RequestOptions 系统图标
     */
    public static RequestOptions iconOptions = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.app_base_default_launcher_2)
            .error(R.drawable.app_base_default_launcher_2)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL);


    /**
     * Glide RequestOptions 头像相关
     */
    public static RequestOptions headOptions = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.app_base_default_launcher_6)
            .error(R.drawable.app_base_default_launcher_5)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL);

    /**
     * Glide RequestOptions 图片内容默认（正方形）相关
     */
    public static RequestOptions contentDefaultOptions = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.app_base_default_launcher_8)
            .error(R.drawable.app_base_default_launcher_8)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL);

    /**
     * Glide RequestOptions 图片内容横向相关
     */
    public static RequestOptions contentHorizontalOptions = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.app_base_default_launcher_10)
            .error(R.drawable.app_base_default_launcher_10)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL);

    /**
     * Glide RequestOptions 图片内容纵向相关
     */
    public static RequestOptions contentVerticalOptions = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.app_base_default_launcher_12)
            .error(R.drawable.app_base_default_launcher_12)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL);

}
