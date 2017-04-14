package com.erayic.agr.service.adapter.holder;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.view.ExpandableTextView;
import com.erayic.agr.service.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceInfoByEntDescriptViewHolder extends BaseViewHolder{

    @BindView(R2.id.service_info_descrip)
    public ExpandableTextView serviceInfoDescrip;

    public ServiceInfoByEntDescriptViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
