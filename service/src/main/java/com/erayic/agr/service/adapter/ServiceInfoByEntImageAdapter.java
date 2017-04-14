package com.erayic.agr.service.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.net.back.CommonImageBean;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.holder.ServiceInfoByEntImageViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceInfoByEntImageAdapter extends BaseQuickAdapter<CommonImageBean, ServiceInfoByEntImageViewHolder> {

    private Context context;

    public ServiceInfoByEntImageAdapter(Context context, List<CommonImageBean> data) {
        super(R.layout.adapter_service_info_ent_image_item, data);
        this.context = context;
    }

    @Override
    protected void convert(ServiceInfoByEntImageViewHolder helper, CommonImageBean item) {
        Glide.with(context).load(item.getPicturePath()).into(helper.serviceInfoImg);
    }

}
