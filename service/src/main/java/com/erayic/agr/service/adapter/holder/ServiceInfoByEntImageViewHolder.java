package com.erayic.agr.service.adapter.holder;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.service.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceInfoByEntImageViewHolder extends BaseViewHolder {

    @BindView(R2.id.service_info_item_img)
    public ImageView serviceInfoImg;

    public ServiceInfoByEntImageViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
