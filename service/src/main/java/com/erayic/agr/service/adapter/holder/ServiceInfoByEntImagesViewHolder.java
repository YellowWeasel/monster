package com.erayic.agr.service.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.service.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceInfoByEntImagesViewHolder extends BaseViewHolder{

    @BindView(R2.id.service_info_recycler_view)
    public RecyclerView serviceInfoRecyclerView;
    public ServiceInfoByEntImagesViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
